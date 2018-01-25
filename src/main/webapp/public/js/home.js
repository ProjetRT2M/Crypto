$(document).ready(function () {
	$('select[name=currency]').change(function () {
		var iframe = $('#trading-view-widget iframe');
		var url = iframe.attr('src');
		var currentCurrency = getParam('symbol', url);
		var newUrl = url.replace(currentCurrency, this.value + 'USD');
		iframe.attr('src', newUrl);
	});
});
