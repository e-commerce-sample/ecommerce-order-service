package com.ecommerce.order.about;


import com.ecommerce.order.common.logging.RequestIdAwareRepresentation;

public class AboutRepresentation extends RequestIdAwareRepresentation {
    private String buildNumber;
    private String buildTime;
    private String gitRevision;
    private String environment;
    private String deployTime;

    public AboutRepresentation(String buildNumber, String buildTime, String gitRevision, String environment, String deployTime) {
        this.buildNumber = buildNumber;
        this.buildTime = buildTime;
        this.gitRevision = gitRevision;
        this.environment = environment;
        this.deployTime = deployTime;
    }

}
