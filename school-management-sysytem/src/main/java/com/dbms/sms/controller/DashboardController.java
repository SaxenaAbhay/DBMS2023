package com.dbms.sms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

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
import com.dbms.sms.entity.Score;
import com.dbms.sms.entity.Student;
import com.dbms.sms.entity.Teacher;
import com.dbms.sms.repository.ClassRepository;
import com.dbms.sms.service.ClassService;
import com.dbms.sms.service.ExamService;
import com.dbms.sms.service.ScoreService;
import com.dbms.sms.service.StudentService;
import com.dbms.sms.service.TeacherService;
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
	private ScoreService scoreService;

	// Needed to automatically convert String date in form to Date object.
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		if (!isAuthenticated(session)) {
			return "redirect:/";
		}

		addDefaultAttributes(model, session);
		return "dashboard/index";
	}

	@PostMapping("/dashboard/studentUpdate")
	public String studentUpdate(@ModelAttribute Student userProfile, Model model, HttpSession session,
			RedirectAttributes attributes) {
		if (!isAuthenticated(session)) {
			return "redirect:/";
		}

		addDefaultAttributes(model, session);

		String userRole = model.getAttribute("userRole").toString();
		if (!userRole.equals("student")) {
			return "redirect:/";
		}

//        userProfile.setScholarId(model.getAttribute("username").toString());
		studentService.updateStudent(userProfile);
//        toastService.redirectWithSuccessToast(attributes, "Profile updated successfully.");
		return "redirect:/dashboard";
	}
	
	
	//Teacher Controller
	
	// handler method to handle list teachers and return mode and view

	@GetMapping("/teachers")
	public String listTeachers(Model model) {
		model.addAttribute("teachers", teacherService.getAllteachers());
		return "teachers";
	}

	@GetMapping("/teachers/new")
	public String createteacherForm(Model model) {
//		 if (!isAuthenticated(session)) {
//	            return "redirect:/";
//	        }
//		 addDefaultAttributes(model, session);
//		 String userRole = model.getAttribute("userRole").toString();
//	        if (!userRole.equals("admin")) {
//	            return "redirect:/";
//	        }
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		return "create_teacher";
	}

	@PostMapping("/teachers")
	public String saveteacher(@ModelAttribute("teacher") Teacher teacher) {
		teacherService.saveteacher(teacher);
		return "redirect:/teachers";
	}

	@GetMapping("/teachers/edit/{employeeId}")
	public String editteacherForm(@PathVariable Long employeeId, Model model) {
		model.addAttribute("teacher", teacherService.getteacherById(employeeId));
		return "edit_teacher";
	}

	@PostMapping("/teachers/{employeeId}")
	public String updateteacher(@PathVariable Long employeeId, @ModelAttribute("teacher") Teacher teacher,
			Model model) {
		Teacher existingteacher = teacherService.getteacherById(employeeId);
		existingteacher.setEmployeeId(employeeId);
		existingteacher.setFirstname(teacher.getFirstname());
		existingteacher.setLastname(teacher.getLastname());
		existingteacher.setEmail(teacher.getEmail());

		teacherService.updateteacher(existingteacher);
		return "redirect:/teachers";
	}

	@GetMapping("/teachers/{employeeId}")
	public String deleteTeacher(@PathVariable Long employeeId) {
		teacherService.deleteteacherById(employeeId);
		return "redirect:/teachers";
	}

	// Class Controller
//    	

	// handler method to handle list Classs and return mode and view

	@GetMapping("/classes")
	public String listStudents(Model model) {
		model.addAttribute("classes", classService.getAllClasses());
		return "classes";
	}

	@GetMapping("/classes/new")
	public String createClassForm(Model model) {
		Class classs = new Class();
		model.addAttribute("classes", classs);
		return "create_class";
	}

	@PostMapping("/classes")
	public String saveClass(@ModelAttribute("classs") Class classs) {
		classService.saveClass(classs);
		return "redirect:/classes";
	}

	// Student Controller

	@Autowired
	private StudentService studentService;

	// handler method to handle list students and return mode and view

	@GetMapping("/students")
	public String listStudetns(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}

	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		model.addAttribute("listClass", classRepository.findAll());
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
	}

	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}

	@GetMapping("/students/edit/{scholarId}")
	public String editStudentForm(@PathVariable Long scholarId, Model model) {
		model.addAttribute("student", studentService.getStudentById(scholarId));
		return "edit_student";
	}

	@PostMapping("/students/{scholarId}")
	public String updateStudent(@PathVariable Long scholarId, @ModelAttribute("student") Student student, Model model) {
		Student existingStudent = studentService.getStudentById(scholarId);
		existingStudent.setScholarId(scholarId);
		existingStudent.setFirstname(student.getFirstname());
		existingStudent.setLastname(student.getLastname());
		existingStudent.setEmail(student.getEmail());

		studentService.updateStudent(existingStudent);
		return "redirect:/students";
	}

	@GetMapping("/students/{scholarId}")
	public String deleteStudent(@PathVariable Long scholarId) {
		studentService.deleteStudentById(scholarId);
		return "redirect:/students";
	}

	
	//ExamController
	

	    @GetMapping("/exams")
		public String listExams(Model model) {
			model.addAttribute("exams", examService.getAllExams());
			return "exams";
		}


		@GetMapping("/exams/new")
		public String createexamForm(Model model){
			Exam exam= new Exam();
			model.addAttribute("exam", exam);
			return "create_exam";
		}

		@PostMapping("/exams")
		public String saveexam(@ModelAttribute("exam") Exam exam){
			examService.saveExam(exam);
			return "redirect:/exams";
		}
	
		
		//Score Controller





			//handler method to handle list scores and return mode and view
			
			@GetMapping("/scores")
			public String listScores(Model model) {
				model.addAttribute("scores", scoreService.getAllScores());
				return "scores";
			}


			@GetMapping("/scores/new")
			public String createscoreForm(Model model){
				Score score= new Score();
				model.addAttribute("score", score);
				return "create_score";
			}

			@PostMapping("/scores")
			public String savescore(@ModelAttribute("score") Score score){
				scoreService.saveScore(score);
				return "redirect:/scores";
			}

			@GetMapping("/scores/edit/{id}")
			public String editscoreForm(@PathVariable Long id, Model model){
				model.addAttribute("score", scoreService.getScoreById(id));
				return "edit_score";
			}

			@PostMapping("/scores/{id}")
			public String updatescore(@PathVariable Long id, @ModelAttribute("score") Score score, Model model){
				Score existingscore=scoreService.getScoreById(id);
				existingscore.setId(id);
				existingscore.setStudent(score.getStudent());
				existingscore.setExam(score.getExam());
				existingscore.setMarks(score.getMarks());
				scoreService.updateScore(existingscore);
				return "redirect:/scores";
			}

			@GetMapping("/scores/{id}")
			public String deleteScore(@PathVariable Long id){
				scoreService.deleteScoreById(id);
				return "redirect:/scores";
			}
		
			
			//
		
//	    
//    @GetMapping("/dashboard/Teacher/subject/{subjectId}/registration/{registrationId}")
//    public String TeacherSubjectRegistration(@PathVariable("subjectId") String subjectId, @PathVariable("registrationId") String registrationId, Model model, HttpSession session) {
//        if (!isAuthenticated(session)) {
//            return "redirect:/";
//        }
//
//        addDefaultAttributes(model, session);
//
//        String userRole = model.getAttribute("userRole").toString();
//        if (!userRole.equals("Teacher")) {
//            return "redirect:/";
//        }
//
//        String userEmail = model.getAttribute("username").toString();
//        Teacher Teacher = TeacherService.getTeacherByEmail(userEmail);
//        Subject subject = subjectService.getSubjectById(subjectId);
//        SemesterRegistrationSubject semesterRegistrationSubject = semesterRegistrationSubjectService.getById(registrationId);
//
//        model.addAttribute("subject", subject);
//        model.addAttribute("registration", semesterRegistrationSubject);
//
//        if (semesterRegistrationSubject.getResult() == null) {
//            model.addAttribute("result", new Result());
//        }
//
//        return "dashboard/TeacherSubjectRegistration";
//    }
//
//    @PostMapping("/dashboard/Teacher/subject/{subjectId}/registration/{registrationId}/grade")
//    public String TeacherGradeSubjectRegistration(@ModelAttribute Result result, @PathVariable("subjectId") String subjectId, @PathVariable("registrationId") String registrationId, Model model, HttpSession session, RedirectAttributes attributes) {
//        if (!isAuthenticated(session)) {
//            return "redirect:/";
//        }
//
//        addDefaultAttributes(model, session);
//
//        String userRole = model.getAttribute("userRole").toString();
//        if (!userRole.equals("Teacher")) {
//            return "redirect:/";
//        }
//
//        resultService.createResult(result, registrationId);
//        toastService.redirectWithSuccessToast(attributes, "Graded this student successfully.");
//        return "redirect:/dashboard/Teacher/subject/" + subjectId + "/registration/" + registrationId;
//    }
//
//    @GetMapping("/dashboard/Teacher/payouts")
//    public String TeacherPayouts(Model model, HttpSession session) {
//        if (!isAuthenticated(session)) {
//            return "redirect:/";
//        }
//
//        addDefaultAttributes(model, session);
//
//        String userRole = model.getAttribute("userRole").toString();
//        if (!userRole.equals("Teacher")) {
//            return "redirect:/";
//        }
//
//        String userEmail = model.getAttribute("username").toString();
//        Teacher Teacher = TeacherService.getTeacherByEmail(userEmail);
//        List<Payout> payouts = payoutService.getAllPayoutsByTeacher(Teacher);
//
//        model.addAttribute("payouts", payouts);
//        return "dashboard/TeacherPayouts";
//    }
//
//    @GetMapping("/dashboard/changePassword")
//    public String changePassword(Model model, HttpSession session) {
//        if (!isAuthenticated(session)) {
//            return "redirect:/";
//        }
//
//        addDefaultAttributes(model, session);
//        model.addAttribute("userObj", new User());
//        return "dashboard/changePassword";
//    }
//
//    @PostMapping("/dashboard/changePassword")
//    public String postChangePassword(@ModelAttribute User userObj, Model model, HttpSession session, RedirectAttributes attributes) {
//        if (!isAuthenticated(session)) {
//            return "redirect:/";
//        }
//
//        addDefaultAttributes(model, session);
//        userService.changePassword(model.getAttribute("username").toString(), userObj);
//        toastService.redirectWithSuccessToast(attributes, "Password changed successfully.");
//        return "redirect:/dashboard/changePassword";
//    }
}
