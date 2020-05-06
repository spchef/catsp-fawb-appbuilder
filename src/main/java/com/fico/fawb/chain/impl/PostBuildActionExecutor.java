package com.fico.fawb.chain.impl;

import com.fico.fawb.chain.IBuildProcessor;
import com.fico.fawb.model.BuildContext;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class PostBuildActionExecutor implements IBuildProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostBuildActionExecutor.class);

    @Override
    public void process(BuildContext context) {
        String assetDir = context.getBuildConfig().getCatspSharedAssetDir();
        String projectDir = context.getProjectDir();
        String targetDir = projectDir + "/target/";
        String appName = context.getMetadata().getName();
        String sourceArchetypeZip = targetDir +
                appName + ".zip";
        String destArchetypeZip = assetDir + "/" + context.getBuildConfig().getModelArchetypeName() + ".zip";

        File source = new File(sourceArchetypeZip);
        File dest = new File(destArchetypeZip);
        try {
            FileUtils.copyFile(source, dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
