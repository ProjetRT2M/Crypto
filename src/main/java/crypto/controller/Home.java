package crypto.controller;

import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import web.core.Controller;
import web.core.Message;
import web.core.View;
import web.html.Form;
import web.html.Select;

public class Home extends Controller {
	public void show() {
		Select select = new Select("currency", t.t("currency"));
		select.addAttr("class", "js-select2 w50");

		try {
			String response = IOUtils.toString(new URL("https://bittrex.com/api/v1.1/public/getcurrencies"), "UTF-8");
			JSONArray currencies = new JSONObject(response).getJSONArray("result");

			for (int i = 0; i < currencies.length(); i++) {
				JSONObject jo = currencies.getJSONObject(i);
				select.addOption(jo.getString("Currency"), jo.getString("CurrencyLong"));
			}
		} catch (IOException | JSONException e) {
			page.addMessage(new Message(Message.WARNING, t.t("currency.list.error")));
		}

		Form form = new Form();
		form.insert(select.toString(), "p");

		View view = new View("home")
			.add("currencyForm", form);

		page.setTitle(t.t("menu.home"));
		page.setView(view);
	}

	public void showHelp() {
		View view = new View("help");
		page.setTitle(t.t("menu.help"));
		page.setView(view);
	}
}
