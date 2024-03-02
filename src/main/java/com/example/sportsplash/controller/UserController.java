package com.example.sportsplash.controller;

import com.example.sportsplash.service.sportsservice;
import com.example.sportsplash.sports.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private sportsservice sportsservice;
    /*For getting all the users */
    @GetMapping("/signup")
    public List<User> getUser() {
        return this.sportsservice.getUser();
    }
    /* For creating new user*/
    @PostMapping("/signup")
    public User createUser(@RequestBody User s){
        return this.sportsservice.createUser(s);
    }
    /*For updating the user*/
    @PutMapping("/signup")
    public User updateUser(@RequestBody User s){
        return this.sportsservice.updateUser(s);
    }
    /*For verifying the user*/
    @PostMapping("/verifyUser")
    public User verifyUser(@RequestBody User s){
        return this.sportsservice.verifyUser(s);
    }
    /*For deleting the user*/
    @DeleteMapping("/signup/{email}")
    public ResponseEntity<HttpStatus> deletesports(@PathVariable String email) {
        try {
            this.sportsservice.deleteUser(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getall")
    public List<Object> getAlltheMatches(){
        return this.sportsservice.getAlltheMatches();
    }

}
