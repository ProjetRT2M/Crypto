<section>
	<h2>{{ t.t("settings.account") }}</h2>
	<article>
		{{ settingsForm | raw }}
	</article>
</section>

<section>
	<h2>{{ t.t("password.change") }}</h2>
	<article>
		{{ passwordForm | raw }}
	</article>
</section>

{% if accountForm is not null %}
	<section>
		<h2>{{ t.t("account.create") }}</h2>
		<article>
			{{ accountForm | raw }}
		</article>
	</section>
{% endif %}
