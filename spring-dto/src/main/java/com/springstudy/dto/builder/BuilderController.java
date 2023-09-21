package com.springstudy.dto.builder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/builder")
public class BuilderController {

    @PostMapping
    public void builderTest(@RequestBody @Valid MemberRequest request) {
        var r = request;
        r = request;
    }
}
