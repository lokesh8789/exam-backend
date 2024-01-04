package com.exam.service;

import com.exam.entity.Category;
import com.exam.entity.Quiz;

import java.util.List;
import java.util.Set;

public interface QuizService {
    Quiz addQuiz(Quiz quiz);
    Quiz updateQuiz(Quiz quiz);
    Set<Quiz> getQuizzes();
    Quiz getQuiz(Long id);
    void deleteQuiz(Long id);
    List<Quiz> getQuizzesOfCategory(Category c);
    List<Quiz> findByActive(boolean active);
    List<Quiz> findByCategoryAndActive(Category c,boolean b);
}
