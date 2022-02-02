package com.example.backbenchers;

public class UserFeedbackHelperClass {


    String name ;
    String username;
    String email;
    String feedback;


    public UserFeedbackHelperClass() {
    }

    public UserFeedbackHelperClass(String name, String username, String email, String feedback) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.feedback = feedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
