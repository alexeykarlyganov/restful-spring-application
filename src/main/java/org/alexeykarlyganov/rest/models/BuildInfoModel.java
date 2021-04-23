package org.alexeykarlyganov.rest.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildInfoModel {
    @JsonProperty("name")
    private String name;

    @JsonProperty("version")
    private String version;
    
    @JsonProperty("buildTime")
    private LocalDateTime buildTime;

    @JsonProperty("startTime")
    private LocalDateTime startTime;

    @JsonProperty("activeProfile")
    private String activeProfile;
}
