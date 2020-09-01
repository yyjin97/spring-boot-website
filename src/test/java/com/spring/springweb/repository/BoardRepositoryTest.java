package com.spring.springweb.repository;

import com.spring.springweb.domain.BoardVO;
import com.spring.springweb.domain.Criteria;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void testPaging() {
        Criteria cri = new Criteria();
        int amount = cri.getAmount();
        int start = (cri.getPageNum() - 1) * amount;

        List<BoardVO> list = boardRepository.getList(cri.getType(), cri.getKeyword(), start, amount);

        list.forEach(board -> log.info(board));
    }

    @Test
    public void testTotalBoard() {
        log.info(boardRepository.getTotalCount(2, "세"));
    }

    @Test
    public void testInsertBoardVO() {
        BoardVO boardVO = new BoardVO();
        Date now = new Date();

        boardVO.setBno(2);
        boardVO.setContent("content");
        boardVO.setTitle("title");
        boardVO.setWriter("writer");
        boardVO.setRegDate(now);
        boardVO.setUpdateDate(now);

        boardRepository.save(boardVO);
    }

    @Test
    public void testDeleteByBno() {
        int bno = 3;

        System.out.println(boardRepository.deleteBoardVOByBno(bno));
    }

    @Test
    public void testUpdateBoardVO() {
        BoardVO boardVO = new BoardVO();

        boardVO.setBno(3);
        boardVO.setTitle("제목3");
        boardVO.setContent("내용3");
        boardVO.setWriter("작가3");

        if(boardRepository.existsBoardVOByBno(boardVO.getBno())) {
            boardRepository.save(boardVO);
        }
    }
}