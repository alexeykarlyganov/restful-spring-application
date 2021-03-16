package org.alexeykarlyganov.rest.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.annotation.PostConstruct;

import org.alexeykarlyganov.rest.models.BuildInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

@Service
public class BuildInfoService {
 
    private final BuildProperties buildProperties;

    private BuildInfo buildInfo;

    @Autowired
    public BuildInfoService(BuildProperties buildProperties)
    {
        this.buildProperties = buildProperties;
    }

    @PostConstruct
    public void buildProperties()
    {
        String name = buildProperties.getName();
        String version = buildProperties.getVersion();
        LocalDateTime buildTime = buildProperties.getTime().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime startTime = LocalDateTime.now();

        buildInfo = new BuildInfo(name, version, buildTime, startTime);
    }

    public BuildInfo getBuildInfo()
    {
        return buildInfo;
    }
}
