package com.spring.springweb.service;

import com.spring.springweb.domain.BoardVO;

import java.util.List;

public interface BoardService {

    void register(BoardVO board);

    BoardVO get(int bno);

    boolean modify(BoardVO board);

    boolean remove(int bno);

    List<BoardVO> getList();
}
