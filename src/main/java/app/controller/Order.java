package app.controller;

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

public class Order extends Controller {
  public void show() {
    JSONArray currencies = null;
    try {
      String response = IOUtils.toString(new URL("https://bittrex.com/api/v1.1/public/getcurrencies"), "UTF-8");
      currencies = new JSONObject(response).getJSONArray("result");
    } catch (IOException | JSONException e) {
      page.addMessage(new Message(Message.Type.WARNING, t.t("currency.list.error")));
    }

    View view = new View("order");
    view.set("brokerForm", getBrokerForm());
    view.set("currencies", currencies);
    page.setResponse(view);
  }

  private Form getBrokerForm() {
    Select brokerSlt = new Select("broker", t.t("broker"));
    brokerSlt.addAttr("class", "js-select2 w100");
    brokerSlt.setDefaultValue("bittrex");
    brokerSlt.addOption("bittrex", "Bittrex");
    brokerSlt.addOption("binance", "Binance");
    brokerSlt.addOption("poloniex", "Poloniex");
    brokerSlt.addOption("kraken", "Kraken");
    brokerSlt.addOption("bitfinex", "Bitfinex");
    brokerSlt.addOption("gdax", "GDax");

    Form form = new Form();
    form.insert(brokerSlt.toString());

    return form;
  }
}
