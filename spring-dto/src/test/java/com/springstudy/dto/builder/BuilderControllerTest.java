package com.springstudy.dto.builder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BuilderController.class)
public class BuilderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void test() {

    }
}
