package app.controller;

import app.entity.Order;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import web.core.Controller;
import web.core.Message;
import web.core.View;
import web.db.SelectQuery;
import web.util.EntityException;

public class Dashboard extends Controller {
	public void show() {
		if (req.isPost()) {
			try {
				String[] currency = req.get("currency").split("@");

				app.entity.Order o = new app.entity.Order();
				o.setUser(app.getUserId());
				o.setCurrency(currency[0]);
				o.setCurrencyLong(currency[1]);
				o.setPercentage(req.get("percentage"));
				o.save();
				page.addMessage(Message.SUCCESS, "Ordre enregistré");
			} catch (EntityException e) {
				page.addMessage(Message.WARNING, e.getMessage());
			}
		}

		JSONArray currencies = null;

		try {
			String response = IOUtils.toString(new URL("https://bittrex.com/api/v1.1/public/getcurrencies"), "UTF-8");
			currencies = new JSONObject(response).getJSONArray("result");
		} catch (IOException | JSONException e) {
			page.addMessage(Message.WARNING, t.t("currency.list.error"));
		}

		ArrayList<Order> orders = new SelectQuery<>(app.entity.Order.class)
			.addCondition("userId", "=", app.getUserId())
			.getAll();

		View v = new View("dashboard");
		v.set("currencies", currencies);
		v.set("orders", orders);

		page.setResponse(v);
	}
}
