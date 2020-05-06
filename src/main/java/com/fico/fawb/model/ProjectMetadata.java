package com.fico.fawb.model;

import java.util.Properties;

public class ProjectMetadata {
    private String name;
    private String vcsUrl;
    private Properties buildParams;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVcsUrl() {
        return vcsUrl;
    }

    public void setVcsUrl(String vcsUrl) {
        this.vcsUrl = vcsUrl;
    }

    public Properties getBuildParams() {
        return buildParams;
    }

    public void setBuildParams(Properties buildParams) {
        this.buildParams = buildParams;
    }
}
