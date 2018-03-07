$(document).ready(function () {
	var selectedCurrency = "BTC";
	$(".currency-list").find("p[data-currency=" + selectedCurrency + "]").addClass("selected");

	$(".currency-list").click(function (e) {
		if (e.target.hasAttribute("data-currency")) {
			var selectedElement = $(e.target);
		} else {
			var selectedElement = $(e.target.parentElement);
		}

		$(".currency-list .selected").removeClass("selected");
		selectedElement.addClass("selected");

		selectedCurrency = selectedElement.data("currency");
		var iframe = $(".trading-view-widget iframe");
		var url = iframe.attr("src");
		var currentCurrency = getParam("symbol", url);
		var conversionCurrency = selectedCurrency.startsWith("BTC") ? "USD" : "BTC";
		var newCurrency = selectedCurrency + conversionCurrency;

		iframe.attr("src", url.replace(currentCurrency, newCurrency));
	});
});
