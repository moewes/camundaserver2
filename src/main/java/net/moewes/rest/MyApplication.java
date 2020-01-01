package net.moewes.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.camunda.bpm.engine.rest.impl.CamundaRestResources;

@ApplicationPath("/api")
public class MyApplication extends Application {


  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.addAll(CamundaRestResources.getResourceClasses());
    classes.addAll(CamundaRestResources.getConfigurationClasses());
    classes.add(TestResource.class);

    return classes;
  }
}
