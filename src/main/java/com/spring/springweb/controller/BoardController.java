package com.spring.springweb.controller;

import com.spring.springweb.domain.BoardVO;
import com.spring.springweb.domain.Criteria;
import com.spring.springweb.domain.PageDTO;
import com.spring.springweb.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/*")
@Log4j2
public class BoardController {

    private final BoardService boardService;

    BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping({"/get", "/modify"})
    public void get(int bno, @ModelAttribute("cri") Criteria cri, Model model) {
        log.info("/get or modify #" + bno);
        model.addAttribute("board", boardService.get(bno));
    }

//    @GetMapping("/list")
//    public void list(Model model) {
//        log.info("list");
//        model.addAttribute("list", boardService.getList());
//    }

    @GetMapping("/list")
    public void list(Criteria cri, Model model) {
        log.info("list: " + cri);

        if(cri.getType() == 1) {
            model.addAttribute("list", boardService.getListByTitle(cri));
            model.addAttribute("pageMaker", new PageDTO(cri, boardService.getCountByTitle(cri.getKeyword())));
        }
        else if(cri.getType() == 2){
            model.addAttribute("list", boardService.getListByWriter(cri));
            model.addAttribute("pageMaker", new PageDTO(cri, boardService.getCountByWriter(cri.getKeyword())));
        }
        else {
            model.addAttribute("list", boardService.getList(cri));
            model.addAttribute("pageMaker", new PageDTO(cri, boardService.getTotalCount()));
        }
    }

    @GetMapping("/register")
    public void register() { }

    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {
        log.info("register: " + board);

        boardService.register(board);

        rttr.addFlashAttribute("result", "success");

        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
        log.info("modify post: " + board);

        if(boardService.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }
        rttr.addAttribute("bno", board.getBno());
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/get";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") int bno,@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
        log.info("remove: " + bno);

        if(boardService.remove(bno)) {
            rttr.addFlashAttribute("result", bno);
        }
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list";
    }
}
