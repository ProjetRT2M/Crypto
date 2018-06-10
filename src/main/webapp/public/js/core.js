$(document).ready(function () {
  $("select.js-select2").select2({
    width: 'resolve'
  });
});

function getParam(name, url) {
  return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(url)
      || [null, ''])[1].replace(/\+/g, '%20')) || null;
}
