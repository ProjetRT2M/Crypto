package crypto.controller;

import crypto.entity.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import web.core.Controller;
import web.core.Message;
import web.core.View;
import web.html.BoxSelect;
import web.html.Form;
import web.html.Input;
import web.util.EntityException;

public class Settings extends Controller {
	public void show() {
		User user = new User();
		user.fetch(app.getUserId());

		if (req.isPost()) {
			switch (req.get("formId")) {
			case "settingsForm":
				processSettingsForm(user);
				break;
			case "passwordForm":
				processPasswordForm(user);
				break;
			case "accountForm":
				processAccountForm();
				break;
			}
		}

		View view = new View("settings")
			.add("settingsForm", getSettingsForm(user))
			.add("passwordForm", getPasswordForm());

		if (app.access("admin")) {
			view.add("accountForm", getAccountForm());
		}

		page.setTitle(t.t("menu.settings"));
		page.setView(view);
	}

	private Form getSettingsForm(User user) {
		Input emailIpt = new Input("email", "email", t.t("address.email"));
		emailIpt.addAttr("value", user.getEmail());
		Input apiKeyIpt = new Input("text", "apiKey", "Bittrex API Key");
		apiKeyIpt.addAttr("value", user.getApiKey());
		Input apiSecretIpt = new Input("text", "apiSecret", "Bittrex API Secret");
		apiSecretIpt.addAttr("value", user.getApiSecret());

		Form form = new Form("post", "", "settingsForm");
		form.insert(emailIpt.toString(), "p");
		form.insert(apiKeyIpt.toString(), "p");
		form.insert(apiSecretIpt.toString(), "p");
		form.addSubmitButton(t.t("ok"));

		return form;
	}

	private void processSettingsForm(User user) {
		try {
			user.setEmail(req.get("email"));
			user.setApiKey(req.get("apiKey"));
			user.setApiSecret(req.get("apiKey"));
			user.save();
			page.addMessage(Message.SUCCESS, t.t("settings.saved"));
		} catch (EntityException e) {
			page.addMessage(Message.WARNING, e.getMessage());
		}
	}

	private Form getPasswordForm() {
		Input newPasswordIpt = new Input("password", "newPassword", t.t("password.new"));
		Input newPasswordConfirmIpt = new Input("password", "newPasswordConfirm", t.t("password.confirm"));
		Input oldPasswordIpt = new Input("password", "oldPassword", t.t("password.old"));

		Form form = new Form("post", "", "passwordForm");
		form.insert(newPasswordIpt.toString(), "p");
		form.insert(newPasswordConfirmIpt.toString(), "p");
		form.insert(oldPasswordIpt.toString(), "p");
		form.addSubmitButton(t.t("ok"));

		return form;
	}

	private void processPasswordForm(User user) {
		String newPassword = req.get("newPassword");
		String newPasswordConfirm = req.get("newPasswordConfirm");
		String oldPassword = DigestUtils.sha256Hex(req.get("oldPassword"));

		if (!newPassword.equals(newPasswordConfirm)) {
			page.addMessage(Message.WARNING, t.t("password.different"));
		} else if (!oldPassword.equals(user.getPassword())) {
			page.addMessage(Message.WARNING, t.t("password.old.incorrect"));
		} else {
			try {
				user.setPassword(newPassword);
				user.save();
				page.addMessage(Message.SUCCESS, t.t("password.saved"));
			} catch (EntityException e) {
				page.addMessage(Message.WARNING, e.getMessage());
			}
		}
	}

	private Form getAccountForm() {
		Input loginIpt = new Input("text", "login", t.t("login"));
		Input passwordIpt = new Input("password", "password", t.t("password"));

		BoxSelect permissionBox = new BoxSelect("permissions[]");
		Input memberIpt = permissionBox.getInput("member", t.t("user.member"));
		memberIpt.addAttr("checked");
		memberIpt.addAttr("disabled");
		permissionBox.insert(memberIpt.toString());
		permissionBox.addInput("modo", t.t("user.modo"));
		permissionBox.addInput("admin", t.t("user.admin"));

		Form form = new Form("post", "", "accountForm");
		form.insert(loginIpt.toString(), "p");
		form.insert(passwordIpt.toString(), "p");
		form.insert(permissionBox.toString(), "p");
		form.addSubmitButton(t.t("ok"));

		return form;
	}

	private void processAccountForm() {
		try {
			User user = new User();
			user.setLogin(req.get("login"));
			user.setPassword(req.get("password"));
			List<String> permissions = new ArrayList<>(Arrays.asList(req.getAll("permissions[]")));
			permissions.add("member");
			user.setPermissions(permissions);
			user.save();
			page.addMessage(Message.SUCCESS, t.t("account.saved"));
		} catch (EntityException e) {
			page.addMessage(Message.WARNING, e.getMessage());
		}
	}
}
