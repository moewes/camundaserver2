package net.moewes.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestResource {

  @Inject
  TestBean bean;

  @Path("/static")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String sayHello() {
    return "Hello World";
  }

  @Path("/cdi")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String sayHelloCdi() {
    return bean.getText();
  }

  @Path("/pe")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String name() {
    return bean.getEngines();
  }

}
