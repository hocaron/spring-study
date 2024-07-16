package com.springstudy.grpcserver.grpc;

import io.grpc.*;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.stereotype.Component;

@Component
@GrpcGlobalServerInterceptor
public class MetadataInterceptor implements ServerInterceptor {
    static final Context.Key<Metadata> METADATA_KEY = Context.key("metadata");

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
        ServerCall<ReqT, RespT> call,
        Metadata headers,
        ServerCallHandler<ReqT, RespT> next) {

        // 메타데이터를 현재 컨텍스트에 저장합니다.
        Context ctx = Context.current().withValue(METADATA_KEY, headers);
        return Contexts.interceptCall(ctx, call, headers, next);
    }
}
