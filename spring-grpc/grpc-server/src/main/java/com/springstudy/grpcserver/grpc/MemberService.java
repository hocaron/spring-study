package com.springstudy.grpcserver.grpc;

import com.springstudy.grpcinterface.MemberInfoRequest;
import com.springstudy.grpcinterface.MemberInfoResponse;
import com.springstudy.grpcinterface.MemberServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@GrpcService
@RequiredArgsConstructor
public class MemberService extends MemberServiceGrpc.MemberServiceImplBase {

    private static final Map<Long, MemberInfoResponse> memberDatabase = new ConcurrentHashMap<>();

    static {
        memberDatabase.put(1L, MemberInfoResponse.newBuilder()
            .setId(1L)
            .setEmail("user123@example.com")
            .setIdentification("ID123456789")
            .setPhoneNumber("010-1234-5678")
            .build());
        memberDatabase.put(2L, MemberInfoResponse.newBuilder()
            .setId(2L)
            .setEmail("user456@example.com")
            .setIdentification("ID987654321")
            .setPhoneNumber("010-8765-4321")
            .build());
    }

    @Override
    public void getMemberInfo(MemberInfoRequest request, StreamObserver<MemberInfoResponse> responseObserver) {

        var memberInfo = memberDatabase.get(request.getId());
        var reply = MemberInfoResponse.newBuilder()
            .setId(memberInfo.getId())
            .setPhoneNumber(memberInfo.getPhoneNumber())
            .setIdentification(memberInfo.getIdentification())
            .setEmail(memberInfo.getEmail())
            .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
