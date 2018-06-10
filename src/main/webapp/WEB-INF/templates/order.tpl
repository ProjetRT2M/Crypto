{% extends "core/body" %}
{% block title %} {{ t.t("menu.order") }} {% endblock %}

{% block content %}
<section class="order-page">
  <h2>{{ t.t("buy.sell.order") }}</h2>
  <article>
    <div class="order-menu">
      {{ brokerForm | raw }}

      <div class="currency-list">
        {% for json in currencies %}
          <p data-currency="{{ json.getString("Currency") }}">
            <span class="currency-short">{{ json.getString("Currency") }}</span>
            <span class="currency-long hidden">{{ json.getString("CurrencyLong") }}</span>
          </p>
        {% endfor %}
      </div>
    </div>

    <div class="order-block">
      <div class="trading-view-widget">
        <script type="text/javascript" src="https://s3.tradingview.com/tv.js"></script>
        <script type="text/javascript">
          new TradingView.widget({
          "autosize": true,
          "symbol": "BTCUSD",
          "interval": "D",
          "timezone": "Etc/UTC",
          "theme": "Light",
          "style": "1",
          "locale": "fr",
          "toolbar_bg": "#f1f3f6",
          "enable_publishing": false,
          "allow_symbol_change": false,
          "save_image": false,
          "hideideas": true
          });
        </script>
      </div>

      <form class="order-form hidden">
        <p>
          <input type="radio" name="orderType" id="orderType-buy" value="buy">
          <label for="orderType-buy">Achat</label>

          <input type="radio" name="orderType" id="orderType-sell" value="sell">
          <label for="orderType-sell">Vente</label>
        </p>

        <p>
          <input type="radio" name="priceType" id="priceType-market" value="market">
          <label for="priceType-market">Market</label>

          <input type="radio" name="priceType" id="priceType-order" value="order">
          <label for="priceType-order">Ordre</label>
        </p>

        <p>
          <label class="left-label" for="price">Prix</label>
          <input type="text" name="price" id="price" placeholder="Prix" title="Prix">
        </p>

        <p>
          <label class="left-label" for="quantity">Quantité</label>
          <input type="text" name="quantity" id="quantity" placeholder="Quantité" title="Quantité">
        </p>

        <p>
          <label class="left-label" for="btcQuantity">Quantité (BTC)</label>
          <input type="text" name="btcQuantity" id="btcQuantity" placeholder="Quantité (BTC)" title="Quantité (BTC)" disabled>
        </p>

        <p>
          <input type="button" value="Acheter" id="buy-button">
          <input type="button" value="Programmer" id="program-button">
        </p>

        <div class="loading-gif"></div>
        <p class="order-message"></p>
      </form>
    </div>

    <span class="clear-float"></span>
  </article>
</section>
<script type="text/javascript" src="{{ u.uri("/public/js/order.js") }}"></script>
{% endblock %}
