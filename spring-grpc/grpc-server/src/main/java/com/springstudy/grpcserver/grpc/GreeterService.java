package com.springstudy.grpcserver.grpc;

import com.springstudy.grpcdemo.*;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@GrpcService
@RequiredArgsConstructor
public class GreeterService extends GreeterGrpc.GreeterImplBase {

    private static final Map<Long, MemberInfoReply> memberDatabase = new ConcurrentHashMap<>();
    public static final Context.Key<Metadata> METADATA_KEY = Context.key("metadata");
    public static final Metadata.Key<String> key1 = Metadata.Key.of("Header1", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> key2 = Metadata.Key.of("Header2", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> key3 = Metadata.Key.of("Header3", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> key4 = Metadata.Key.of("Header4", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> key5 = Metadata.Key.of("Header5", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> key6 = Metadata.Key.of("Header6", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> key7 = Metadata.Key.of("Header7", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> key8 = Metadata.Key.of("Header8", Metadata.ASCII_STRING_MARSHALLER);
    public static final Metadata.Key<String> key9 = Metadata.Key.of("Header9", Metadata.ASCII_STRING_MARSHALLER);

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
        Metadata metadata = MetadataInterceptor.METADATA_KEY.get(Context.current());
        String header1 = metadata.get(key1);
        String header2 = metadata.get(key2);
        String header3 = metadata.get(key3);
        String header4 = metadata.get(key4);
        String header5 = metadata.get(key5);
        String header6 = metadata.get(key6);
        String header7 = metadata.get(key7);
        String header8 = metadata.get(key8);
        String header9 = metadata.get(key9);

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
