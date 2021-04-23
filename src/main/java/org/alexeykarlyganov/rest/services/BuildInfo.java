package org.alexeykarlyganov.rest.services;

import org.alexeykarlyganov.rest.models.BuildInfoModel;

public interface BuildInfo {
    void buildProperties();
    BuildInfoModel getBuildInfo();
}
