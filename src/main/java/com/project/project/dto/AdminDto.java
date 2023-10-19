package com.project.project.dto;

import jakarta.validation.constraints.Size;

public class AdminDto {
    
    @Size(min = 3,max = 10,message = "Give between (3-10) Charactiers")
    private String firstname;
     @Size(min = 3,max = 10,message = "Give between (3-10) Charactiers")
    private String lastname;
   //  @Size(min = 3,max = 10,message = "Give between (3-10) Charactiers")
    private String username;
     @Size(min = 5,max = 15,message = "Give between (3-10) Charactiers")
    private String password;

    private String repeatpassword;

    public AdminDto() {
    }
    public AdminDto(String firstname, String lastname, String username, String password, String repeatpassword) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.repeatpassword = repeatpassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatpassword() {
        return repeatpassword;
    }

    public void setRepeatpassword(String repeatpassword) {
        this.repeatpassword = repeatpassword;
    }
    
}
