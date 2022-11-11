package com.example.gamestore.entities.users;

import com.example.gamestore.exceprions.ValidationException;

//обект който валидира данните от конзолата
public class RegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;


    public RegisterDTO(String[] commandParts) {
        this.email = commandParts[1];
        this.password = commandParts[2];
        this.confirmPassword = commandParts[3];
        this.fullName = commandParts[4];

        this.validate();
    }

    private void validate() {
        int indexOfAt = email.indexOf("@");//ако има @ ще върне 1
        int indexOfDot = email.indexOf(".");
        if(indexOfAt < 0 || indexOfDot < 0 || indexOfAt > indexOfDot){
            throw new ValidationException("Email must contain @ or .");
        }

        //TODO validate password

        if (!password.equals(confirmPassword)){
            throw new ValidationException("Password and confirm password must match!");
        }

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
}
