package com.fico.fawb.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
public class BuildConfig {
    @Value("${catsp.fawb.project.workspace.dir}")
    private String workspacePath;
    @Value("${catsp.fawb.shared.dir}")
    private String catspSharedAssetDir;
    @Value("${catsp.fawb.model.file.path}")
    private String modelFilePath;
    @Value("${fawb.project.metadata.file}")
    private String projectMetadataFileName;
    @Value("${fawb.project.build.command}")
    private String buildCommand;
    @Value("${logging.file}")
    private String logFilePath;
    @Value("${catsp.model.archetype.archive.name}")
    private String modelArchetypeName;
    @Value("${registry.url}")
    private String registryUrl;
    @Value("${registry.username}")
    private String registryUserName;
    @Value("${registry.password}")
    private String registryPassword;

    @PostConstruct
    public void init()
    {
        File workspaceDir = new File(workspacePath);
        workspaceDir.mkdirs();
    }
    public String getWorkspacePath() {
        return workspacePath;
    }

    public void setWorkspacePath(String workspacePath) {
        this.workspacePath = workspacePath;
    }

    public String getModelFilePath() {
        return modelFilePath;
    }

    public void setModelFilePath(String modelFilePath) {
        this.modelFilePath = modelFilePath;
    }

    public String getProjectMetadataFileName() {
        return projectMetadataFileName;
    }

    public void setProjectMetadataFileName(String projectMetadataFileName) {
        this.projectMetadataFileName = projectMetadataFileName;
    }

    public String getBuildCommand() {
        return buildCommand;
    }

    public void setBuildCommand(String buildCommand) {
        this.buildCommand = buildCommand;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public String getCatspSharedAssetDir() {
        return catspSharedAssetDir;
    }

    public void setCatspSharedAssetDir(String catspSharedAssetDir) {
        this.catspSharedAssetDir = catspSharedAssetDir;
    }

    public String getModelArchetypeName() {
        return modelArchetypeName;
    }

    public void setModelArchetypeName(String modelArchetypeName) {
        this.modelArchetypeName = modelArchetypeName;
    }

    public String getRegistryUrl() {
        return registryUrl;
    }

    public void setRegistryUrl(String registryUrl) {
        this.registryUrl = registryUrl;
    }

    public String getRegistryUserName() {
        return registryUserName;
    }

    public void setRegistryUserName(String registryUserName) {
        this.registryUserName = registryUserName;
    }

    public String getRegistryPassword() {
        return registryPassword;
    }

    public void setRegistryPassword(String registryPassword) {
        this.registryPassword = registryPassword;
    }
}
