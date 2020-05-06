package com.fico.fawb.chain;

import com.fico.fawb.model.BuildContext;
import com.fico.fawb.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class BuildProcessorChain {

    private List<State> buildStates = new ArrayList<>();

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init()
    {
        buildStates.add(State.EXTRACT_METADATA);
        buildStates.add(State.CLONE_PROJECT);
        buildStates.add(State.PREPARE_BUILD);
        buildStates.add(State.EXECUTE_BUILD);
        buildStates.add(State.POST_BUILD);
    }

    public void process()
    {
        BuildContext buildContext = new BuildContext();
        for(State state: buildStates)
        {
            state.getBuildProcessor(applicationContext).process(buildContext);
        }
    }

}
