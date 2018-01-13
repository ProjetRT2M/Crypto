package crypto.controller;

import crypto.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import web.core.Controller;
import web.core.Message;
import web.core.View;
import web.db.SelectQuery;
import web.html.Form;
import web.html.Input;
import web.util.NotFoundException;
import web.util.Util;

public class Authentication extends Controller {
	public void login() {
		if (req.isPost() && req.contains("login", "password")) {
			try {
				User user = new SelectQuery<>(User.class)
					.addCondition("login", "=", req.get("login"))
					.addCondition("password", "=", DigestUtils.sha256Hex(req.get("password")))
					.get();

				app.setUser(user.getId(), user.getPermissions());
				page.setRedirection(Util.uri("/"));
				return;
			} catch (NotFoundException e) {
				page.addMessage(Message.WARNING, t.t("login.incorrect"));
			}
		}

		Input loginInp = new Input("text", "login", t.t("login"));
		Input passwordInp = new Input("password", "password", t.t("password"));

		Form form = new Form("post", "");
		form.insert(loginInp.toString(), "p");
		form.insert(passwordInp.toString(), "p");
		form.addSubmitButton(t.t("ok"));

		View view = new View("login")
			.add("form", form);

		page.setTitle(t.t("menu.login"));
		page.setView(view);
	}

	public void logout() {
		app.getSession().destroy();
		page.setRedirection(Util.uri("/login"));
	}
}
