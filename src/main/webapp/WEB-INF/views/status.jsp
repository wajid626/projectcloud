<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
    <title>Create Account</title>
    <link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
    <link rel="stylesheet" href="<c:url value="/resources/popup.css" />" type="text/css" media="screen, projection">
    <script type="text/javascript" src="<c:url value="/resources/jquery-1.4.min.js" /> "></script>
    <script type="text/javascript" src="<c:url value="/resources/json.min.js" /> "></script>
  </head>
  <body>
    <IMG SRC='/resources/banner1.png' onClick="/">
    <div class="container">
     
        <td>
          status: <form:label path="message">${message}</form:label>
      </td>
      <td>
           <a href="/vm/vms">go back</a>
        </td>
    </div>    
  </body>
</html>