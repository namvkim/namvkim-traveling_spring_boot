package com.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.UserEntity;
import com.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    // trả về bản ghi user dựa vào user name
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	
        UserEntity user = userRepository.findByUsername(username)
                	.orElseThrow(() -> 
                        new UsernameNotFoundException("User Not Found with -> username or email : " + username)
        );

        // chuyển hướng qua class UserPrinciple
        return UserPrinciple.build(user);
    }
}
