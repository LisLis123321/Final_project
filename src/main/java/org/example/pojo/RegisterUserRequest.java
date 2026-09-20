package org.example.pojo;

public class RegisterUserRequest {
    private String email;
    private String password;
    private String repeatPassword;

    public RegisterUserRequest(String email, String password, String repeatPassword) {
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
