var selectedCurrency = "BTC";
var currencyData = null;

$(document).ready(function () {
  $.getJSON("https://poloniex.com/public?command=returnTicker", function (data) {
    currencyData = data;
  });

  $(".currency-list").click(updateWidget);
  $("input[name=price], input[name=quantity]").keyup(updateForm);
  $("input[name=priceType]").change(updatePrice);
  $("input#buy-button").click(buy);
  $("input#program-button").click(program);
  $(".currency-list").find("p[data-currency=" + selectedCurrency + "]").trigger("click");
});

function updateWidget(e) {
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
}

function updateForm() {
  var price = parseFloat($("input[name=price]").val());
  if (isNaN(price))
    price = 0;

  var quantity = parseFloat($("input[name=quantity]").val());
  if (isNaN(quantity))
    quantity = 0;

  var btcQuantity = price * quantity;
  $("input[name=btcQuantity]").val(btcQuantity > 0 ? btcQuantity : "");
}

function updatePrice() {
  $("input#program-button").toggle(this.value === "order");

  if (this.value === "market" && currencyData !== null) {
    $("input[name=price]").prop('disabled', true);
    var currency = "BTC_" + selectedCurrency;

    if (currency in currencyData) {
      $("input[name=price]").val(currencyData[currency].last).trigger("keyup");
    }
  } else {
    $("input[name=price]").val("");
    $("input[name=price]").prop('disabled', false);
  }
}

function buy() {
  $(".loading-gif").show();
  $(".order-message").html("Démarrage d'une requête Bittrex...");

  setTimeout(function () {
    $(".order-message").html("Tentative de transaction...");
    setTimeout(function () {
      $(".order-message").html("Echec de la transaction : Your balance is not sufficient for this transaction.");
      $(".loading-gif").hide();
    }, 3000);
  }, 3000);
}

function program() {
  $(".loading-gif").show();
  $(".order-message").html("Enregistrement de l'ordre d'achat...");

  setTimeout(function () {
    $(".order-message").html("Ordre enregistré, le traitement vous sera informé par e-mail.");
    $(".loading-gif").hide();
  }, 3000);
}

function resetForm() {
  $(".order-form").toggle(!selectedCurrency.startsWith("BTC"));
  $("input[name=price], input[name=quantity], input[name=btcQuantity]").val("");
  $("input[name=orderType], input[name=priceType]").prop("checked", false);
}
