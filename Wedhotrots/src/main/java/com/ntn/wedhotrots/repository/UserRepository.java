package com.ntn.wedhotrots.repository;

import com.ntn.wedhotrots.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> getUserByUsername(String username);
    @Query("SELECT u FROM User u Where u.active=1 AND u.name LIKE %:search% AND u.userRole <> 'ROLE_ADMIN' ")
    List<User> getAllsUser(Pageable pageable, String search);

}
