package com.fico.fawb.chain.impl;

import com.fico.fawb.chain.IBuildProcessor;
import com.fico.fawb.model.BuildContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProjectCloner implements IBuildProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectCloner.class);
    @Override
    public void process(BuildContext context) {
        LOGGER.info(this.getClass().getName());
    }
}
