package com.exam.service;

import com.exam.entity.Question;
import com.exam.entity.Quiz;

import java.util.Set;

public interface QuestionService {
    Question addQuestion(Question question);
    Question updateQuestion(Question question);
    Set<Question> getQuestions();
    Question getQuestion(Long id);
    void deleteQuestion(Long id);
    Set<Question> getQuestionOfQuiz(Quiz quiz);
}
