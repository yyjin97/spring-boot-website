package com.spring.springweb.service;

import com.spring.springweb.domain.Criteria;
import com.spring.springweb.domain.ReplyPageDTO;
import com.spring.springweb.domain.ReplyVO;

import java.util.List;

public interface ReplyService {

    int register(ReplyVO replyVO);

    ReplyVO get(int rno);

    int modify(ReplyVO replyVO);

    boolean remove(int rno);

    List<ReplyVO> getList(Criteria cri, int bno);

    ReplyPageDTO getListPage(Criteria cri, int bno);
}
