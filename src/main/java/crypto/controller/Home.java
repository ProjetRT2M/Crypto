package crypto.controller;

import web.core.Controller;
import web.core.View;

public class Home extends Controller {
	public void show() {
		View view = new View("home");
		page.setTitle(t.t("menu.home"));
		page.setView(view);
	}

	public void showHelp() {
		View view = new View("help");
		page.setTitle(t.t("menu.help"));
		page.setView(view);
	}
}
