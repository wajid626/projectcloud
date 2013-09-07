<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
  <head>
    <title>View Vms By State</title>
    <link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
    <link rel="stylesheet" href="<c:url value="/resources/popup.css" />" type="text/css" media="screen, projection">
    <script type="text/javascript" src="<c:url value="/resources/jquery-1.4.min.js" /> "></script>
    <script type="text/javascript" src="<c:url value="/resources/json.min.js" /> "></script>
    <script type="text/javascript">
   
     </script>  
  </head>
    
  <body>
    <IMG SRC='/resources/banner1.png' onClick="alert('hi')">

    <div  class='container' style="width:70%;align">
      <div><font size=3>&#9;&#9;&#9;&#9;&#9;Running</font></div>
          <table style="border: 1px solid #000;">
            <c:forEach items="${runningval}" var="record" varStatus = "status">
           <tr><td width="200" ><c:out value="${record.vmname}" /> </td>
           <td width="200"><c:out value="${record.guestosname}" /></td>
           <td width="200"><c:out value="${record.memory}" /></td>
           <td width="200"><c:out value="${record.cpu}" /></td>
           </tr>
       </c:forEach>
    </table>
          </div>
     </div>
    <div  class='container' style="width:70%">
      <div><font size=3>&#9;&#9;&#9;&#9;&#9;Suspended</font></div>
          <table style="border: 1px solid #000;">
           <c:forEach items="${suspendedval}" var="record" varStatus = "status">
           <tr><td width="200" ><c:out value="${record.vmname}" /> </td>
           <td width="200"><c:out value="${record.guestosname}" /></td>
           <td width="200"><c:out value="${record.memory}" /></td>
           <td width="200"><c:out value="${record.cpu}" /></td>
           </tr>
        </c:forEach>
    </table>
          </div>
     </div>
      <div class='container' style="width:70%">
          <div><font size=3>&#9;&#9;&#9;&#9;&#9;PoweredOff</font></div>
          <table style="border: 1px solid #000;">
           
                     <c:forEach items="${poweredoffval}" var="record" varStatus = "status">
           <tr><td width="200" ><c:out value="${record.vmname}" /> </td>
           <td width="200"><c:out value="${record.guestosname}" /></td>
           <td width="200"><c:out value="${record.memory}" /></td>
           <td width="200"><c:out value="${record.cpu}" /></td>
           </tr>
        </c:forEach>
    </table>
          </div>
     </div>
  
     
           <a href="/">Go to home page</a>
     
  </body>
</html>