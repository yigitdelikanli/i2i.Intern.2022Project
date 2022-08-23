package com.eyecell.rest.api.resource;

public class ForgotPassword {
    private String Email;
    private String SecurityQuestion;

    public ForgotPassword(String email, String securityQuestion) {
        Email = email;
        SecurityQuestion = securityQuestion;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSecurityQuestion() {
        return SecurityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        SecurityQuestion = securityQuestion;
    }
}
