var selectedCurrency = "BTC";

$(document).ready(function () {
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

		resetForm();
		iframe.attr("src", url.replace(currentCurrency, newCurrency));
	});

	$("input[name=price], input[name=quantity]").keyup(updateForm);

	$(".currency-list").find("p[data-currency=" + selectedCurrency + "]").trigger("click");
});

function updateForm() {
	var price = parseFloat($("input[name=price]").val());
	if (isNaN(price)) price = 0;

	var quantity = parseFloat($("input[name=quantity]").val());
	if (isNaN(quantity)) quantity = 0;

	var btcQuantity = price * quantity;
	$("input[name=btcQuantity]").val(btcQuantity > 0 ? btcQuantity : "");
}

function resetForm() {
	if (selectedCurrency.startsWith("BTC")) {
		$(".order-form").fadeOut();
	} else {
		$(".order-form").fadeIn();
	}

	$("input[name=price], input[name=quantity], input[name=btcQuantity]").val("");
	$("input[name=orderType], input[name=priceType]").prop("checked", false);
}
