package com.spring.springweb.service;

import com.spring.springweb.domain.BoardVO;
import com.spring.springweb.domain.Criteria;

import java.util.List;

public interface BoardService {

    void register(BoardVO board);

    BoardVO get(int bno);

    boolean modify(BoardVO board);

    boolean remove(int bno);

    //List<BoardVO> getList();
    List<BoardVO> getList(Criteria cri);
    List<BoardVO> getListByTitle(Criteria cri);
    List<BoardVO> getListByWriter(Criteria cri);

    int getTotalCount();
    int getCountByTitle(String keyword);
    int getCountByWriter(String keyword);
}
