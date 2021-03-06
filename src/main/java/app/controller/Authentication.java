package app.controller;

import app.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import web.core.Action;
import web.core.Controller;
import web.core.Message;
import web.core.View;
import web.db.SelectQuery;
import web.html.Form;
import web.html.Input;
import web.util.NotFoundException;
import web.util.Util;

public class Authentication extends Controller {
  @Action(uri = "/login", permission = "guest")
  public void login() {
    if (req.isPost() && req.get("formId").equals("loginForm")) {
      try {
        User user = new SelectQuery<>(User.class)
            .addCondition("login", "=", req.get("login"))
            .addCondition("password", "=", DigestUtils.sha256Hex(req.get("password")))
            .get();

        appUser.setId(user.getId());
        appUser.setPermissions(user.getPermissions());
        page.setRedirection(Util.uri("/"));
        return;
      } catch (NotFoundException e) {
        page.addMessage(Message.Type.WARNING, t.t("login.incorrect"));
      }
    }

    Input loginIpt = new Input("text", "login", t.t("login"));
    Input passwordIpt = new Input("password", "password", t.t("password"));

    Form form = new Form("post", "", "loginForm");
    form.insert(loginIpt.toString(), "p");
    form.insert(passwordIpt.toString(), "p");
    form.addSubmitButton(t.t("ok"));

    View view = new View("login");
    view.set("form", form);
    page.setResponse(view);
  }

  @Action(uri = "/logout", permission = "member", token = true)
  public void logout() {
    app.getSession().destroy();
    page.setRedirection(Util.uri("/login"));
  }
}
