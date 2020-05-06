package com.fico.fawb;

import com.fico.fawb.chain.BuildProcessorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class CatspFawbAppbuilderApplication implements CommandLineRunner {

	@Autowired
	private Environment environment;
	@Autowired
	private BuildProcessorChain processorChain;
	private static final Logger LOGGER = LoggerFactory.getLogger(CatspFawbAppbuilderApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(CatspFawbAppbuilderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("java.version: "+System.getProperty("java.version"));
		LOGGER.info("run.debug: "+environment.getProperty("run.debug"));

		try
		{
			processorChain.process();
		}
		catch (Exception e)
		{
			LOGGER.error(e.toString(),e );
		}
		finally {
			shutdown();
		}
	}
	private void shutdown()
	{

		if(environment.getProperty("run.debug")==null)
		{
			LOGGER.info("terminating");
			System.exit(0);
		}
		else{
			LOGGER.info("running in debug mode");
		}
	}
}
