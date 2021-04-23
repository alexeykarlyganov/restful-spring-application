package org.alexeykarlyganov.rest.services;

import lombok.extern.slf4j.Slf4j;
import org.alexeykarlyganov.rest.models.BuildInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Profile("prod")
@Slf4j
public class BuildInfoProdImpl implements BuildInfo {
    private final BuildProperties buildProperties;

    private BuildInfoModel buildInfoModel;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    @Autowired
    public BuildInfoProdImpl(BuildProperties buildProperties)
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
        String profile = this.getActiveProfile(this.activeProfile);
        log.info("Build PROD system info");
        buildInfoModel = new BuildInfoModel(name, version, buildTime, startTime, profile);
    }

    public BuildInfoModel getBuildInfo()
    {
        return buildInfoModel;
    }

    private String getActiveProfile(String profiles) {
        String activeProfile = "";
        for (final String profile : profiles.split(",")) {
            activeProfile += profile;
        }

        return activeProfile;
    }
}
