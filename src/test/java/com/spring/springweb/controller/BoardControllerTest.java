package com.spring.springweb.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListPaging() throws Exception {
        log.info(mockMvc.perform(get("/board/list")
                .param("pageNum", "2")
                .param("amount", "50"))
                .andReturn().getModelAndView().getModelMap());
    }

    @Test
    public void testRegister() throws Exception {
        String resultPage = mockMvc.perform(post("/board/register")
                .param("title", "제목3")
                .param("content", "내용3")
                .param("writer", "user03")
        ).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testModify() throws Exception {

        String resultPage = mockMvc.perform(post("/board/modify")
                .param("bno", "2")
                .param("title", "제목1")
                .param("content", "내용1")
                .param("writer", "writer1"))
                .andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testRemove() throws Exception {
        //삭제전 데이터베이스에 게시물 번호 확인할 것
        String resultPage = mockMvc.perform(post("/board/remove")
                .param("bno", "4"))
                .andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }
}