package com.ntn.wedhotrots.components;

import com.ntn.wedhotrots.pojo.User;
import com.ntn.wedhotrots.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    private final static String TOKEN_HEADER = "authorization";
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String authToken = httpRequest.getHeader(TOKEN_HEADER);
            if(authToken!=null){
                if (jwtService.validateToken(authToken)) {
                    String username = jwtService.extractUsername(authToken);
                    Optional<User> u = userService.getUserByUsername(username);
                    User user = u.get();
                    if (user != null) {
                        boolean enabled = true;
                        boolean accountNonExpired = true;
                        boolean credentialsNonExpired = true;
                        boolean accountNonLocked = true;

                        Set<GrantedAuthority> authorities = new HashSet<>();
                        authorities.add(new SimpleGrantedAuthority(user.getUserRole()));

                        UserDetails userDetail = new org.springframework.security.core.userdetails.User(username, user.getPassword(), enabled, accountNonExpired,
                                credentialsNonExpired, accountNonLocked, authorities);
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
                                null, userDetail.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(JwtTokenAuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
