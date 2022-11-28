$(document).ready(function () {
console.log("page is ready");

var responseData;
    $("#forms").on("submit", function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        var ip = {}
            ip["ip"] = $('input[name=ip]').val();
        var ipData = ip.ip;
        $("#hide").css('visibility','hidden');
        $("#loading").css('visibility', 'visible');
               $.ajax({
               	method: 'GET',
               	contentType: 'application/json',
               	url: 'https://ipwho.is/' + ipData,
               	dataType: 'json',
               	success: function(ipwhois) {
               	 var res = JSON.stringify(ipwhois);
                           window.localStorage.setItem('ipwhois', res);
                           window.location = "/results";
               		       console.log(ipwhois);
               	}
               });
      });
});
