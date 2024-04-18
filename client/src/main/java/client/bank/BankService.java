package client.bank;

import com.colak.banking.AddressProofRequest;
import com.colak.banking.BankServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BankService {

    public void uploadAddressProof() {

        String accountHolderName = "javainuse";
        String accountNumber = "account5";
        // Create a channel to connect to the gRPC server
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();

        // Create a gRPC stub
        BankServiceGrpc.BankServiceStub stub = BankServiceGrpc.newStub(channel);

        // Create an instance of the ResponseObserver class to handle the response from
        // the server
        ResponseObserver responseObserver = new ResponseObserver();

        // Create a StreamObserver to send the request to the server
        StreamObserver<AddressProofRequest> requestObserver = stub.uploadAddressProof(responseObserver);

        for (int index = 0; index < 5; index++) {
            byte[] buffer = new byte[]{(byte) index};

            AddressProofRequest request = AddressProofRequest.newBuilder()
                    .setAccountHolderName(accountHolderName)
                    .setAccountNumber(accountNumber)
                    .setPdfFile(com.google.protobuf.ByteString.copyFrom(buffer, 0, buffer.length))
                    .build();

            // Send the request to the server
            requestObserver.onNext(request);
        }

        // Mark the client-side stream as complete
        requestObserver.onCompleted();

        // Wait for the response from the server
        responseObserver.awaitCompletion();

        // Close the channel after the upload is completed
        channel.shutdown();
    }
}
