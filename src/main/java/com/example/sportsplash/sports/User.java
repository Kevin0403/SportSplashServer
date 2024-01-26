package com.example.sportsplash.sports;

import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.FetchType.EAGER;



@Entity
public class User {
    @Id
    String email;
    String mobileno;
    String university;
    String password;
    String fname;
    String lname;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", university='" + university + '\'' +
                ", password='" + password + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +

                '}';
    }

    public User(String email, String mobileno, String university, String password, String fname, String lname) {
        this.email = email;
        this.mobileno = mobileno;
        this.university = university;
        this.password = password;
        this.fname = fname;
        this.lname = lname;

    }

    public String getFname() {

        return fname;
    }


    public void setFname(String fname) {

        this.fname = fname;
    }

    public String getLname() {

        return lname;
    }

    public void setLname(String lname) {

        this.lname = lname;
    }

    public User() {

        super();
    }


    public User(String email) {
        this.email = email;
    }

}
