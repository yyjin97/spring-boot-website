package com.spring.springweb.repository;

import com.spring.springweb.domain.BoardVO;
import com.spring.springweb.domain.Criteria;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardRepository extends Repository<BoardVO, Integer> {

    @Query("SELECT boardVO FROM BoardVO boardVO WHERE boardVO.bno > 0")
    List<BoardVO> getList();

    @Query(value = "SELECT * FROM tbl_board board ORDER BY board.bno DESC LIMIT :start, :amount", nativeQuery = true)
    List<BoardVO> getListWithPaging(int start, int amount);

    @Query(value = "SELECT * FROM tbl_board board WHERE board.title LIKE %:keyword% ORDER BY board.bno DESC LIMIT :start, :amount",
            nativeQuery = true)
    List<BoardVO> getListByTitle(int start, int amount, String keyword);

    @Query(value = "SELECT * FROM tbl_board board WHERE board.writer LIKE %:keyword% ORDER BY board.bno DESC LIMIT :start, :amount",
            nativeQuery = true)
    List<BoardVO> getListByWriter(int start, int amount, String keyword);

    BoardVO getByBno(int bno);

    boolean existsBoardVOByBno(int bno);

    @Query("SELECT COUNT(board.bno) FROM BoardVO board")
    int getTotalCount();

    @Query("SELECT COUNT(board.bno) FROM BoardVO board WHERE board.title LIKE %:keyword%")
    int getCountByTitle(String keyword);

    @Query("SELECT COUNT(board.bno) FROM BoardVO board WHERE board.writer LIKE %:keyword%")
    int getCountByWriter(String keyword);

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

