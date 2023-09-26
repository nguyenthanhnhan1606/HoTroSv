package com.ntn.wedhotrots.service;

import com.ntn.wedhotrots.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> getUserByUsername(String username);

    User getUserById(int id);

    List<User> getAllsUser();

    boolean checkUsername( String username);
    boolean authUser(String username, String password);
    boolean updateUser(User u);
    boolean addUser(Map<String, String> params, MultipartFile file) throws IOException;
    boolean recycleBin(int id);
}
