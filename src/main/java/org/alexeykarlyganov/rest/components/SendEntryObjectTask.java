package org.alexeykarlyganov.rest.components;

import lombok.extern.slf4j.Slf4j;
import org.alexeykarlyganov.rest.models.Entry;
import org.alexeykarlyganov.rest.services.entry.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Random;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class SendEntryObjectTask {
    private final EntryService entryService;

    @Autowired
    public SendEntryObjectTask(EntryService entryService) {
        this.entryService = entryService;
    }

    @Scheduled(fixedRateString = "3500")
    public void sendEntryObjectToKafka() throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<String, Entry>> future = entryService.send(new Entry(String.valueOf(
                new Random().nextFloat()), String.valueOf(new Random().nextFloat()))
        );

        future.addCallback(new ListenableFutureCallback<SendResult<String, Entry>>() {

            @Override
            public void onSuccess(SendResult<String, Entry> result) {
                log.info("### PRODUCED ###\nTOPIC: {}\nPARTITION: {}\nOFFSET: {}\n",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset()
                );
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Could not produced a message. Error: {}", ex.getMessage());
            }
        });
    }
}
