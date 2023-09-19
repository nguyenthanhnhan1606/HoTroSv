package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.components.JwtService;
import com.ntn.wedhotrots.pojo.User;
import com.ntn.wedhotrots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userSer;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;


    @GetMapping("current-user")
    @CrossOrigin
    public User detail(Principal user){
        Optional<User> u = this.userSer.getUserByUsername(user.getName());
        return new ResponseEntity<>(u.get(), HttpStatus.OK).getBody();
    }

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<String> Login(@RequestBody User user){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("error",HttpStatus.OK);
        } catch (DisabledException disabledException) {
            return null;
        }
        final UserDetails userDetails = this.userSer.loadUserByUsername(user.getUsername());
        final String token = jwtService.generateToken(userDetails.getUsername());
        return new ResponseEntity<>(token, HttpStatus.OK);

    }

    @PostMapping("/register")
    @CrossOrigin
    public boolean addUser(@RequestParam Map<String, String> params, @RequestParam MultipartFile file) throws IOException {
        return userSer.addUser(params,file);
    }
}
