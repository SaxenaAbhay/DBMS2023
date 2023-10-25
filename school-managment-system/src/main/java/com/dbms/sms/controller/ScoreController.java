package com.dbms.sms.controller;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dbms.sms.entity.Score;
import com.dbms.sms.repository.ClassRepository;
import com.dbms.sms.service.ClassService;
import com.dbms.sms.service.ScoreService;

@Controller
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;

	public ScoreController(ScoreService scoreService) {
		super();
		this.scoreService = scoreService;
	}
	//handler method to handle list scores and return mode and view
	
	@GetMapping("/scores")
	public String listStudetns(Model model) {
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
	public String deleteString(@PathVariable Long id){
		scoreService.deleteScoreById(id);
		return "redirect:/scores";
	}
}
