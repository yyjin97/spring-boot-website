package com.spring.springweb.security;

import com.spring.springweb.domain.UserVO;
import com.spring.springweb.repository.UsersRepository;
import com.spring.springweb.security.domain.CustomUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        log.warn("Load User By UserName: " + userName);

        UserVO userVO = repository.getByUserId(userName);

        return userVO == null ? null : new CustomUser(userVO);
    }
}
