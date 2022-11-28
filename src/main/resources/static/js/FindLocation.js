var res;
$(document).ready(function () {
console.log("page is ready");

    $("#forms").on("submit", function (event) {
//      stop submit the form, we will post it manually.
        event.preventDefault();
        let mobileCountryCode = document.getElementById('mobileCountryCode').value;
        let mobileNetworkCode = document.getElementById('mobileNetworkCode').value;
        let locationAreaCode = document.getElementById('locationAreaCode').value;
        let cellId = document.getElementById('cellId').value;
            var imsiData = {mobileCountryCode,mobileNetworkCode,locationAreaCode,cellId};
            console.log(imsiData)
            var imsiDto  = JSON.stringify(imsiData);
            console.log(imsiDto)
        $("#hide").css('visibility','hidden');
        $("#loading").css('visibility', 'visible');
        $.ajax({
               	type: 'POST',
                url: '/getlocation',
               	contentType: 'application/json',
               	dataType: 'json',
               	data: imsiDto,
              	asynch: false,
                cache :false,
               	success: function(response) {
                                        res = response;
                                         $("#loading").css('visibility','hidden');
                                        // window.location = "/FindLocation";
                               		     console.log(response.location.lat);
                               		     console.log(response.location.lng);},
               error: function (response) {
                              var res = JSON.stringify(response);
                                        console.log(response);
                              }
               });
     });
});