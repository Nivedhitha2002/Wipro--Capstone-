package com.wp.doconnect.question_service.entity;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;        // The answer text
    private String userName;       // Who answered
    private boolean approved = false;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;     // Link to the question

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

@Override
public String toString() {
    return "Answer{" +
            "id=" + id +
            ", content='" + content + '\'' +
            ", userName='" + userName + '\'' +
            ", questionId=" + (question != null ? question.getId() : null) +
            '}';
}
}