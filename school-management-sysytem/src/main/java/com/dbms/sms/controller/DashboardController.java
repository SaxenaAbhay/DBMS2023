package com.dbms.sms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dbms.sms.entity.Class;
import com.dbms.sms.entity.Exam;
import com.dbms.sms.entity.Message;
import com.dbms.sms.entity.Score;
import com.dbms.sms.entity.Student;
import com.dbms.sms.entity.Subject;
import com.dbms.sms.entity.Teacher;
import com.dbms.sms.entity.User;
import com.dbms.sms.repository.ClassRepository;
import com.dbms.sms.repository.UserRepository;
import com.dbms.sms.service.AuthenticationService;
import com.dbms.sms.service.ClassService;
import com.dbms.sms.service.ExamService;
import com.dbms.sms.service.ScoreService;
import com.dbms.sms.service.StudentService;
import com.dbms.sms.service.SubjectService;
import com.dbms.sms.service.TeacherService;
import com.dbms.sms.service.ToastService;
import com.dbms.sms.service.UserService;

@Controller
public class DashboardController extends BaseController {
	
	@Autowired
	private TeacherService teacherService;

	@Autowired
	private UserService userService;
	
	@Autowired
    private ExamService examService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private AuthenticationService authenticationService;
	

	// Needed to automatically convert String date in form to Date object.
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	@GetMapping("/")
	public String dashboard(Model model, HttpSession session) {
		if (!isAuthenticated(session)) {
			return "redirect:/login";
		}
		
		addDefaultAttributes(model, session);
		 String userRole = model.getAttribute("userRole").toString();
	        if (!userRole.equals("admin")) {
	            return "teacher_dashboard";
	        }
		return "dashboard";
	}
	
	//Register
	
	@GetMapping("/register")
    public String register(Model model, HttpSession session) {
        if (!authenticationService.isAuthenticated(session)) {
            return "redirect:/";
        }
        String currentUser = authenticationService.getCurrentUser(session);
//        model.addAttribute("username", currentUser);
        String userRole = userService.getRole(currentUser);
        if (!userRole.equals("admin")) {
//       	attributes.addFlashAttribute("errorMsg","You don't have the rights to access this.");
           return "teacher_dashboard";
       }
        model.addAttribute("credentials", new User());
        return "register";
    }
    
    @PostMapping("/register")
    public String postRegister(@ModelAttribute User credentials, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
//        if (authenticationService.isAuthenticated(session)) {
//            return "redirect:/";
//        }

        String username = credentials.getUsername();
        
        
        String password = credentials.getPassword();
        
//        Boolean isAdmin= credentials.getIsAdmin();
//         System.out.println(isAdmin);
        String errorMessage=null;
        try {
            if (!authenticationService.checkCredentials(username, password)) {
            	
            }
            errorMessage="User already exists";
            session.setAttribute("message", new Message(errorMessage, "danger"));
            return "redirect:/register";
        }
           catch(Exception e){
        	  userRepository.createUser(username,password);
              session.setAttribute("message", new Message("Successfully Registered User", "success"));

            }
        return "redirect:/";
    }   
	
	
	//Teacher Controller
	
	// handler method to handle list teachers and return mode and view

	@GetMapping("/teachers")
	public String listTeachers(Model model,HttpSession session) {
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		model.addAttribute("teachers", teacherService.getAllteachers());
		return "teachers";
	}

	@GetMapping("/teachers/new")
	public String createteacherForm(Model model,HttpSession session,RedirectAttributes attributes) {
		 if (!isAuthenticated(session)) {
	            return "redirect:/login";
	        }
		 addDefaultAttributes(model, session);
		 String userRole = model.getAttribute("userRole").toString();
	        if (!userRole.equals("admin")) {
	        	session.setAttribute("message", new Message("You don't have the right to add teacher details", "danger"));
	            return "teachers";
	        }
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		return "create_teacher";
	}

	@PostMapping("/teachers")
	public String saveteacher(@ModelAttribute("teacher") Teacher teacher,HttpSession session) {
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		teacherService.saveteacher(teacher);
		return "redirect:/teachers";
	}

	@GetMapping("/teachers/edit/{employeeId}")
	public String editteacherForm(@PathVariable Long employeeId, Model model,HttpSession session) {
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		addDefaultAttributes(model, session);
		 String userRole = model.getAttribute("userRole").toString();
	        if (!userRole.equals("admin")) {
//	        	attributes.addFlashAttribute("errorMsg","You don't have the rights to access this.");
	            return "errorMsg";
	        }
		model.addAttribute("teacher", teacherService.getteacherById(employeeId));
		return "edit_teacher";
	}

	@PostMapping("/teachers/{employeeId}")
	public String updateteacher(@PathVariable Long employeeId, @ModelAttribute("teacher") Teacher teacher,
			Model model,HttpSession session) {
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		addDefaultAttributes(model, session);
		 String userRole = model.getAttribute("userRole").toString();
	        if (!userRole.equals("admin")) {
//	        	attributes.addFlashAttribute("errorMsg","You don't have the rights to access this.");
	            return "errorMsg";
	        }
	        
	        Teacher existingteacher=teacherService.getteacherById(employeeId);
	        existingteacher.setEmployeeId(employeeId);
			existingteacher.setFirstname(teacher.getFirstname());
			existingteacher.setLastname(teacher.getLastname());
			existingteacher.setEmail(teacher.getEmail());
			existingteacher.setSubId(teacher.getSubId());
			existingteacher.setSalary(teacher.getSalary());

	        
			teacherService.updateteacher(existingteacher);
			return "redirect:/teachers";

	}

	@GetMapping("/teachers/{employeeId}")
	public String deleteTeacher(@PathVariable Long employeeId,Model model,HttpSession session) {
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		addDefaultAttributes(model, session);
		 String userRole = model.getAttribute("userRole").toString();
	        if (!userRole.equals("admin")) {
	            return "errorMsg";
	        }
		teacherService.deleteteacherById(employeeId);
		return "redirect:/teachers";
	}

	// Class Controller
//    	

	// handler method to handle list Classs and return mode and view

//	private ClassService classService;
	@GetMapping("/classes")
	public String listClasses(Model model,HttpSession session) {
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		
		model.addAttribute("classes", classService.getAllClasses());
		return "classes";
	}


	@GetMapping("/classes/new")
	public String createClassForm(Model model,HttpSession session){
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		addDefaultAttributes(model, session);
		 String userRole = model.getAttribute("userRole").toString();
	        if (!userRole.equals("admin")) {
	        	session.setAttribute("message", new Message("You don't have the right to add class details", "danger"));
	            return "classes";
	        }
		Class classs= new Class();
		model.addAttribute("classes", classs);
		return "create_class";
	}

	@PostMapping("/classes")
	public String saveClass(@ModelAttribute("classs") Class classs,HttpSession session){
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		classService.saveClass(classs);
		return "redirect:/classes";
	}

		
	@GetMapping("/classes/edit/{classId}")
	public String editClassForm(@PathVariable Long classId, Model model,HttpSession session){
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		addDefaultAttributes(model, session);
		 String userRole = model.getAttribute("userRole").toString();
	        if (!userRole.equals("admin")) {
	        	session.setAttribute("message", new Message("You don't have the right to update class details", "danger"));
	            return "classes";
	        }
		model.addAttribute("classs", classService.getClassById(classId));
		return "edit_class";
	}

	@PostMapping("/classes/{classId}")
	public String updateClass(@PathVariable Long classId, @ModelAttribute("classs") Class classs, Model model,HttpSession session){
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		Class existingclass=classService.getClassById(classId);
		existingclass.setClassId(classId);
		existingclass.setSection(classs.getSection());
		existingclass.setStandard(classs.getStandard());

		classService.updateClass(existingclass);
		return "redirect:/classes";
	}

	@GetMapping("/classes/{classId}")
	public String deleteClass(@PathVariable Long classId,HttpSession session,Model model){
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		addDefaultAttributes(model, session);
		 String userRole = model.getAttribute("userRole").toString();
	        if (!userRole.equals("admin")) {
	        	session.setAttribute("message", new Message("You don't have the right to delete class details", "danger"));
	            return "redirect:/classes";
	        }
		classService.deleteClassById(classId);
		return "redirect:/classes";
	}

	// Student Controller

	@Autowired
	private StudentService studentService;

	// handler method to handle list students and return mode and view

	@GetMapping("/students")
	public String listStudetns(Model model,HttpSession session) {
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}

	@GetMapping("/students/new")
	public String createStudentForm(Model model,HttpSession session,RedirectAttributes attributes) {
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		addDefaultAttributes(model, session);
		 String userRole = model.getAttribute("userRole").toString();
	        if (!userRole.equals("admin")) {
	        	session.setAttribute("message",new Message("You don't have the right to add students.","danger"));
	            return "redirect:/students";
	        }
		model.addAttribute("listClass", classRepository.findAll());
		Student student = new Student();
		model.addAttribute("student", student);
		session.setAttribute("message",new Message("Student added!!","success"));
		return "create_student";
	}

	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student,HttpSession session,Model model,RedirectAttributes attributes) {
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		
		studentService.saveStudent(student);
		return "redirect:/students";
	}

	@GetMapping("/students/edit/{scholarId}")
	public String editStudentForm(@PathVariable Long scholarId, Model model,HttpSession session) {
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		addDefaultAttributes(model, session);
		 String userRole = model.getAttribute("userRole").toString();
	        if (!userRole.equals("admin")) {
	        	session.setAttribute("message",new Message("You don't have the right to update student details","danger"));
	            return "redirect:/students";
	        }
		model.addAttribute("student", studentService.getStudentById(scholarId));
		return "edit_student";
	}

	@PostMapping("/students/{scholarId}")
	public String updateStudent(@PathVariable Long scholarId, @ModelAttribute("student") Student student, Model model,HttpSession session) {
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		addDefaultAttributes(model, session);
		 String userRole = model.getAttribute("userRole").toString();
	        if (!userRole.equals("admin")) {
	        	session.setAttribute("message", new Message("You don't have the right to update student details", "danger"));
	            return "redirect:/students";
	        }
		Student existingStudent = studentService.getStudentById(scholarId);
		existingStudent.setScholarId(scholarId);
		existingStudent.setFirstname(student.getFirstname());
		existingStudent.setLastname(student.getLastname());
		existingStudent.setEmail(student.getEmail());

		studentService.updateStudent(existingStudent);
		return "redirect:/students";
	}

	@GetMapping("/students/{scholarId}")
	public String deleteStudent(@PathVariable Long scholarId,HttpSession session,Model model) {
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		
		addDefaultAttributes(model, session);
		 String userRole = model.getAttribute("userRole").toString();
	        if (!userRole.equals("admin")) {
	        	session.setAttribute("message",new Message("You don't have the right to delete student details.","danger"));
	            return "redirect:/students";
	        }
		studentService.deleteStudentById(scholarId);
		return "redirect:/students";
	}

	
	//ExamController
	

	@GetMapping("/exams")
	public String listExams(Model model,HttpSession session) {
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		model.addAttribute("exams", examService.getAllExams());
		return "exams";
	}


	@GetMapping("/exams/new")
	public String createexamForm(Model model,HttpSession session){
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		Exam exam= new Exam();
		model.addAttribute("exam", exam);
		return "create_exam";
	}

	@PostMapping("/exams")
	public String saveexam(@ModelAttribute("exam") Exam exam,HttpSession session){
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		examService.saveExam(exam);
		return "redirect:/exams";
	}


	
	@GetMapping("/exams/edit/{examId}")
	public String editSExamForm(@PathVariable Long examId, Model model,HttpSession session){
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		model.addAttribute("exam", examService.getExamById(examId));
		return "edit_exam";
	}

	@PostMapping("/exams/{examId}")
	public String updateExam(@PathVariable Long examId, @ModelAttribute("exam") Exam exam, Model model,HttpSession session){
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		Exam existingexam=examService.getExamById(examId);
		existingexam.setExamId(examId);
		existingexam.setDate(exam.getDate());
		existingexam.setSlot(exam.getSlot());
		existingexam.setStandard(exam.getStandard());
		existingexam.setTotalMarks(exam.getTotalMarks());
		existingexam.setType(exam.getType());

		examService.updateExam(existingexam);
		return "redirect:/exams";
	}

	@GetMapping("/exams/{examId}")
	public String deleteExam(@PathVariable Long examId,HttpSession session){
		if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
		examService.deleteExamById(examId);
		return "redirect:/exams";
	}
	
		//Score Controller





			//handler method to handle list scores and return mode and view
			
			@GetMapping("/scores")
			public String listScores(Model model,HttpSession session) {
				if (!isAuthenticated(session)) {
		            return "redirect:/login";
		        }

				model.addAttribute("scores", scoreService.getAllScores());
				return "scores";
			}


			@GetMapping("/scores/new")
			public String createscoreForm(Model model,HttpSession session){
				if (!isAuthenticated(session)) {
		            return "redirect:/login";
		        }

				Score score= new Score();
				model.addAttribute("score", score);
				return "create_score";
			}

			@PostMapping("/scores")
			public String savescore(@ModelAttribute("score") Score score,HttpSession session){
				if (!isAuthenticated(session)) {
		            return "redirect:/login";
		        }

				scoreService.saveScore(score);
				return "redirect:/scores";
			}

			@GetMapping("/scores/edit/{id}")
			public String editscoreForm(@PathVariable Long id, Model model,HttpSession session){
				if (!isAuthenticated(session)) {
		            return "redirect:/login";
		        }
				model.addAttribute("score", scoreService.getScoreById(id));
				return "edit_score";
			}

			@PostMapping("/scores/{id}")
			public String updatescore(@PathVariable Long id, @ModelAttribute("score") Score score, Model model,HttpSession session){
				if (!isAuthenticated(session)) {
		            return "redirect:/login";
		        }
				Score existingscore=scoreService.getScoreById(id);
				existingscore.setId(id);
				existingscore.setStudent(score.getStudent());
				existingscore.setExam(score.getExam());
				existingscore.setMarks(score.getMarks());
				scoreService.updateScore(existingscore);
				return "redirect:/scores";
			}

			@GetMapping("/scores/{id}")
			public String deleteScore(@PathVariable Long id,HttpSession session){
				if (!isAuthenticated(session)) {
		            return "redirect:/login";
		        }
				scoreService.deleteScoreById(id);
				return "redirect:/scores";
			}
		
	//Subject Controller
			

			@Autowired
			private SubjectService subjectService;
			
			@GetMapping("/subjects")
			public String listSubjects(Model model,HttpSession session) {
				if (!isAuthenticated(session)) {
		            return "redirect:/login";
		        }
				model.addAttribute("subjects", subjectService.getAllSubjects());
				return "subjects";
			}


			@GetMapping("/subjects/new")
			public String createsubjectForm(Model model,HttpSession session){
				if (!isAuthenticated(session)) {
		            return "redirect:/login";
		        }
				addDefaultAttributes(model, session);
				 String userRole = model.getAttribute("userRole").toString();
			        if (!userRole.equals("admin")) {
			        	session.setAttribute("message", new Message("You don't have the right to add subject details", "danger"));
			            return "redirect:/subjects";
			        }
				model.addAttribute("listClass", classRepository.findAll());
				Subject subject= new Subject();
				model.addAttribute("subject", subject);
				return "create_subject";
			}

			@PostMapping("/subjects")
			public String savesubject(@ModelAttribute("subject") Subject subject,Model model,HttpSession session){
				if (!isAuthenticated(session)) {
		            return "redirect:/login";
		        }
				addDefaultAttributes(model, session);
				 String userRole = model.getAttribute("userRole").toString();
			        if (!userRole.equals("admin")) {
//			        	attributes.addFlashAttribute("errorMsg","You don't have the rights to access this.");
			            return "errorMsg";
			        }
				subjectService.saveSubject(subject);
				return "redirect:/subjects";
			}

			@GetMapping("/subjects/edit/{subId}")
			public String editsubjectForm(@PathVariable Long subId, Model model,HttpSession session){
				if (!isAuthenticated(session)) {
		            return "redirect:/login";
		        }
				addDefaultAttributes(model, session);
				 String userRole = model.getAttribute("userRole").toString();
			        if (!userRole.equals("admin")) {
			        	session.setAttribute("message", new Message("You don't have the right to edit subject details", "danger"));
			            return "redirect:/subjects";
			        }
				model.addAttribute("subject", subjectService.getSubjectById(subId));
				return "edit_subject";
			}

			@PostMapping("/subjects/{subId}")
			public String updatesubject(@PathVariable Long subId, @ModelAttribute("subject") Subject subject, Model model,HttpSession session){
				if (!isAuthenticated(session)) {
		            return "redirect:/login";
		        }
				addDefaultAttributes(model, session);
				 String userRole = model.getAttribute("userRole").toString();
			        if (!userRole.equals("admin")) {
//			        	attributes.addFlashAttribute("errorMsg","You don't have the rights to access this.");
			            return "errorMsg";
			        }
				Subject existingsubject=subjectService.getSubjectById(subId);
				existingsubject.setSubId(subId);
				existingsubject.setSub_name(subject.getSub_name());

				subjectService.updateSubject(existingsubject);
				return "redirect:/subjects";
			}

			@GetMapping("/subjects/{subId}")
			public String deleteSubject(@PathVariable Long subId,HttpSession session,Model model){
				if (!isAuthenticated(session)) {
		            return "redirect:/login";
		        }
				addDefaultAttributes(model, session);
				 String userRole = model.getAttribute("userRole").toString();
			        if (!userRole.equals("admin")) {
			        	session.setAttribute("message", new Message("You don't have the right to delete subject details", "danger"));
			            return "redirect:/subjects";
			        }
				subjectService.deleteSubjectById(subId);
				return "redirect:/subjects";
			}

    @GetMapping("/change_password")
    public String changePassword(Model model, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        addDefaultAttributes(model, session);
        model.addAttribute("userObj", new User());
        return "change_password";
    }

    @PostMapping("/post_change_password")
    public String postChangePassword(@ModelAttribute User userObj, Model model, HttpSession session, RedirectAttributes attributes) {
        if (!isAuthenticated(session)) {
            return "redirect:/";
        }

        addDefaultAttributes(model, session);
        userService.changePassword(model.getAttribute("username").toString(), userObj);
//        toastService.redirectWithSuccessToast(attributes, "Password changed successfully.");
//        return "login";
          authenticationService.logoutUser(session);
          return "redirect:/";
    }
}
