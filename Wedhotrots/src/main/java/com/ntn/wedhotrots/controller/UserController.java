package com.ntn.wedhotrots.controller;

import com.ntn.wedhotrots.components.JwtService;
import com.ntn.wedhotrots.pojo.User;
import com.ntn.wedhotrots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
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

    @GetMapping("/listuser")
    @CrossOrigin
    public List<User> getAlls(@RequestParam Map<String, String> params){
        return userSer.getAllsUser(params);
    }
    @GetMapping("/listuser/page")
    @CrossOrigin
    public double Page(){
        double count = userSer.getAllsUser(null).size();
        return Math.ceil(count/2);
    }

    @GetMapping("/listuser/{id}")
    @CrossOrigin
    public User getUserByName(@PathVariable int id) {
        return this.userSer.getUserById(id);
    }

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
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername().trim(),user.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("error",HttpStatus.OK);
        } catch (DisabledException disabledException) {
            return null;
        }
        final UserDetails userDetails = this.userSer.loadUserByUsername(user.getUsername().trim());
        final String token = jwtService.generateToken(userDetails.getUsername());
        return new ResponseEntity<>(token, HttpStatus.OK);

    }

    @PostMapping("/register")
    @CrossOrigin
    public boolean addUser(@RequestParam Map<String, String> params, @RequestParam MultipartFile file) throws IOException {
        return userSer.addUser(params,file);
    }

    @PutMapping("/listuser/{id}")
    @CrossOrigin
    public boolean updateUser(@RequestBody User u){
        return this.userSer.updateUser(u);
    }

    @PutMapping("/listuser/recyclebin/{id}")
    @CrossOrigin
    public boolean RecycleBinUser(@PathVariable int id){
        return this.userSer.recycleBin(id);
    }
}
