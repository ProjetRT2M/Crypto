$(document).ready(function () {
	$("#page-messages .close").click(function () {
		$("#page-messages").slideUp();
	});

	$("select.js-select2").select2();
});
