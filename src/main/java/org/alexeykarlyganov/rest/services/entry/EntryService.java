package org.alexeykarlyganov.rest.services.entry;

import org.alexeykarlyganov.rest.models.Entry;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

public interface EntryService {
    void send(Entry entry);
    void receive(@Payload Entry user, @Headers MessageHeaders headers);
}
