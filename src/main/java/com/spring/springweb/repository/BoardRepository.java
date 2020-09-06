package com.spring.springweb.repository;

import com.spring.springweb.domain.BoardVO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardRepository extends Repository<BoardVO, Integer> {

    @Query(value = "SELECT * FROM tbl_board board WHERE CASE WHEN :type = 1 THEN (board.title LIKE %:keyword%) ELSE (board.writer LIKE %:keyword%) END " +
            "ORDER BY board.bno DESC LIMIT :start, :amount", nativeQuery = true)
    List<BoardVO> getList(int type, String keyword, int start , int amount);

    BoardVO getByBno(int bno);

    boolean existsBoardVOByBno(int bno);

    @Query(value = "SELECT COUNT(board.bno) FROM tbl_board board " +
            "WHERE CASE WHEN :type = 1 THEN (board.title LIKE %:keyword%) ELSE (board.writer LIKE %:keyword%) END", nativeQuery = true)
    int getTotalCount(int type, String keyword);

    @Query("UPDATE BoardVO board SET board.replyCnt = board.replyCnt + :amount where board.bno = :bno")
    @Modifying
    void updateReplyCnt(int bno, int amount);

    @Query("UPDATE BoardVO SET title=:#{#boardVO.title}, content=:#{#boardVO.content}, writer=:#{#boardVO.writer}, updateDate=:#{#boardVO.updateDate} WHERE bno = :#{#boardVO.bno}")
    @Transactional
    @Modifying
    int updateBoardVO(BoardVO boardVO);

    @Transactional
    @Modifying
    int deleteBoardVOByBno(int bno);

    void save(BoardVO boardVO);
}

