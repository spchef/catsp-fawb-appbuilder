package com.fico.fawb.chain;

import com.fico.fawb.model.BuildConfig;
import com.fico.fawb.model.BuildContext;
import com.fico.fawb.model.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class BuildProcessorChain {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuildProcessorChain.class);

    private List<State> buildStates = new ArrayList<>();

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BuildConfig buildConfig;

    @PostConstruct
    public void init() {
        buildStates.add(State.EXTRACT_MODEL);
        buildStates.add(State.PREPARE_BUILD);
        buildStates.add(State.EXECUTE_BUILD);
        buildStates.add(State.POST_BUILD);
    }

    public void process() {
        BuildContext buildContext = new BuildContext(buildConfig);
        for (State state : buildStates) {
            LOGGER.info("about to execute state: " + state.name());
            state.getBuildProcessor(applicationContext).process(buildContext);
            LOGGER.info("successfully executed state: " + state.name());
        }
    }
}
