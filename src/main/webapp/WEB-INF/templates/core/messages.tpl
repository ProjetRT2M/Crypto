<section id="page-messages">
	<article>
		{% for msg in messages %}
			<p class="{{ msg.type }}">{{ msg.content }}</p>
		{% endfor %}
	</article>
</section>
