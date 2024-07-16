package com.springstudy.grpcclient.grpc;

import io.grpc.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.stereotype.Component;

@Component
@GrpcGlobalClientInterceptor
@RequiredArgsConstructor
public class MetadataClientInterceptor implements ClientInterceptor {

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
        MethodDescriptor<ReqT, RespT> method,
        CallOptions callOptions,
        Channel next) {

        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                // 메타데이터를 설정합니다.
                headers.put(Metadata.Key.of("Header1", Metadata.ASCII_STRING_MARSHALLER), "Value1");
                headers.put(Metadata.Key.of("Header2", Metadata.ASCII_STRING_MARSHALLER), "Value2");
                headers.put(Metadata.Key.of("Header3", Metadata.ASCII_STRING_MARSHALLER), "Value3");
                headers.put(Metadata.Key.of("Header4", Metadata.ASCII_STRING_MARSHALLER), "Value4");
                headers.put(Metadata.Key.of("Header5", Metadata.ASCII_STRING_MARSHALLER), "Value5");
                headers.put(Metadata.Key.of("Header6", Metadata.ASCII_STRING_MARSHALLER), "Value6");
                headers.put(Metadata.Key.of("Header7", Metadata.ASCII_STRING_MARSHALLER), "Value7");
                headers.put(Metadata.Key.of("Header8", Metadata.ASCII_STRING_MARSHALLER), "Value8");
                super.start(responseListener, headers);
            }
        };
    }
}
