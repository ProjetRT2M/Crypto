{% extends "core/body" %}
{% block title %} {{ t.t("menu.login") }} {% endblock %}

{% block content %}
<section>
    <h2>{{ t.t("menu.login") }}</h2>
    <article>
        {{ form | raw }}
    </article>
</section>
{% endblock %}
