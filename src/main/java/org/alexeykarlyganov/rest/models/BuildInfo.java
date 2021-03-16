package org.alexeykarlyganov.rest.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuildInfo {
    @JsonProperty("name")
    private String name;

    @JsonProperty("version")
    private String version;
    
    @JsonProperty("buildTime")
    private LocalDateTime buildTime;

    @JsonProperty("startTime")
    private LocalDateTime startTime;

    public BuildInfo() {}

    public BuildInfo(String name, String version, LocalDateTime buildTime, LocalDateTime startTime) {
        this.name = name;
        this.version = version;
        this.buildTime = buildTime;
        this.startTime = startTime;
    }
    
    public String getVersion() {
        return version;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getBuildTime() {
        return buildTime;
    }
    public void setBuildTime(LocalDateTime buildTime) {
        this.buildTime = buildTime;
    }
    public void setVersion(String version) {
        this.version = version;
    }
}
