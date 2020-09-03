package com.spring.springweb.controller;

import com.google.gson.Gson;
import com.spring.springweb.domain.ReplyVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
class ReplyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testCreate() throws Exception{
        ReplyVO replyVO = new ReplyVO();
        replyVO.setRno(4);
        replyVO.setBno(3);
        replyVO.setReply("댓글입니다 !");
        replyVO.setReplyer("작성자");

        String jsonStr = new Gson().toJson(replyVO);

        log.info(jsonStr);

        mockMvc.perform(post("/replies/new")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonStr))
                .andExpect(status().isOk());
    }
}