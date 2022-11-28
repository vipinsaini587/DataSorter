$(document).ready(function () {
console.log("page is ready");

        $("#sourceIP").on("submit", function (event) {
//      stop submit the form, we will post it manually.
        event.preventDefault();
        var ip = {}
            ip["sourceIP"] = $('input[name=sourceIP]').val();
        var ipData = ip.sourceIP;
            console.log(ipData);
        $("#hide").css('visibility','hidden');
        $("#loading").css('visibility', 'visible');
               $.ajax({
               	method: 'GET',
//              contentType: 'application/json',
               	url: 'sourceIP/'+ipData,
               	dataType: 'json',
               	success: function(response) {
//                      	console.log(url);
               	 var res = JSON.stringify(response);
                           window.localStorage.setItem('response', res);
                           console.log(response);
                           window.location = "/sort_results";
                           }
               });
      });

//             find by start time of ip allocation start here

               $("#startdate").on("submit", function (event) {
//             stop submit the form, we will post it manually.
               event.preventDefault();
               var start_Date = {}
               start_Date["startDateIpAllocation"] = $('input[name=startDateIpAllocation]').val();
               var start_DateData = JSON.stringify(start_Date.startDateIpAllocation);
               console.log(start_DateData);
               $("#hide").css('visibility','hidden');
               $("#loading").css('visibility','visible');
                     $.ajax({
                     	type: 'POST',
                     	contentType: 'application/json',
                     	url: 'startDate',
                     	data: start_DateData,
                     	dataType: 'json',
                     	asynch: false,
                     	cache :false,
                     	success: function(response) {
                     	console.log(response);
                     	var res = JSON.stringify(response);
                                 window.localStorage.setItem('response', res);
                                 console.log(response);
                                 window.location = "/sort_results";
                                  }
                     });
            });

//    find all

                   $("#Find_All").on("submit", function (event) {
//                        stop submit the form, we will post it manually.
                          event.preventDefault();
                          $("#hide").css('visibility','hidden');
                          $("#loading").css('visibility', 'visible');
                                   $.ajax({
                                 	method: 'GET',
//                                 	contentType: 'application/json',
                                 	url: '/getAll',
                                 	dataType: 'json',
                                 	success: function(response) {
//                                        	 console.log(url);
                                   var res = JSON.stringify(response);
                                             window.localStorage.setItem('response', res);
                                             console.log(response);
                                             window.location = "/sort_results";
                                              }
                                 });
                        });
});
