package org.alexeykarlyganov.rest.controllers.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.alexeykarlyganov.rest.services.BuildInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/system")
public class PingController {
    private BuildInfo buildInfo;
    private ObjectMapper objectMapper;

    @Autowired
    public PingController(BuildInfo BuildInfo, ObjectMapper objectMapper) {
        this.buildInfo = BuildInfo;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProperties() throws JsonProcessingException
    {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(buildInfo.getBuildInfo());
    }
}
