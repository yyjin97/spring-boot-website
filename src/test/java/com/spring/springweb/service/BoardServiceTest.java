package com.spring.springweb.service;

import com.spring.springweb.domain.BoardVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    public void testRegister() {
        BoardVO boardVO = new BoardVO();

        boardVO.setBno(3);
        boardVO.setTitle("제목");
        boardVO.setContent("내용");
        boardVO.setWriter("작가");

        boardService.register(boardVO);
    }
}