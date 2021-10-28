package zabolotnyi;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class GrpcClient {
    // for 1 request -> 1 response
//    public static void main(String[] args) {
//        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
//                .usePlaintext()
//                .build();
//
//        HelloServiceGrpc.HelloServiceBlockingStub stub
//                = HelloServiceGrpc.newBlockingStub(channel);
//
//        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
//                .setFirstName("Vova")
//                .setLastName("gRPC")
//                .build());
//        System.out.println(helloResponse);
//        channel.shutdown();
//    }

    public static void main(String[] args) {
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext(true)
                .build();
        HelloServiceGrpc.HelloServiceStub stub = HelloServiceGrpc.newStub(channel);

        HelloRequest request = HelloRequest.newBuilder()
                .setFirstName("Vova")
                .setLastName("gRPC")
                .build();

        stub.greeting(request, new StreamObserver<HelloResponse>() {
            @Override
            public void onNext(HelloResponse response) {
                System.out.println(response);
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {
                channel.shutdown();
            }
        });
    }
}
