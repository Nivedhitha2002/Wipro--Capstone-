package com.wp.doconnect.question_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.wp.doconnect.question_service.entity.Question;
import com.wp.doconnect.question_service.entity.Answer;
import com.wp.doconnect.question_service.repository.QuestionRepository;
import com.wp.doconnect.question_service.repository.AnswerRepository;

@SpringBootApplication
public class QuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner testData(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        return args -> {
            System.out.println("===== Testing Question & Answer Repositories =====");

            // Create Questions
            Question q1 = new Question();
            q1.setTitle("How to reverse a string in Java?");
            q1.setDescription("I want to reverse a string without using built-in methods.");
            q1.setUserName("TestUser");
            questionRepository.save(q1);

            Question q2 = new Question();
            q2.setTitle("What is Spring Boot?");
            q2.setDescription("Explain basics of Spring Boot framework.");
            q2.setUserName("AdminUser");
            questionRepository.save(q2);

            System.out.println("All Questions in DB:");
            questionRepository.findAll().forEach(System.out::println);

            // Create Answers
            Answer a1 = new Answer();
            a1.setContent("Use a for loop to reverse characters one by one.");
            a1.setUserName("UserA");
            a1.setQuestion(q1);
            answerRepository.save(a1);

            Answer a2 = new Answer();
            a2.setContent("Spring Boot is a framework for rapid Spring applications.");
            a2.setUserName("UserB");
            a2.setQuestion(q2);
            answerRepository.save(a2);

            System.out.println("\nAll Answers in DB:");
            answerRepository.findAll().forEach(System.out::println);

            System.out.println("\nAnswers for first question:");
            answerRepository.findByQuestion(q1).forEach(System.out::println);
        };
    }
}

