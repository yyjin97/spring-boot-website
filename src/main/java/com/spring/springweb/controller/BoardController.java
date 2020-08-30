package com.spring.springweb.controller;

import com.spring.springweb.domain.BoardVO;
import com.spring.springweb.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/*")
@Log4j2
public class BoardController {

    private final BoardService boardService;

    BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/get")
    public void get(int bno, Model model) {
        log.info("/get");
        model.addAttribute("board", boardService.get(bno));
    }

    @GetMapping("/list")
    public void list(Model model) {
        log.info("list");
        model.addAttribute("list", boardService.getList());
    }

    @GetMapping("/register")
    public void register() { }

    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {
        log.info("register: " + board);

        boardService.register(board);

        rttr.addFlashAttribute("result", board.getBno());

        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modify(BoardVO board, RedirectAttributes rttr) {
        log.info("modify:" + board);

        if(boardService.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) {
        log.info("remove: " + bno);

        if(boardService.remove(bno)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }
}