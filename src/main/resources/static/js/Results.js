$(document).ready(function () {
console.log("page ready");
 var str_array = JSON.parse(localStorage.getItem("ipwhois") || "[]");
  console.log(str_array);

  var html="<table border='1|1'>"

  setTimeout(()=>{
  html+='<tr>';
  html+='<td>IP<td>';
  html+='<td>new<td>';
  html+='</tr>';
  html+='<tr>';
  html+='<td>'+str_array.ip+'<td>';
  html+='<td>'+str_array.ip+'<td>';
  html+='</tr>';

 document.getElementById("table").innerHTML = html

  },500);

   // Clear storage

//   console.log(response);
//$("#rdesc").append(str_array).css('visibility', 'visible');
window.localStorage.clear();
});