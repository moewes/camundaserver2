package net.moewes.rest;

import io.quarkus.runtime.StartupEvent;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngines;

@ApplicationScoped
public class TestBean {

  private static final Logger LOGGER = Logger.getLogger("ListenerBean");
  private String text = "Hello";

  @PostConstruct
  public void init() {
    System.out.println("Hallo Welt");
    text = "Hello from CDI";

    ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
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

  void onStart(@Observes StartupEvent ev) {
    LOGGER.info("The application is starting...");
  }
}
