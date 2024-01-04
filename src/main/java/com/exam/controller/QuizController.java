package com.exam.controller;

import com.exam.entity.Category;
import com.exam.entity.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.addQuiz(quiz));
    }

    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.updateQuiz(quiz));
    }

    @GetMapping("/")
    public ResponseEntity<Set<Quiz>> getQuizzes() {
        return ResponseEntity.ok(quizService.getQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuiz(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quiz> deleteQuiz(@PathVariable Long id) {
        System.out.println("deleteQuiz API Called");
        quizService.deleteQuiz(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{catId}")
    public ResponseEntity<?> getQuizzesOfCategory(@PathVariable Long catId) {
        return ResponseEntity.ok(quizService.getQuizzesOfCategory(Category.builder().id(catId).build()));
    }

    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes() {
        return quizService.findByActive(true);
    }

    @GetMapping("/category/active/{catId}")
    public List<Quiz> getActiveQuizzesOfCategory(@PathVariable Long catId) {
        return quizService.findByCategoryAndActive(Category.builder().id(catId).build(),true);
    }
}
