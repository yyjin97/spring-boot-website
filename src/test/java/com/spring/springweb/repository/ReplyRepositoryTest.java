package com.spring.springweb.repository;

import com.spring.springweb.domain.ReplyVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
@Log4j2
class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void testGetList() {
        log.info(replyRepository.getList(188, 2, 10));
    }

    @Test
    public void testUpdate() {
        ReplyVO replyVO = new ReplyVO();
        Date now = new Date();

        replyVO.setRno(1);
        replyVO.setUpdateDate(now);
        replyVO.setReply("첫 번째 댓글입니당");

        replyRepository.updateReply(replyVO);
    }

    @Test
    public void testDeleteByRno() {
        int rno = 1;
        log.info(replyRepository.deleteByRno(rno));
    }

    @Test
    public void testGet() {
        int rno = 1;
        log.info(replyRepository.getByRno(rno));
    }

    @Test
    public void testSave() {
        ReplyVO replyVO = new ReplyVO();
        Date date = new Date();

        replyVO.setRno(1);
        replyVO.setBno(14);
        replyVO.setReply("first reply!");
        replyVO.setReplyer("writer");
        replyVO.setReplyDate(date);
        replyVO.setUpdateDate(date);

        replyRepository.save(replyVO);
    }
}