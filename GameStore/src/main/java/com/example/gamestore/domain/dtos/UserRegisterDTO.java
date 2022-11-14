package com.example.gamestore.domain.dtos;

import org.springframework.validation.annotation.Validated;

import java.util.regex.Pattern;

import static com.example.gamestore.constants.Commands.*;
import static com.example.gamestore.constants.Validations.*;

@Validated
public class UserRegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserRegisterDTO(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        validate ();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private void validate() throws IllegalArgumentException {
        final boolean isEmailValid = Pattern.matches (EMAIL_PATTERN, this.email);//валидираме имейла
        if (!isEmailValid){
            throw new IllegalArgumentException (EMAIL_NOT_VALID_MESSAGE);//ако не е валиден
        }

        final boolean isPasswordValid = Pattern.matches (PASSWORD_PATTERN, this.password);
        if(!isPasswordValid){
            throw new IllegalArgumentException(PASSWORD_NOT_VALID_MESSAGE);
        }

        if(!password.equals (confirmPassword)){
            throw new IllegalArgumentException (PASSWORDS_MISS_MATCH_MESSAGE);
        }
    }

    public String successfulRegisterFormat() {
        return String.format (SUCCSESSFUL_REGISTER_FORMAT, fullName);
    }
}
