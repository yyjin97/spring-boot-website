package com.spring.springweb.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class ReplyPageDTO {

    private int replyCnt;
    private List<ReplyVO> list;

    public ReplyPageDTO(int replyCnt, List<ReplyVO> list) {
        this.replyCnt = replyCnt;
        this.list = list;
    }
}
