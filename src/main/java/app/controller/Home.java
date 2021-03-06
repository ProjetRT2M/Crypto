package app.controller;

import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import web.core.Action;
import web.core.Controller;
import web.core.Message;
import web.core.View;
import web.html.Form;
import web.html.Select;

public class Home extends Controller {
  @Action(uri = "/", permission = "all")
  public void show() {
    Select currencySlt = new Select("currency", t.t("currency"));
    currencySlt.addAttr("class", "js-select2 w40");
    currencySlt.setDefaultValue("BTC");

    try {
      String response = IOUtils.toString(new URL("https://bittrex.com/api/v1.1/public/getcurrencies"), "UTF-8");
      JSONArray currencies = new JSONObject(response).getJSONArray("result");

      for (int i = 0; i < currencies.length(); i++) {
        JSONObject jo = currencies.getJSONObject(i);
        currencySlt.addOption(jo.getString("Currency"), jo.getString("CurrencyLong"));
      }
    } catch (IOException | JSONException e) {
      page.addMessage(Message.Type.WARNING, t.t("currency.list.error"));
    }

    Select conversionSlt = new Select("conversion");
    conversionSlt.addAttr("class", "js-select2 w40");
    conversionSlt.setDefaultValue("USD");
    conversionSlt.addOption("USD", "Dollar");
    conversionSlt.addOption("BTC", "Bitcoin");

    Form form = new Form();
    form.insert(currencySlt.toString() + conversionSlt.toString(), "p");

    View view = new View("home");
    view.set("currencyForm", form);
    page.setResponse(view);
  }

  @Action(uri = "/help", permission = "all")
  public void showHelp() {
    View view = new View("help");
    page.setResponse(view);
  }
}
