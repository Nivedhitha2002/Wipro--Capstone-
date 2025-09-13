package com.wp.doconnect.question_service.repository;

import com.wp.doconnect.question_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    // Search by title or description
    List<Question> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

}
