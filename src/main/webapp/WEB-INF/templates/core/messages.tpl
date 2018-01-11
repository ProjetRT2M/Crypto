<section id="page-messages">
	<div class="close"></div>
	<article>
		{% for msg in messages %}
			<p class="{{ msg.type }}">{{ msg.content }}</p>
		{% endfor %}
	</article>
</section>
