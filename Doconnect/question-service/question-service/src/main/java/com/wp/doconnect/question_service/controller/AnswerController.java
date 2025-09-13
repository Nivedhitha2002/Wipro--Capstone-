package com.wp.doconnect.question_service.controller;

import com.wp.doconnect.question_service.entity.Answer;
import com.wp.doconnect.question_service.entity.Question;
import com.wp.doconnect.question_service.repository.AnswerRepository;
import com.wp.doconnect.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // Create answer
    @PostMapping
    public Answer createAnswer(@RequestParam Long questionId, @RequestBody Answer answer) {
        Question question = questionRepository.findById(questionId).orElseThrow();
        answer.setQuestion(question);
        return answerRepository.save(answer);
    }

    // Get all answers
    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    // Get answers for a specific question
    @GetMapping("/question/{questionId}")
    public List<Answer> getAnswersByQuestion(@PathVariable Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow();
        return answerRepository.findByQuestion(question);
    }

    // Approve answer
    @PutMapping("/{id}/approve")
    public Answer approveAnswer(@PathVariable Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow();
        answer.setApproved(true);
        return answerRepository.save(answer);
    }

    // Delete answer
    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        answerRepository.deleteById(id);
    }
}
