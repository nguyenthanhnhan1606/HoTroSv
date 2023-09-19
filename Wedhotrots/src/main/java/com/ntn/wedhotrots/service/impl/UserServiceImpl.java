package com.ntn.wedhotrots.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntn.wedhotrots.pojo.User;
import com.ntn.wedhotrots.repository.UserRepository;
import com.ntn.wedhotrots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEnc;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    @Override
    public boolean authUser(String username, String password) {
        Optional<User> userOptional = this.userRepo.getUserByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEnc.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public boolean addUser(Map<String, String> params, MultipartFile file) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            User u = new User();
            u.setActive((byte) 1);
            u.setEmail(params.get("email"));
            u.setGioitinh(params.get("gioitinh"));
            u.setName(params.get("name"));
            u.setNgaysinh(dateFormatter.parse(params.get("ngaysinh")));
            u.setUserRole("ROLE_USER");
            u.setPassword(passwordEnc.encode(params.get("password")));
            u.setUsername(params.get("username"));
            u.setSdt(params.get("sdt"));
            if(file != null && !file.isEmpty()){
                try {
                    Map res = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    u.setAvatar(res.get("secure_url").toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            userRepo.save(u);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> u = userRepo.getUserByUsername(username);
        if(u.isPresent()){
            User user = u.get();
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(user.getUserRole()));
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("Tài khoản không tồn tại!!");
    }
}
