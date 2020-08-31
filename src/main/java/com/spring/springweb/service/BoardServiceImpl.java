package com.spring.springweb.service;

import com.spring.springweb.domain.BoardVO;
import com.spring.springweb.domain.Criteria;
import com.spring.springweb.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public void register(BoardVO board) {
        Date now = new Date();

        if(board == null) {
            log.info("register input cannot null");
            return;
        }

        if(boardRepository.existsBoardVOByBno(board.getBno())) {
            log.info("#"+board.getBno()+" board already exists");
            return;
        }

        board.setRegDate(now);
        board.setUpdateDate(now);

        log.info("register boardVO");
        boardRepository.save(board);
    }

    @Override
    public BoardVO get(int bno) {

        if(bno < 0) {
            log.info("get bno value error");
            return null;
        }

        log.info("get boardVO by bno");
        return boardRepository.getByBno(bno);
    }

    @Override
    public boolean modify(BoardVO board) {
        Date now = new Date();

        if(board == null) {
            log.info("modify function input is null");
            return false;
        }

        if(!boardRepository.existsBoardVOByBno(board.getBno())) {
            log.info("cannot find bno in db");
            return false;
        }

        log.info("modify boardVO");
        board.setUpdateDate(now);
        boardRepository.updateBoardVO(board);
        return true;
    }

    @Override
    public boolean remove(int bno) {
        log.info("remove boardVO: " + bno);

        return boardRepository.deleteBoardVOByBno(bno) == 1;
    }

//    @Override
//    public List<BoardVO> getList() {
//        log.info("get boardVO list");
//
//        return boardRepository.getList();
//    }

    @Override
    public List<BoardVO> getList(Criteria cri) {
        int amount = cri.getAmount();
        int start = (cri.getPageNum() - 1) * amount;

        log.info("get List with criteria: " + cri);

        return boardRepository.getListWithPaging(start, amount);
    }
}
