package com.springstudy.grpcclient.grpc;

import com.springstudy.grpcdemo.GreeterGrpc;
import com.springstudy.grpcdemo.HelloReply;
import com.springstudy.grpcdemo.HelloRequest;
import com.springstudy.grpcdemo.MemberInfoRequest;
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

    public String getMemberInfo(final long memberId) {
        var request = MemberInfoRequest.newBuilder().setId(memberId).build();
        var response = greeterBlockingStub.getMemberInfo(request);
        return response.getEmail();
    }
}
