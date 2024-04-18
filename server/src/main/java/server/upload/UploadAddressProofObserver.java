package server.upload;

import com.colak.banking.AddressProofRequest;
import com.colak.banking.AddressProofResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
public class UploadAddressProofObserver implements StreamObserver<AddressProofRequest> {

    private String accountHolderName;
    private String accountNumber;
    private final ByteArrayOutputStream pdfBytes = new ByteArrayOutputStream();
    private final StreamObserver<AddressProofResponse> responseObserver;

    public UploadAddressProofObserver(StreamObserver<AddressProofResponse> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(AddressProofRequest request) {
        // Retrieve data from each request chunk
        if (request.hasField(AddressProofRequest.getDescriptor().findFieldByName("account_holder_name"))) {
            accountHolderName = request.getAccountHolderName();
        }
        if (request.hasField(AddressProofRequest.getDescriptor().findFieldByName("account_number"))) {
            accountNumber = request.getAccountNumber();
        }
        if (request.hasField(AddressProofRequest.getDescriptor().findFieldByName("pdf_file"))) {
            try {
                // Append the received bytes from the PDF file
                pdfBytes.write(request.getPdfFile().toByteArray());
            } catch (IOException exception) {
                log.error("Exception :", exception);
            }
        }
    }

    @Override
    public void onError(Throwable t) {
        // Handle any errors that occur during streaming
    }

    @Override
    public void onCompleted() {
        log.info("Bytes : {}", pdfBytes.toByteArray());

        // Create and send a response message
        AddressProofResponse response = AddressProofResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Address proof document uploaded successfully for " + accountHolderName
                            + " having account number " + accountNumber)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
