package com.example.sportsplash.service;

import com.example.sportsplash.dao.sportsdao;
import com.example.sportsplash.dao.tournamentdao;
import com.example.sportsplash.sports.Tournaments;
import com.example.sportsplash.sports.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class sportsserviceimpl implements sportsservice{

      @Autowired
      private sportsdao sd;
      @Autowired
      private tournamentdao td;

    public sportsserviceimpl() {
        super();
    }

    @Override
    public User verifyUser(User s) {
        String email = s.getEmail();
        User s1 = sd.findByEmail(email);

        if (s1.getPassword().equals(s.getPassword())) {
            return s1;

        } else
            return null;
    }




    @Override
    public List<User> getUser(){


        return  sd.findAll();
    }


    @Override
    public User createUser(User s) {
       sd.save(s);
       return s;
    }

    @Override
    public User updateUser(User s) {
        sd.save(s);
        return  s;
    }

    @Override
    public void deleteUser(String email) {
        User entity=sd.getOne(email);
        sd.delete(entity);

    }
    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullpath=path+File.separator+fileName;
        InputStream is=new FileInputStream(fullpath);

        return is;
    }

    @Override
    public String uploadImage(String path, MultipartFile file) throws  IOException {

        String name = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
        String filepath = path + File.separator + fileName1;

        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filepath));
        return fileName1;
    }

//    @Override
//    public Tournaments createTournament(Tournaments t) {
//        td.save(t);
//        return t;
//
//    }
    @Override
    public User retrieveOrCreateUser(User user) {
        User existingUser = sd.findByEmail(user.getEmail());

        if (existingUser == null) {
            sd.save(user);
            return user;
        } else {
            return existingUser;
        }
    }

    @Override
    @Transactional
    public void createTournament(Tournaments t) {
        // Retrieve or create user entity
        User existingUser = sd.findByEmail(t.getUser().getEmail());

        if (existingUser == null) {
            // User doesn't exist, save the new user to the database
            User newUser = new User();
            newUser.setEmail(t.getUser().getEmail());
            sd.save(newUser);
            t.setUser(newUser);
        } else {
            // User already exists, use the existing user
            t.setUser(existingUser);
        }

        // Set other tournament properties based on your requirements
        // tournament.setXXX(...);
        if (t.getUser() == null) {
            throw new IllegalArgumentException("User cannot be null in the tournament.");
        }

        td.save(t);
    }
}
