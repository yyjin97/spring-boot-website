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
@Table(name = "tbl_board")
public class BoardVO {

    @Id
    private int bno;

    private String title;

    private String content;

    private String writer;

    @Column(name = "regdate")
    private Date regDate;

    @Column(name = "updatedate")
    private Date updateDate;

    @Override
    public String toString() {
        return "BoardVO{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regdate=" + regDate +
                ", updatedate=" + updateDate +
                '}';
    }
}
