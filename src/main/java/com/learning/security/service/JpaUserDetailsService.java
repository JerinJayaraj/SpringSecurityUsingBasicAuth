package com.learning.security.service;

import com.learning.security.repository.UserRepository;
import com.learning.security.utils.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByUsername(username);

        return user.map(SecurityUser::new).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}
