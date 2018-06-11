package app.init;

import javax.servlet.ServletContext;
import web.core.App;
import web.core.Initializable;
import web.core.Route;
import web.core.View;
import web.util.ForbiddenException;
import web.util.NotFoundException;

public class Initializer implements Initializable {
  @Override
  public void onAppStart(ServletContext context) {
    //...
  }

  @Override
  public void onRequestStart(App app) {
    //...
  }

  @Override
  public void onRequestFinish(App app) {
    //...
  }

  @Override
  public void handleException(NotFoundException e, App app) {
    View view = new View("core/error");
    view.set("code", 404);
    view.set("message", "Page introuvable");
    app.getPage().setResponse(view);
  }

  @Override
  public void handleException(ForbiddenException e, App app, Route route) {
    View view = new View("core/error");
    view.set("code", 403);
    view.set("message", "Accès interdit");
    app.getPage().setResponse(view);
  }

  @Override
  public void handleException(Exception e, App app) {
    View view = new View("core/error");
    view.set("code", 500);
    view.set("message", e.getMessage());
    app.getPage().setResponse(view);
  }
}
