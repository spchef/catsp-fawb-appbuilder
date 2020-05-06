package com.fico.fawb.chain.impl;

import com.fico.fawb.chain.IBuildProcessor;
import com.fico.fawb.extractor.CatspModelExtractor;
import com.fico.fawb.model.BuildContext;
import com.fico.fawb.model.ProjectMetadata;
import com.fico.fawb.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class ModelExtractor implements IBuildProcessor {


    private static final Logger LOGGER = LoggerFactory.getLogger(ModelExtractor.class);

    @Override
    public void process(BuildContext context) {
        String modelFilePath = context.getBuildConfig().getModelFilePath();
        LOGGER.info("about to read model from: " +
                modelFilePath);
        File modelFile = new File(modelFilePath);
        if (!modelFile.exists()) {
            throw new RuntimeException("model file is not available");
        } else {
            try {
                extractModel(context);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void extractModel(BuildContext context) throws IOException {
        String projectMetadataFileName = context.getBuildConfig().getProjectMetadataFileName();
        String artifactDir = "artifact/";
        CatspModelExtractor extractor = new CatspModelExtractor(context);
        ZipInputStream zis = extractor.getZis();
        try {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                LOGGER.info(zipEntry.getName());
                byte bytes[] = new byte[(int) zipEntry.getSize()];
                zis.read(bytes);
                if (projectMetadataFileName.equals(zipEntry.getName())) {
                    extractor.setProjectMetadataJson(bytes);
                } else if (zipEntry.getName().startsWith(artifactDir)) {
                    extractor.setProjectZipContent(bytes);
                }
            }
            extractor.extract();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            zis.close();
        }
    }
}
