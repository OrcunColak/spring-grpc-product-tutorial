package server.upload;

import com.colak.banking.AddressProofRequest;
import com.colak.banking.AddressProofResponse;
import com.colak.banking.BankServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ProcessAddressService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public StreamObserver<AddressProofRequest> uploadAddressProof(
            StreamObserver<AddressProofResponse> responseObserver) {

        return new UploadAddressProofObserver(responseObserver);
    }

}
