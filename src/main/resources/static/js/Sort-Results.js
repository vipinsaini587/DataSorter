$(document).ready(function () {
console.log("page ready");
     $("#rdesc").css('visibility', 'visible');

 //var response = window.localStorage.getItem('response');
var response = JSON.parse(localStorage.getItem("response") || "[]");
  console.log(response);
    var str_array = response;

for(var i = 0; i < str_array.length; i++) {

    var newTable = document.createElement("table");
    newTable.innerHTML = "<thead><th>Landline<br><br></th><th>Source IP</th><th>StartDate IP Allocation</th><th>Start Time IP Allocation</th><th>EndDate IP Allocation</th><th>End Time IP Allocation</th><th>User Id Authentication</th><th>Device Id Number</th><th>IMSE</th><th>PGW IP Address</th><th>CGI Id</th></thead>";
    for(res of response){
        const newRow = document.createElement("tr");
        const Landline = document.createElement("td");
        const SourceIP = document.createElement("td");
        const startDateIpAllocation = document.createElement("td");
        const startTimeIpAllocation = document.createElement("td");
        const endDateIpAllocation = document.createElement("td");
        const endTimeIpAllocation = document.createElement("td");
        //const ipType = document.createElement("td");
        const userIdAuthentication = document.createElement("td");
        const deviceIdNumber = document.createElement("td");
        const imse = document.createElement("td");
        const pgwIpAddress = document.createElement("td");
        const cgiId = document.createElement("td");
        Landline.textContent = str_array[i].landLine;
        SourceIP.textContent = str_array[i].sourceIP;
        startDateIpAllocation.textContent = str_array[i].startDateIpAllocation;
        startTimeIpAllocation.textContent = str_array[i].startTimeIpAllocation;
        endDateIpAllocation.textContent = str_array[i].endDateIpAllocation;
        endTimeIpAllocation.textContent = str_array[i].endTimeIpAllocation;
      //  ipType.textContent = str_array[i].ipType;
        userIdAuthentication.textContent = str_array[i].userIdAuthentication;
        deviceIdNumber.textContent = str_array[i].deviceIdNumber;
        imse.textContent = str_array[i].imse;
        pgwIpAddress.textContent = str_array[i].pgwIpAddress;
        cgiId.textContent = str_array[i].cgiId;
        newRow.appendChild(Landline);
        newRow.appendChild(SourceIP);
        newRow.appendChild(startDateIpAllocation);
        newRow.appendChild(startTimeIpAllocation);
        newRow.appendChild(endDateIpAllocation);
        newRow.appendChild(endTimeIpAllocation);
       // newRow.appendChild(ipType);
        newRow.appendChild(userIdAuthentication);
        newRow.appendChild(deviceIdNumber);
        newRow.appendChild(imse);
        newRow.appendChild(pgwIpAddress);
        newRow.appendChild(cgiId);
        newTable.appendChild(newRow);
    }
     }
    var target = document.getElementById('rdesc');
    target.appendChild(newTable);

   // Clear storage
   window.localStorage.clear();


});