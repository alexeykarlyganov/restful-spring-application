package org.alexeykarlyganov.rest.controllers.system;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.alexeykarlyganov.rest.services.BuildInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
public class PingController {
    @Autowired
    private BuildInfoService buildInfoService;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getProperties() throws JsonProcessingException
    {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(buildInfoService.getBuildInfo());
    }
}
