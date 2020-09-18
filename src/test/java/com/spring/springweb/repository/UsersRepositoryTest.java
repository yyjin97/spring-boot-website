package com.spring.springweb.repository;

import com.spring.springweb.domain.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsersRepositoryTest {

    @Autowired
    UsersRepository repository;

    @Test
    public void testSave() {
        UserVO userVO = new UserVO();
        Date now = new Date();

        userVO.setUserId("xyz@abcd");
        userVO.setUserName("Jin");
        userVO.setPhoneNum("01012345678");
        userVO.setRegDate(now);
        userVO.setUpdateDate(now);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userVO.setUserPw(encoder.encode("xyz"));

        repository.save(userVO);
    }

}