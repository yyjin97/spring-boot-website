package com.zerock.ex02.service;

import com.zerock.ex02.domain.BoardVO;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

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