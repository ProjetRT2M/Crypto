{% extends "core/body" %}
{% block title %} {{ t.t("menu.settings") }} {% endblock %}

{% block content %}
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

{% if u.access("admin") %}
	<section>
		<h2>{{ t.t("account.create") }}</h2>
		<article>
			{{ accountForm | raw }}
		</article>
	</section>
{% endif %}
{% endblock %}
