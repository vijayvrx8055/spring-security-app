package com.vrx.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAppController {

    @GetMapping("/secure/user")
    public ResponseEntity<String> getSecureDetails(){
        return ResponseEntity.ok("Highly Secured Information");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/secure/users")
    public String userEndpoint(){
        return "Hello, User !!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/secure/admins")
    public String adminEndpoint(){
        return "Hello, Admin !!";
    }

}
