package com.exam.repo;

import com.exam.entity.Category;
import com.exam.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    List<Quiz> findByCategory(Category c);
    List<Quiz> findByActive(boolean active);
    List<Quiz> findByCategoryAndActive(Category c,boolean b);
}
