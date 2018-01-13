<!DOCTYPE html>
<html>
	<head>
		<title>{{ title }} - Crypto</title>
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
					<li><a href="{{ u.uri("/logout?tk=") + u.getToken() }}">{{ t.t("menu.logout") }}</a></li>
				{% else %}
					<li><a href="{{ u.uri("/login") }}">{{ t.t("menu.login") }}</a></li>
				{% endif %}
			</ul>
		</nav>

		<header>
			<h1>Crypto</h1>
		</header>

		<main>
			{{ messages | raw }}
			{{ content | raw }}
		</main>

		<footer>
			<p>Bla bla bla</p>
		</footer>
	</body>
</html>
