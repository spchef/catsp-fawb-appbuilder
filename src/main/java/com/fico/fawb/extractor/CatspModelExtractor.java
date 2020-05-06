package com.fico.fawb.extractor;

import com.fico.fawb.model.BuildContext;
import com.fico.fawb.model.ProjectMetadata;
import com.fico.fawb.utils.JsonUtils;
import com.fico.fawb.utils.ZipUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CatspModelExtractor {

    private ByteArrayInputStream bis;
    private ZipInputStream zis;
    private byte[] projectMetadataJson;
    private byte[] projectZipContent;
    private BuildContext context;

    public CatspModelExtractor(BuildContext context) {
        this.context = context;
        init();
    }

    public void init() {
        File modelFile = new File(context.getBuildConfig().getModelFilePath());
        try {
            bis = new ByteArrayInputStream(Files.readAllBytes(modelFile.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        zis = new ZipInputStream(bis);
    }

    public CatspModelExtractor setProjectMetadataJson(byte[] projectMetadataJson) {
        if (this.projectMetadataJson == null) {
            this.projectMetadataJson = projectMetadataJson;
        }
        return this;
    }

    public CatspModelExtractor setProjectZipContent(byte[] projectZipContent) {
        if (this.projectZipContent == null) {
            this.projectZipContent = projectZipContent;
        }
        return this;
    }

    public ZipInputStream getZis() {
        return zis;
    }

    public void extract() {
        extractMetadata();
        try {
            extractProject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void extractProject() throws IOException {

        String appName = context.getMetadata().getName();
        String workspace = context.getBuildConfig().getWorkspacePath();
        String projectDir = workspace + "/" + appName;
        String projectZipFilePath = projectDir + ".zip";
        File projectZipFile = new File(projectZipFilePath);
        if (projectZipFile.exists()) {
            projectZipFile.delete();
        }
        OutputStream os = new FileOutputStream(projectZipFile);
        os.write(projectZipContent);
        ZipUtils.unzip(projectZipFilePath, projectDir);
        context.setProjectDir(projectDir);
    }


    private void extractMetadata() {
        ProjectMetadata projectMetadata = JsonUtils.toObject(projectMetadataJson, ProjectMetadata.class);
        context.setMetadata(projectMetadata);
    }


}
