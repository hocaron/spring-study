package com.springstudy.grpcserver.grpc;

import com.springstudy.grpcdemo.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@GrpcService
@RequiredArgsConstructor
public class GreeterService extends GreeterGrpc.GreeterImplBase {

    private static final Map<Long, MemberInfoReply> memberDatabase = new ConcurrentHashMap<>();

    static {
        memberDatabase.put(1L, MemberInfoReply.newBuilder()
            .setId(1L)
            .setEmail("user123@example.com")
            .setIdentification("ID123456789")
            .setPhoneNumber("010-1234-5678")
            .build());
        memberDatabase.put(2L, MemberInfoReply.newBuilder()
            .setId(2L)
            .setEmail("user456@example.com")
            .setIdentification("ID987654321")
            .setPhoneNumber("010-8765-4321")
            .build());
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        var reply = HelloReply.newBuilder().setMessage("Hello ==> " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void getMemberInfo(MemberInfoRequest request, StreamObserver<MemberInfoReply> responseObserver) {

        var memberInfo = memberDatabase.get(request.getId());
        var reply = MemberInfoReply.newBuilder()
            .setId(memberInfo.getId())
            .setPhoneNumber(memberInfo.getPhoneNumber())
            .setIdentification(memberInfo.getIdentification())
            .setEmail(memberInfo.getEmail())
            .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
