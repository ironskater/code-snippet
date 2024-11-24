package codesnippet.spring.grpc.bidirectional_stream.stream_producer.controller;

import codesnippet.spring.grpc.bidirectional_stream.stream_common.stubs.HelloWorldRequest;
import codesnippet.spring.grpc.bidirectional_stream.stream_common.stubs.HelloWorldResponse;
import codesnippet.spring.grpc.bidirectional_stream.stream_common.stubs.HelloWorldServiceGrpc.HelloWorldServiceImplBase;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class HelloWorldController extends HelloWorldServiceImplBase {

    private static final String GREETING_MSG = "Hello, let's say hello to %s, and age is %s";

     private HelloWorldResponse sayHello(HelloWorldRequest request) {

        log.info("Received request: {}", request);

        String greetingMsg = String.format(GREETING_MSG, request.getName(), request.getAge());
        Timestamp timestamp = Timestamp.newBuilder()
            .setSeconds(System.currentTimeMillis() / 1000)
            .build();

        return HelloWorldResponse.newBuilder()
            .setGreeting(greetingMsg)
            .setTimestamp(timestamp)
            .build();
    }

    @Override
    public StreamObserver<HelloWorldRequest> sayHello(
        StreamObserver<HelloWorldResponse> responseObserver) {

        return StreamObserverUtility.proxyStream(
            responseObserver,
            this::sayHello);
    }
}
