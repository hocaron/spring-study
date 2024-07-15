package com.springstudy.grpcclient.grpc;

import com.example.grpcdemo.GreeterGrpc;
import com.example.grpcdemo.HelloReply;
import com.example.grpcdemo.HelloRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {

    @GrpcClient("greeterService")
    private GreeterGrpc.GreeterBlockingStub greeterBlockingStub;

    public String sayHello(final String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response = greeterBlockingStub.sayHello(request);
        return response.getMessage();
    }
}
