package com.ntn.wedhotrots.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntn.wedhotrots.pojo.User;
import com.ntn.wedhotrots.repository.UserRepository;
import com.ntn.wedhotrots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public User getUserById(int id) {
        Optional<User> u= userRepo.findById(id);
        return  u.get();
    }

    @Override
    public List<User> getAllsUser(Map<String, String> params) {
        Pageable pageable = Pageable.unpaged();
        String search="";

        if (params != null) {
            String pageStr = params.get("page");
            if (pageStr != null && !pageStr.isEmpty()) {
                int page = Integer.parseInt(pageStr);
                page -=1;
                pageable = PageRequest.of(page, 2, Sort.by("id").descending());
            }
            search = params.get("search");
        }
        if (search != null && !search.isEmpty()) {
            return userRepo.getAllsUser(pageable, search);
        }
        return userRepo.getAllsUser(pageable, "");
    }

    @Override
    public List<User> getListAdviser() {
        return userRepo.getListAdviser();
    }

    @Override
    public List<User> getListUserChat(List<Integer> ids) {
        return userRepo.getListUserChat(ids);
    }

    @Override
    public boolean checkUsername(String username) {
        Optional<User> u = userRepo.getUserByUsername(username);
        if(u.isPresent())
            return true;
        return false;
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
    public boolean updateUser(User u) {
        Optional<User> user = userRepo.findById(u.getId());
        if(user.isPresent()) {
            try {
                User user1 = user.get();
                user1.setEmail(u.getEmail());
                user1.setGioitinh(u.getGioitinh());
                user1.setName(u.getName());
                user1.setNgaysinh(u.getNgaysinh());
                user1.setUserRole(u.getUserRole());
                user1.setSdt(u.getSdt());
                userRepo.save(user1);
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean addUser(Map<String, String> params, MultipartFile file) {
        try {
            if(checkUsername(params.get("username").trim()))
                return false;
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            User u = new User();
            u.setActive((byte) 1);
            u.setEmail(params.get("email"));
            u.setGioitinh(params.get("gioitinh"));
            u.setName(params.get("name"));
            u.setNgaysinh(dateFormatter.parse(params.get("ngaysinh")));
            u.setUserRole(params.get("userRole"));
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
    public boolean recycleBin(int id) {
        Optional<User> u = userRepo.findById(id);
        if(u.isPresent()){
            User user = u.get();
            user.setActive((byte) 0 );
            userRepo.save(user);
            return true;
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
