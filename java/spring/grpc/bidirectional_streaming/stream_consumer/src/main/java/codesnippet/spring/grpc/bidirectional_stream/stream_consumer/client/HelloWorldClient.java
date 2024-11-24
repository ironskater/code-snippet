package codesnippet.spring.grpc.bidirectional_stream.stream_consumer.client;

import codesnippet.spring.grpc.bidirectional_stream.stream_common.stubs.HelloWorldRequest;
import codesnippet.spring.grpc.bidirectional_stream.stream_common.stubs.HelloWorldResponse;
import codesnippet.spring.grpc.bidirectional_stream.stream_common.stubs.HelloWorldServiceGrpc;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import java.util.Random;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloWorldClient {

    /**
     * Constructor injection not possible!!
     * The gRPC stub proxy isn't ready during the regular Spring dependency injection phase when constructors are called
     * The @GrpcClient annotation needs to do its own special post-processing to create the connection
     *
     * Do not use in conjunction with @Autowired or @Inject
     * (see https://github.com/grpc-ecosystem/grpc-spring)
     */
    @GrpcClient("hello")
    private HelloWorldServiceGrpc.HelloWorldServiceStub serviceStub;

    @PostMapping("/v1/requests/send")
    public ResponseEntity<Void> sendRequests() {

        // prepare the response callback
        final StreamObserver<HelloWorldResponse> responseObserver = new StreamObserver<HelloWorldResponse>() {

            @Override
            public void onNext(HelloWorldResponse helloWorldResponse) {
                // 當收到新數據時被調用
                log.info("Hello World Response: {}", helloWorldResponse.getGreeting());
            }

            @Override
            public void onError(Throwable throwable) {
                // 當發生錯誤時被調用
                log.error("Error occured", throwable);
            }

            @Override
            public void onCompleted() {
                // 當數據流結束時被調用
                log.info("Hello World request finished");
            }
        };

        /**
         * 被觀察者(subject): StreamObserver<HelloWorldRequest> request
         * 觀察者(observer): responseObserver
         */
        final StreamObserver<HelloWorldRequest> request = serviceStub.sayHello(responseObserver);

        // send multiple requests (streaming)
        Stream.of("Tom", "Julia", "Baeldung", "", "Ralf")
            .map(HelloWorldRequest.newBuilder()::setName)
            .map(builder -> builder.setAge(
                    Int32Value.newBuilder()
                        .setValue(new Random().nextInt(100))
                        .build()
                )
            ).map(HelloWorldRequest.Builder::build)
            .forEach(req -> {
                log.info("Sending request: {}", req);
                request.onNext(req); // 這裡視作client主動發送請求
            });

        request.onCompleted();

        return ResponseEntity.ok().build();
    }
}
