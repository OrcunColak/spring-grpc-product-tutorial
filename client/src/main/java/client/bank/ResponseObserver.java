package client.bank;

import com.colak.banking.AddressProofResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseObserver implements StreamObserver<AddressProofResponse> {
    private final Object lock = new Object();
    private boolean completed = false;

    @Override
    public void onNext(AddressProofResponse response) {
        log.info("Server response: " + response.getMessage());
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error occurred: ", throwable);
        synchronized (lock) {
            completed = true;
            lock.notifyAll();
        }
    }

    @Override
    public void onCompleted() {
        log.info("File uploaded successfully!");
        synchronized (lock) {
            completed = true;
            lock.notifyAll();
        }
    }

    public void awaitCompletion() {
        synchronized (lock) {
            while (!completed) {
                try {
                    lock.wait();
                } catch (InterruptedException exception) {
                    log.error("InterruptedException occurred: ", exception);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
