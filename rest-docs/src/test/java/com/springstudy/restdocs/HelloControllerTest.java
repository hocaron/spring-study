package com.springstudy.restdocs;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void hello() throws Exception {
        // given
        String name = "hocaron";

        // when
        ResultActions result = this.mockMvc.perform(
            post("/")
                .content(objectMapper.writeValueAsString(name))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        //return
        result.andExpect(status().isOk())
            .andDo(document("hello", // (4)
                ApiDocumentUtil.getDocumentRequest(),
                ApiDocumentUtil.getDocumentResponse(),
                requestFields(
                    fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
                ),
                responseFields(
                    fieldWithPath("data").type(JsonFieldType.STRING).description("반환되는 문장")
                )
            ));
    }
}