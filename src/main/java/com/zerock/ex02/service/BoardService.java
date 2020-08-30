package com.zerock.ex02.service;

import com.zerock.ex02.domain.BoardVO;

import java.util.List;

public interface BoardService {

    void register(BoardVO board);

    BoardVO get(int bno);

    boolean modify(BoardVO board);

    boolean remove(int bno);

    List<BoardVO> getList();
}
