package com.spring.springweb.repository;

import com.spring.springweb.domain.ReplyVO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReplyRepository extends Repository<ReplyVO, Integer> {

    @Query(value = "SELECT /*+ INDEX(tbl_reply, idx_reply) */ * FROM tbl_reply reply WHERE reply.bno = :bno ORDER BY reply.rno DESC LIMIT :start, :amount", nativeQuery = true)
    List<ReplyVO> getList(int bno, int start, int amount);

    ReplyVO getByRno(int rno);

    boolean existsByRno(int rno);

    @Query("SELECT COUNT(reply.rno) FROM ReplyVO reply WHERE reply.bno = :bno")
    int getCntByBno(int bno);

    @Query("UPDATE ReplyVO SET reply = :#{#replyVO.reply}, updateDate = :#{#replyVO.updateDate} WHERE rno = :#{#replyVO.rno}")
    @Transactional
    @Modifying
    int updateReply(ReplyVO replyVO);

    @Transactional
    @Modifying
    int deleteByRno(int rno);

    void save(ReplyVO replyVO);
}
