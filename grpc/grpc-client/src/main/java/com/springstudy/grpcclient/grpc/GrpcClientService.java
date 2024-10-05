package com.springstudy.grpcclient.grpc;

import com.springstudy.grpcinterface.MemberInfoRequest;
import com.springstudy.grpcinterface.MemberServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {

    @GrpcClient("memberService")
    private MemberServiceGrpc.MemberServiceBlockingStub memberBlockingStub;

    public String getMemberInfo(final long memberId) {
        var request = MemberInfoRequest.newBuilder().setId(memberId).build();
        var response = memberBlockingStub.getMemberInfo(request);
        return response.getEmail();
    }
}
