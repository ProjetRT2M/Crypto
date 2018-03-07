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
		</div>

		<span class="clear-float"></span>
	</article>
</section>

<script type="text/javascript" src="{{ u.uri("/public/js/order.js") }}"></script>
