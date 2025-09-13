package com.wp.doconnect.question_service.controller;

import com.wp.doconnect.question_service.entity.Question;
import com.wp.doconnect.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    // Create question
    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }

    // Get all questions
    @GetMapping
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // Search questions
    @GetMapping("/search")
    public List<Question> searchQuestions(@RequestParam String query) {
        return questionRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }

    // Update question
    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody Question q) {
        Question question = questionRepository.findById(id).orElseThrow();
        question.setTitle(q.getTitle());
        question.setDescription(q.getDescription());
        return questionRepository.save(question);
    }

    // Delete question
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionRepository.deleteById(id);
    }

    // Mark as approved (admin)
    @PutMapping("/{id}/approve")
    public Question approveQuestion(@PathVariable Long id) {
        Question question = questionRepository.findById(id).orElseThrow();
        question.setApproved(true);
        return questionRepository.save(question);
    }

    // Mark as resolved
    @PutMapping("/{id}/resolve")
    public Question resolveQuestion(@PathVariable Long id) {
        Question question = questionRepository.findById(id).orElseThrow();
        question.setResolved(true);
        return questionRepository.save(question);
    }
}
