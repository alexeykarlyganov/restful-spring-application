package org.alexeykarlyganov.rest.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Entry {
    private String name;
    private String createdTime;

    @JsonCreator
    public Entry(
            @JsonProperty("name") String name,
            @JsonProperty("createdTime") String createdTime
    ) {
        this.name = name;
        this.createdTime = createdTime;
    }
}
