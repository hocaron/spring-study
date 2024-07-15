package com.springstudy.grpcclient.grpc;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GrpcClientController {

    private final GrpcClientService grpcClientService;

    @GetMapping("/say-hello")
    public String sayHello(@RequestParam String name) {
        return grpcClientService.sayHello(name);
    }

    @GetMapping("/member/{memberId}")
    public String sayHello(@PathVariable long memberId) {
        return grpcClientService.getMemberInfo(memberId);
    }
}
