package com.exam.service;

import com.exam.entity.Category;
import com.exam.entity.Quiz;
import com.exam.repo.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService{

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizzes() {
        return new LinkedHashSet<>(quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long id) {
        return quizRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteQuiz(Long id) {
        quizRepository.findById(id).ifPresent(quizRepository::delete);
    }

    @Override
    public List<Quiz> getQuizzesOfCategory(Category c) {
        return quizRepository.findByCategory(c);
    }

    @Override
    public List<Quiz> findByActive(boolean active) {
        return quizRepository.findByActive(active);
    }

    @Override
    public List<Quiz> findByCategoryAndActive(Category c, boolean b) {
        return quizRepository.findByCategoryAndActive(c,b);
    }
}
