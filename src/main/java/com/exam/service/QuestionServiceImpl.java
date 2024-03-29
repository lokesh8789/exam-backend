package com.exam.service;

import com.exam.entity.Question;
import com.exam.entity.Quiz;
import com.exam.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new HashSet<>(questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.findById(id).ifPresent(questionRepository::delete);
    }

    @Override
    public Set<Question> getQuestionOfQuiz(Quiz quiz) {
        return questionRepository.findByQuiz(quiz);
    }
}
