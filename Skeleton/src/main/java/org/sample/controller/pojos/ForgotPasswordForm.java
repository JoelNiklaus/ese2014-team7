package org.sample.controller.pojos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class ForgotPasswordForm {

    private Long id;

    @NotNull
    @Email(message="Please enter a valid E-Mail address")
    private String email;    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
