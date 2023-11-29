package com.springstudy.feign.date;

import feign.RequestLine;
import feign.Response;

public interface JsonTestFeignClientBuilder {

    @RequestLine("GET")
    Response getDate();
}
