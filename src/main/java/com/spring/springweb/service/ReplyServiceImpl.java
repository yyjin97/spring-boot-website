package com.spring.springweb.service;

import com.spring.springweb.domain.Criteria;
import com.spring.springweb.domain.ReplyVO;
import com.spring.springweb.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Override
    public int register(ReplyVO replyVO) {
        Date now = new Date();

        if(replyVO == null) {
            log.info("register input cannot null");
            return 0;
        }

        if(replyRepository.existsByRno(replyVO.getRno())) {
            log.info("#" + replyVO.getRno() + " reply already exists");
            return 0;
        }

        replyVO.setReplyDate(now);
        replyVO.setUpdateDate(now);

        log.info("register reply #" + replyVO.getRno());

        replyRepository.save(replyVO);

        return 1;
    }

    @Override
    public ReplyVO get(int rno) {
        log.info("get reply #"+ rno);

        return replyRepository.getByRno(rno);
    }

    @Override
    public int modify(ReplyVO replyVO) {
        Date now = new Date();

        if(!replyRepository.existsByRno(replyVO.getRno())) {
            log.info("No #" + replyVO.getRno() + " reply exist");
            return 0;
        }

        log.info("modify reply #" + replyVO.getRno());
        replyVO.setUpdateDate(now);
        return replyRepository.updateReply(replyVO);
    }

    @Override
    public boolean remove(int rno) {

        if(!replyRepository.existsByRno(rno)) {
            log.info("No #" + rno + " reply exist");
            return false;
        }

        log.info("remove reply #" + rno);
        return replyRepository.deleteByRno(rno) == 1;
    }

    @Override
    public List<ReplyVO> getList(int bno) {
        log.info("get reply list of a board #" + bno);

        return replyRepository.getList(bno);
    }
}
