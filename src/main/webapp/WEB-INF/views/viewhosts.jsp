<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
    <title>Host Systems</title>
    <link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
    <link rel="stylesheet" href="<c:url value="/resources/popup.css" />" type="text/css" media="screen, projection">
    <script type="text/javascript" src="<c:url value="/resources/jquery-1.4.min.js" /> "></script>
    <script type="text/javascript" src="<c:url value="/resources/json.min.js" /> "></script>
    <script type="text/javascript">
     function redirectUrl()
     {
        var vmName = $('#vmName').val();
       return "/vm/hostsummary/".concat(vmName);
     }
     function updateHostSummary()
     {
        var vmName = $('#vmName').val();
       $.getJSON("/vm/hostsummary", { hoststr: $('#vmName').val() }, function(data) {
         var html = '';
         $.each(data.mapval, function(key, val) {
           html = html + '<tr>' + '<td>' + key + '</td><td>' + val + '</td>'+'</tr>';
        });
         //alert(html);
         // $('#summarytable').val(html);
         document.getElementById("summarytable").innerHTML = html;
       }); 
     }
     </script>  
  </head>
    
  <body bgcolor="#FFF000">
   <IMG SRC='/resources/banner1.png'>
    <div class="container">
      <td>Hosts:</td>
        <td>
          <form:select id="vmName" path="vmdropdownvalues" items="${vmdropdownvalues}" onChange='updateHostSummary();'/>
         <br>
           <div><font size=3>&#9;&#9;&#9;&#9;&#9;Host System Summary</font></div>
          <table id="summarytable" style="border: 1px solid #000;">
           
           <c:forEach items="${mapval}" var="mapval">
            <tr>
                <td>${mapval.key}</td>
                <td>${mapval.value}</td>
            </tr>
        </c:forEach>
        </table>
           <a href="/">Go to home page</a>
        </td>
    
  
  </body>
</html>