
package com.wp.doconnect.question_service.repository;

import com.wp.doconnect.question_service.entity.Answer;
import com.wp.doconnect.question_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    // Get all answers for a question
    List<Answer> findByQuestion(Question question);
}
