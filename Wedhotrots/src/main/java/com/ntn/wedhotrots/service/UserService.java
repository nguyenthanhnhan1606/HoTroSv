package com.ntn.wedhotrots.service;

import com.ntn.wedhotrots.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> getUserByUsername(String username);

    boolean authUser(String username, String password);
    boolean addUser(Map<String, String> params, MultipartFile file) throws IOException;
}
