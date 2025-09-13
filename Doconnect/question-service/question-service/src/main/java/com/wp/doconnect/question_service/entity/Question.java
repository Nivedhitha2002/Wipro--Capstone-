package com.wp.doconnect.question_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean approved = false;
    private boolean resolved = false;
    private String userName;

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }

    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

@Override
public String toString() {
    return "Question{" +
           "id=" + id +
           ", title='" + title + '\'' +
           ", description='" + description + '\'' +
           ", approved=" + approved +
           ", resolved=" + resolved +
           ", userName='" + userName + '\'' +
           '}';
}
}