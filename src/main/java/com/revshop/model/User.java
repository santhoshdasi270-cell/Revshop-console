package com.revshop.model;

public abstract class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String securityQuestion;
    private String securityAnswer;


    public User(String id, String name, String email, String password,
                String securityQuestion, String securityAnswer) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }


    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }


    public String getPassword() {
        return password;
    }

    // Setter for password (for change password feature later)
    public void setPassword(String password) {
        this.password = password;
    }
}
