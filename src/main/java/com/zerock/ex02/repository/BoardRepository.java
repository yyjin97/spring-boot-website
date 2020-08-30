package com.zerock.ex02.repository;

import com.zerock.ex02.domain.BoardVO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BoardRepository extends Repository<BoardVO, Integer> {

    @Query("SELECT boardVO FROM BoardVO boardVO WHERE boardVO.bno > 0")
    List<BoardVO> getList();

    BoardVO getByBno(int bno);

    boolean existsBoardVOByBno(int bno);

    @Query("UPDATE BoardVO SET title=:#{#boardVO.title}, content=:#{#boardVO.content}, writer=:#{#boardVO.writer}, updateDate=:#{#boardVO.updateDate} WHERE bno = :#{#boardVO.bno}")
    @Transactional
    @Modifying
    int updateBoardVO(BoardVO boardVO);

    @Query("UPDATE BoardVO boardVO SET boardVO.title = :title WHERE boardVO.bno = :bno")
    @Transactional
    @Modifying
    int updateTitleByBno(int bno, String title);

    @Transactional
    @Modifying
    int deleteBoardVOByBno(int bno);

    void save(BoardVO boardVO);
}

