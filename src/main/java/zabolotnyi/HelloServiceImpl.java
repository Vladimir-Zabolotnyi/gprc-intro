package zabolotnyi;

import io.grpc.stub.StreamObserver;
import java.util.HashMap;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(
            HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        String greeting = new StringBuilder()
                .append("Hello, ")
                .append(request.getFirstName())
                .append(" ")
                .append(request.getLastName())
                .toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String greeting = new StringBuilder()
                .append("Good morning, ")
                .append(request.getFirstName())
                .append(" ")
                .append(request.getLastName())
                .toString();
        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

    responseObserver.onNext(response);
    responseObserver.onNext(response);
    responseObserver.onNext(response);
    responseObserver.onCompleted();

    }
}
