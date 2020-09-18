package com.spring.springweb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "users")
public class UserVO {

    @Id
    @Column(name = "userid")
    @Email
    private String userId;

    @Column(name = "userpw")
    private String userPw;

    @Column(name = "username")
    private String userName;

    @Column(name = "phone_num")
    @Digits(fraction = 0, integer = 11)
    private String phoneNum;

    @Column(name = "reg_date")
    private Date regDate;

    @Column(name = "update_date")
    private Date updateDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<AuthVO> auths;

    @Override
    public String toString() {
        return "UserVO{" +
                "userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", auths=" + auths +
                '}';
    }
}
