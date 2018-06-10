{% extends "core/body" %}
{% block title %} {{ t.t("menu.home") }} {% endblock %}

{% block content %}
<section>
  <h2>{{ t.t("menu.home") }}</h2>
  <article>
    {{ currencyForm | raw }}

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
  </article>
</section>
<script type="text/javascript" src="{{ u.uri("/public/js/home.js") }}"></script>
{% endblock %}
