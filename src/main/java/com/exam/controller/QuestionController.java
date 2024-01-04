package com.exam.controller;

import com.exam.entity.Question;
import com.exam.entity.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;
    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(question));
    }

    @GetMapping("/")
    public ResponseEntity<Set<Question>> getQuestions() {
        return ResponseEntity.ok(questionService.getQuestions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestion(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/quiz/{id}")
    public ResponseEntity<List<Question>> getQuestionsOfQuiz(@PathVariable Long id) {
        Quiz quiz = quizService.getQuiz(id);
        Set<Question> questions = quiz.getQuestions();
        List<Question> list = new ArrayList<>(questions);
        if (list.size() > Integer.parseInt(quiz.getNoOfQuestions())) {
            list = list.subList(0,Integer.parseInt(quiz.getNoOfQuestions())+1);
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/quiz/all/{id}")
    public ResponseEntity<Set<Question>> getQuestionsOfQuizAdmin(@PathVariable Long id) {
        Quiz quiz = quizService.getQuiz(id);
        Set<Question> questions = quiz.getQuestions();
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
        int correctAnswers = 0;
        double marksGot = 0;
        int attempted = 0;
        for(Question question : questions) {
            Question q = questionService.getQuestion(question.getId());
            if(question.getGivenAnswer()!= null && q.getAnswer().trim().equalsIgnoreCase(question.getGivenAnswer().trim())) {
                correctAnswers++;
                double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                marksGot+=marksSingle;
            }
            if(question.getGivenAnswer() != null && !question.getGivenAnswer().trim().equalsIgnoreCase("")) {
                attempted++;
            }
        }
        Map<String, ? extends Number> map = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted", attempted);
        return ResponseEntity.ok(map);
    }
}
