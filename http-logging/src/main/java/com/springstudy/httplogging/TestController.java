package com.springstudy.httplogging;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/get")
    public String getTest(@RequestParam Long id) {
        return "This is get Logging, ID is " + id;
    }
}
