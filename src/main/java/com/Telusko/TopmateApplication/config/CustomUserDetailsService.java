package com.Telusko.TopmateApplication.config;

import com.Telusko.TopmateApplication.Repo.ITopmateRepo;
import com.Telusko.TopmateApplication.model.User;
import com.Telusko.TopmateApplication.security.CustomUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ITopmateRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
    {
        // Fetch user from the database by email
        User user = userRepo.findByEmail(email);

        // Handle case when user is not found
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Wrap the user into CustomUserDetails and return
        return new CustomUserDetails(user);
    }
}
