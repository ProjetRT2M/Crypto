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
import web.html.Input;
import web.html.RadioSelect;
import web.html.Select;

public class Order extends Controller {
	public void show() {
		JSONArray currencies = null;
		try {
			String response = IOUtils.toString(new URL("https://bittrex.com/api/v1.1/public/getcurrencies"), "UTF-8");
			currencies = new JSONObject(response).getJSONArray("result");
		} catch (IOException | JSONException e) {
			page.addMessage(new Message(Message.WARNING, t.t("currency.list.error")));
		}

		View view = new View("order")
			.add("brokerForm", getBrokerForm())
			.add("orderForm", getOrderForm())
			.add("currencies", currencies);

		page.setTitle(t.t("buy.sell.order"));
		page.setView(view);
	}

	private Form getBrokerForm() {
		Select brokerSlt = new Select("broker", t.t("broker"));
		brokerSlt.addAttr("class", "js-select2 w100");
		brokerSlt.setDefaultValue("bittrex");
		brokerSlt.addOption("bittrex", "Bittrex");
		brokerSlt.addOption("binance", "Binance");
		brokerSlt.addOption("poloniex", "Poloniex");
		brokerSlt.addOption("kraken", "Kraken");
		brokerSlt.addOption("bitbank", "Bitbank");
		brokerSlt.addOption("bitz", "Bit-Z");

		Form form = new Form();
		form.insert(brokerSlt.toString());

		return form;
	}

	private Form getOrderForm() {
		RadioSelect orderType = new RadioSelect("orderType");
		orderType.addInput("buy", t.t("order.buy"));
		orderType.addInput("sell", t.t("order.sell"));

		RadioSelect priceType = new RadioSelect("priceType");
		priceType.addInput("market", "Market");
		priceType.addInput("sell", "Ordre");

		Input price = new Input("text", "price", t.t("price"));
		Input quantity = new Input("text", "quantity", t.t("quantity"));
		Input btcQuantity = new Input("text", "btcQuantity", t.t("quantity") + " (BTC)");
		btcQuantity.addAttr("disabled");

		Form form = new Form();
		form.addAttr("class", "order-form");
		form.insert(orderType.toString(), "p");
		form.insert(priceType.toString(), "p");
		form.insert(price.toString(), "p");
		form.insert(quantity.toString(), "p");
		form.insert(btcQuantity.toString(), "p");

		return form;
	}
}
