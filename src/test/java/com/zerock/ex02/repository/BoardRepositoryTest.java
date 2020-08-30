package com.zerock.ex02.repository;

import com.zerock.ex02.domain.BoardVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void testGetList() {
        boardRepository.getList().forEach(System.out::println);
    }

    @Test
    public void testInsertBoardVO() {
        BoardVO boardVO = new BoardVO();

        boardVO.setBno(2);
        boardVO.setContent("content");
        boardVO.setTitle("title");
        boardVO.setWriter("writer");

        boardRepository.save(boardVO);
    }

    @Test
    public void testGetByBno() {
        int bno = 1;

        BoardVO boardVO = boardRepository.getByBno(bno);

        System.out.println(boardVO.getTitle());
    }

    @Test
    public void testDeleteByBno() {
        int bno = 3;

        System.out.println(boardRepository.deleteBoardVOByBno(bno));
    }

    @Test
    public void testUpdateByBno() {
        int bno = 1;

        System.out.println(boardRepository.updateTitleByBno(bno, "제목"));
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