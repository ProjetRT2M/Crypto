<!DOCTYPE html>
<html>
    <head>
        <title>{% block title %}Crypto{% endblock %} - Crypto</title>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel="stylesheet" href="{{ u.uri("/public/css/style.css") }}"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
        <script src="{{ u.uri("/public/js/core.js") }}"></script>
    </head>

    <body>
        <nav>
            <ul>
                <li><a href="{{ u.uri("/") }}">{{ t.t("menu.home") }}</a></li>

                {% if u.access("member") %}
                    <li><a href="{{ u.uri("/order") }}">{{ t.t("menu.order") }}</a></li>
                    <li><a href="{{ u.uri("/dashboard") }}">{{ "Dashboard" }}</a></li>
                    <li><a href="{{ u.uri("/settings") }}">{{ t.t("menu.settings") }}</a></li>
                    <li><a href="{{ u.uri("/logout?tk=") + u.getToken() }}">{{ t.t("menu.logout") }}</a></li>
                {% else %}
                    <li><a href="{{ u.uri("/login") }}">{{ t.t("menu.login") }}</a></li>
                {% endif %}

                <li><a href="{{ u.uri("/help") }}">{{ t.t("menu.help") }}</a></li>
            </ul>
        </nav>

        <header>
            <h1>Crypto</h1>
        </header>

        <main>
            {% if not messages.isEmpty() %}
                <section id="page-messages">
                    <article>
                        {% for msg in messages %}
                            <p class="{{ msg.type }}">{{ msg.content }}</p>
                        {% endfor %}

                        {{ messages.clear() }}
                    </article>
                </section>
            {% endif %}

            {% block content %}{% endblock %}
        </main>

        <footer>
            <p>© 2018 - Itescia</p>
        </footer>
    </body>
</html>
