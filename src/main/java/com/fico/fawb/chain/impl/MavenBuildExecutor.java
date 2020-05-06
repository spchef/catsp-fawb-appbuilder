package com.fico.fawb.chain.impl;

import com.fico.fawb.chain.IBuildProcessor;
import com.fico.fawb.model.BuildContext;
import com.fico.fawb.utils.ProcessUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MavenBuildExecutor implements IBuildProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MavenBuildExecutor.class);

    @Override
    public void process(BuildContext context) {
        String projectDir = context.getProjectDir();
        String command = context.getCommand();
        File logFile = new File(context.getBuildConfig().getLogFilePath());
        ProcessUtils.executeCommand(projectDir, command, logFile);
    }
}
