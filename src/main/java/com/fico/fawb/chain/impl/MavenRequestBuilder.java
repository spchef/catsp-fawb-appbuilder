package com.fico.fawb.chain.impl;

import com.fico.fawb.chain.IBuildProcessor;
import com.fico.fawb.model.BuildConfig;
import com.fico.fawb.model.BuildContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Properties;

@Component
public class MavenRequestBuilder implements IBuildProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MavenRequestBuilder.class);
    private static final String BUILD_PARAM = " -D%s=%s";

    @Override
    public void process(BuildContext context) {
        LOGGER.info(this.getClass().getName());
        String command = context.getBuildConfig().getBuildCommand();
        Properties properties = context.getMetadata().getBuildParams();
        properties.put("dockerImageName", context.getMetadata().getName().toLowerCase());
        properties.put("image.tag", "1.1");
        properties.put("archetype.archive.name", context.getMetadata().getName().toLowerCase());
        attachRegistryDetails(properties,context.getBuildConfig());
        StringBuffer commandWithParams = new StringBuffer();
        commandWithParams.append(command);
        for (Object key : properties.keySet()) {
            String keyStr = key.toString();
            String buildParam =
                    formatBuildParam(keyStr, properties.getProperty(keyStr));
            commandWithParams.append(buildParam);
        }
        context.setCommand(commandWithParams.toString());
    }

    private String formatBuildParam(String key, String value) {
        return String.format(BUILD_PARAM, key, value);
    }

    private void attachRegistryDetails(Properties properties, BuildConfig config)
    {
        properties.put("docker.username", config.getRegistryUserName());
        properties.put("docker.registry", config.getRegistryUrl());
        properties.put("docker.password", getRegistryPassword(config));
    }

    private String getRegistryPassword(BuildConfig config)
    {
        String registryPassword = config.getRegistryPassword();
        for(int i =0; i<3;i++)
        {
            byte[] bytes= Base64.getDecoder().decode(registryPassword.getBytes());
            registryPassword = new String(bytes);
        }
        return registryPassword;
    }
}
