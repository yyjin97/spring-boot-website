package com.spring.springweb.repository;

import com.spring.springweb.domain.UserVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface UsersRepository extends Repository<UserVO, Integer> {

    @Query("SELECT user FROM UserVO user left join fetch user.auths WHERE user.userId = :userId")
    UserVO getByUserId(String userId);

    void save(UserVO userVO);
}
