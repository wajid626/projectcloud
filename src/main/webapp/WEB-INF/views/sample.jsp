<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
    <title>Virtual Machines</title>
    <link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
    <link rel="stylesheet" href="<c:url value="/resources/popup.css" />" type="text/css" media="screen, projection">
    <script type="text/javascript" src="<c:url value="/resources/jquery-1.4.min.js" /> "></script>
    <script type="text/javascript" src="<c:url value="/resources/json.min.js" /> "></script>
    <script type="text/javascript">
     function redirectUrl(status)
     {
        var vmName = $('#vmName').val();
        return "/status/changeStatus/".concat(status).concat(vmName);
     }
     function updateStatusMsg()
     {
        var vmName = $('#vmName').val();

       $.getJSON("vmstatus", { vmname: $('#vmName').val() }, function(data) {
         
         document.getElementById("message").innerHTML = data.message;
        
      });
      
     }$( "#rename" )
      .href()
      .click(function() {
        $( "#dialog-form" ).dialog( "open" );
      });
  });
      
      
     </script>  
  </head>
    
  <body bgcolor="#FFF000">
    <IMG SRC='/resources/banner1.png' onClick="/">
    <div class="container">
      <td>VMs:
       
          <form:select id="vmName" path="vmdropdownvalues" items="${vmdropdownvalues}" onChange='updateStatusMsg();'/><br> 
        <form:label id="message" for="message"  path="message"> ${message}</form:label>
         </td><br>
    <td>
          
          <a href="#" style="text-decoration: none;color:blue;font-size:15px" onClick='window.location.href= redirectUrl("clone/")'> &nbsp; Clone VM  &nbsp; </a>
          <a href="#" style="text-decoration: none;color:ginger brown;font-size:15px;" onClick='window.location.href= redirectUrl("clone/")'> &nbsp; Remove VM  &nbsp; </a> 
           <a href="#" id="rename" style="text-decoration: none;color:cyan;font-size:15px" > &nbsp; Rename VM  &nbsp; </a>
             <p>
              <form:input />
            </p>
      <br>
          <a href="#" style="text-decoration: none;color:red;font-size:15px" onClick='window.location.href= redirectUrl("poweroff/")'>&nbsp; Power Off VM   &nbsp; </a>
          <a href="#" style="text-decoration: none;color:orange;font-size:15px" onClick='window.location.href= redirectUrl("suspend/")'>&nbsp; Suspend VM   &nbsp; &nbsp;</a>
          <a href="#" style="text-decoration: none;color:green;font-size:15px" onClick='window.location.href= redirectUrl("powerOn/")'>&nbsp; Power On VM  &nbsp; &nbsp;</a><br>
      </td><br>
           <a href="/">Go to home page</a>
        
  
  
  </body>
</html>