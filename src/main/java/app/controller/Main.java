package app.controller;

import web.core.Controller;
import web.core.MainController;
import web.core.View;
import web.util.ForbiddenException;
import web.util.NotFoundException;

public class Main extends Controller implements MainController {
    @Override
    public void start() {}

    @Override
    public void end() {}

    @Override
    public void handleException(NotFoundException e) {
        View view = new View("core/error");
        view.set("code", 404);
        view.set("message", "...");
        page.setResponse(view);
    }

    @Override
    public void handleException(ForbiddenException e) {
        View view = new View("core/error");
        view.set("code", 403);
        view.set("message", "...");
        page.setResponse(view);
    }

    @Override
    public void handleException(Exception otherException) {
        View view = new View("core/error");
        view.set("code", 500);
        view.set("message", otherException.getMessage());
        page.setResponse(view);
    }
}
