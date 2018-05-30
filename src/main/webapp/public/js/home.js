$(document).ready(function () {
    $("select[name=currency], select[name=conversion]").change(function () {
        var iframe = $(".trading-view-widget iframe");
        var url = iframe.attr("src");
        var currentCurrency = getParam("symbol", url);
        var newCurrency = $("select[name=currency] option:selected").val()
            + $("select[name=conversion] option:selected").val();

        iframe.attr("src", url.replace(currentCurrency, newCurrency));
    });
});
