package com.spring.springweb.service;

import com.spring.springweb.domain.ReplyVO;
import com.spring.springweb.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
        log.info("register reply #", replyVO.getRno());

        replyRepository.save(replyVO);
        return 0;
    }

    @Override
    public ReplyVO get(int rno) {
        log.info("get reply #"+ rno);

        return replyRepository.getByRno(rno);
    }

    @Override
    public int modify(ReplyVO replyVO) {

        if(replyRepository.existsByRno(replyVO.getRno())) {
            log.info("#" + replyVO.getRno() + " is already existed");
            return -1;
        }

        log.info("modify reply #" + replyVO.getRno());

        return replyRepository.updateReply(replyVO);
    }

    @Override
    public boolean remove(int rno) {

        if(!replyRepository.existsByRno(rno)) {
            log.info("No #" + rno + " reply exist");
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
