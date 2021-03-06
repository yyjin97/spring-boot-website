package com.spring.springweb.controller;

import com.spring.springweb.domain.Criteria;
import com.spring.springweb.domain.ReplyPageDTO;
import com.spring.springweb.domain.ReplyVO;
import com.spring.springweb.service.ReplyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/replies/")
@Log4j2
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/new", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
    public ResponseEntity<String> create(@RequestBody ReplyVO replyVO) {
          log.info("ReplyVO: " + replyVO);

          int insertCount = replyService.register(replyVO);

          log.info("Reply Insert Count: " + insertCount);

          return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/pages/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") int bno) {
        log.info("getList..................");

        Criteria cri = new Criteria(page, 10);
        log.info(cri);

        return new ResponseEntity<>(replyService.getListPage(cri, bno), HttpStatus.OK);
    }

    @GetMapping(value = "/{rno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReplyVO> get(@PathVariable("rno") int rno) {
        log.info("get: " + rno);

        return new ResponseEntity<>(replyService.get(rno), HttpStatus.OK);
    }

    @PreAuthorize("principal.username == #vo.replyer")
    @DeleteMapping(value = "/{rno}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@RequestBody ReplyVO vo, @PathVariable("rno") int rno) {
        log.info("remove: " + rno);
        log.info("replyer: " + vo.getReplyer());

        return replyService.remove(rno) ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PreAuthorize("principal.username == #replyVO.replyer")
    @RequestMapping(value = "/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH },
                    consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody ReplyVO replyVO,@PathVariable("rno") int rno) {

        if(replyVO.getRno() != rno) {
            log.info("modify error!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        log.info("modify: " + replyVO);

        return replyService.modify(replyVO) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
