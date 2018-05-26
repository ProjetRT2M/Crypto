{% extends "core/body" %}
{% block title %} Dashboard {% endblock %}

{% block content %}
<section>
	<h2>Programmer un ordre</h2>
	<article>
		<form method="post">
			<p>
				<select name="currency" class="js-select2">
					<option selected disabled>Monnaie</option>

					{% for json in currencies %}
						<option value="{{ json.getString("Currency") + "@" + json.getString("CurrencyLong") }}">
							{{ json.getString("CurrencyLong") }}
						</option>
					{% endfor %}
				</select>

				<input type="text" name="percentage" placeholder="%" style="margin-left:20px; width:50px" /> %
				<input type="hidden" name="tk" value="{{ u.getToken() }}" />

				<input type="submit" value="Enregistrer" style="margin-left:20px" />
			</p>
		</form>
	</article>
</section>

<section>
	<h2>Liste des ordres enregistrés</h2>
	<article>
		{% for order in orders %}
			<p>
				Monnaie : {{ order.currencyLong }} |
				Pourcentage : {{ order.percentage }} % |
				Statut : {{ t.t("order.status." + order.status) }}
			</p>
		{% endfor %}
	</article>
</section>
{% endblock %}
