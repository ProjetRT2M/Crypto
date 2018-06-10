{% extends "core/body" %}
{% block title %} {{ t.t("error.number", [code]) }} {% endblock %}

{% block content %}
<section>
  <h2>{{ t.t("error.number", [code]) }}</h2>
  <article>
    <p>{{ message }}</p>
  </article>
</section>
{% endblock %}
