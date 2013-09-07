<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
    <title>Data centers</title>
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
      
     </script>  
  </head>
    
  <body bgcolor="#FFF000">
   <IMG SRC='/resources/banner1.png'>
    <div class="container">
      <td>Data centers:</td>
        <td>
          <form:select id="vmName" path="vmdropdownvalues" items="${vmdropdownvalues}"/>
         <!-- <a href="#" onClick='window.location.href= redirectUrl("poweroff/")'>Power Off VM</a>-->
         <!-- <a href="#" onClick='window.location.href= redirectUrl("suspend/")'>Suspend VM</a>-->
         <!-- <a href="#" onClick='window.location.href= redirectUrl("powerOn/")'>Power On VM</a>-->
      </td>
      <td>
           <a href="/">Go to home page</a>
        </td>
    
  
  </body>
</html>