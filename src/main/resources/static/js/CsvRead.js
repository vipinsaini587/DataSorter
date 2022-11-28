$(document).ready(function () {
console.log("page is ready");

var responseData;
    event.preventDefault();
    let caseNumber = document.getElementById('case_number').value;
    let officerName = document.getElementById('investigating_officer').value;
//    let caseDetails = {caseName,investigating_officer};
//    console.log(caseDetails);
//    let caseData  = JSON.stringify(caseDetails);
//    console.log(caseDetails);
    $("#upload").on("submit", function (event) {
    var form = $('#upload')[0];
    var data = new FormData(form);
    console.log(data);
//    var caseNumber = data.append(caseDetails);
    console.log(caseNumber);
       $.ajax({
               url: "/csvupload",
               data: data,
               type: 'POST',
               enctype: 'multipart/form-data',
               processData: false,
               contentType: false,
               cache: false,
               timeout: 600000,
               success: function() {
                        console.log(response);
                        window.location = "/";
                       ,
               error: function () {
//               window.location = "/ip";
               console.log("error");
               }
    });

      });
});
