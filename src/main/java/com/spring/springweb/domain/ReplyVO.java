package com.spring.springweb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter @Setter
@Entity
@Table(name = "tbl_reply")
public class ReplyVO {

    @Id
    private Integer rno;

    private Integer bno;

    private String reply;

    private String replyer;

    @Column(name = "replydate")
    private Date replyDate;

    @Column(name = "updatedate")
    private Date updateDate;

    @Override
    public String toString() {
        return "ReplyVO{" +
                "rno=" + rno +
                ", bno=" + bno +
                ", reply='" + reply + '\'' +
                ", replyer='" + replyer + '\'' +
                ", replyDate=" + replyDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
