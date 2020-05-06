package com.fico.fawb.model;

import com.fico.fawb.chain.IBuildProcessor;
import com.fico.fawb.chain.impl.*;
import org.springframework.context.ApplicationContext;

public enum State {
    EXTRACT_METADATA{
        @Override
        public IBuildProcessor getBuildProcessor(ApplicationContext context) {
            return context.getBean(MetadataExtractor.class);
        }
    },
    CLONE_PROJECT{
        @Override
        public IBuildProcessor getBuildProcessor(ApplicationContext context) {
            return context.getBean(ProjectCloner.class);
        }
    },
    PREPARE_BUILD{
        @Override
        public IBuildProcessor getBuildProcessor(ApplicationContext context) {
            return context.getBean(MavenRequestBuilder.class);
        }
    },
    EXECUTE_BUILD{
        @Override
        public IBuildProcessor getBuildProcessor(ApplicationContext context) {
            return context.getBean(MavenBuildExecutor.class);
        }
    },
    POST_BUILD {
        @Override
        public IBuildProcessor getBuildProcessor(ApplicationContext context) {
            return context.getBean(PostBuildActionExecutor.class);
        }
    };

    public abstract IBuildProcessor getBuildProcessor(ApplicationContext context);
}
