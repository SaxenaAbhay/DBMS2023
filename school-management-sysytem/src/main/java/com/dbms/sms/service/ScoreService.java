package com.dbms.sms.service;

import java.util.List;

import com.dbms.sms.entity.Score;

public interface ScoreService {
	List<Score> getAllScores();

	Score saveScore(Score score);

	Score getScoreById(Long id);

	Score updateScore(Score score);

	void deleteScoreById(Long id);
}


