package org.alexeykarlyganov.rest.services.entry;

import org.alexeykarlyganov.rest.models.Entry;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.kafka.support.SendResult;

public interface EntryService {
    /**
     * Send a Entry object to the kafka
     * @param entry - A entry object
     */
    ListenableFuture<SendResult<String, Entry>> send(Entry entry);

    /**
     * Receive a single Entry object
     * @param entry - A entry object
     * @param headers - A message headers
     */
    void receive(@Payload Entry entry, @Headers MessageHeaders headers);
}
