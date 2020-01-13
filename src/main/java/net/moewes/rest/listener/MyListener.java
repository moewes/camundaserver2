package net.moewes.rest.listener;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class MyListener implements TaskListener {

  Logger log = Logger.getLogger(MyListener.class.getName());

  @Override
  public void notify(DelegateTask delegateTask) {
    delegateTask.setAssignee("mam");
    log.info(delegateTask.getName());
  }
}
