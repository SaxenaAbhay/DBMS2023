package com.dbms.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dbms.sms.entity.Score;
import com.dbms.sms.repository.ScoreRepository;
import com.dbms.sms.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService
{
	private ScoreRepository scoreRepository;
	
	public ScoreServiceImpl(ScoreRepository scoreRepository) {
		super();
		this.scoreRepository = scoreRepository;
	}

	@Override
	public List<Score> getAllScores() {
		
		return scoreRepository.findAll();
	}

	@Override
	public Score saveScore(Score score) {
		return scoreRepository.save(score);
	}

	@Override
	public Score getScoreById(Long id) {
		return scoreRepository.findById(id).get();
	}

	@Override
	public Score updateScore(Score score) {
		return scoreRepository.save(score);
	}

	@Override
	public void deleteScoreById(Long id){
		scoreRepository.deleteById(id);
	}

}
