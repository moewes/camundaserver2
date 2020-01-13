package net.moewes.rest;

import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngines;

@Singleton
@Startup
public class TestBean {

  private static final Logger LOGGER = Logger.getLogger("ListenerBean");
  private String text = "Hello";

  @PostConstruct
  public void init() {
    System.out.println("Hallo Welt");
    text = "Hello from CDI";

    ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
        .setJobExecutorActivate(true)
        .buildProcessEngine();
    ProcessEngines.getDefaultProcessEngine();
  }

  public String getText() {
    return text;
  }

  public String getEngines() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    return processEngine.getName();
  }
}
