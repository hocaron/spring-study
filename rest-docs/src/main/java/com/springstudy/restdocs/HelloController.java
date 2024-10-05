package com.springstudy.restdocs;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PostMapping("")
    public ApiResponse<String> hello(@RequestBody String name) {
        return ApiResponse.success(name + " Docs는 중요하지");
    }

}
