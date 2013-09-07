<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
  <head>
 <style type="text/css">
  
   
  .wrap {
width: 100%;
}

.floatleft, .floatright {
    float:left;
    width: 50%;
    background-color:#C0C0C0;
    height: 400px;
}

.floatright {
    background-color:#C0C0C0;
    width: 50%;
}
   </style> 
    
<script language="javascript" >

    function displayConfiguration1()
    {
      var guestOsType = new String(document.getElementById("sel").value);
     if( guestOsType == "Windows XP"){
       document.getElementById("guestOSconfiguration").innerHTML= "RAM: 512MB / Disk: 8GB / CPU";
    }
           if( guestOsType == "Windows 2000"){
       document.getElementById("guestOSconfiguration").innerHTML= "RAM: 512MB / Disk: 10GB / CPU";
    }
           if( guestOsType == "Linux"){
       document.getElementById("guestOSconfiguration").innerHTML= "RAM: 1024MB/ Disk: 12GB / CPU";
    }
           if( guestOsType == "Solaris"){
       document.getElementById("guestOSconfiguration").innerHTML= "RAM: 1024MB / Disk:10GB / CPU";
    }
    }
  
    function displayConfiguration2()
    {
      var guestOsType = new String(document.getElementById("vm").value);
    
       document.getElementById("vmconfig").innerHTML= "RAM: 512MB / Disk: 8GB / CPU";
    
    }
  
  function tog(elId){
  document.getElementById(elId).checked = false;
  }
  
  function showValue(field, newValue)
{
  document.getElementById(field).innerHTML=newValue;
}
  
  
  <script type="text/javascript">
function createXMLHttpRequest(){
  // See http://en.wikipedia.org/wiki/XMLHttpRequest
  // Provide the XMLHttpRequest class for IE 5.x-6.x:
  if( typeof XMLHttpRequest == "undefined" ) XMLHttpRequest = function() {
    try { return new ActiveXObject("Msxml2.XMLHTTP.6.0") } catch(e) {}
    try { return new ActiveXObject("Msxml2.XMLHTTP.3.0") } catch(e) {}
    try { return new ActiveXObject("Msxml2.XMLHTTP") } catch(e) {}
    try { return new ActiveXObject("Microsoft.XMLHTTP") } catch(e) {}
    throw new Error( "This browser does not support XMLHttpRequest." )
  };
  return new XMLHttpRequest();
}

var AJAX = createXMLHttpRequest();

function handler() {
  if(AJAX.readyState == 4 && AJAX.status == 200) {
      var json = eval('(' + AJAX.responseText +')');
      alert('Success. Result: name => ' + json.name + ',' + 'balance => ' + json.balance);
  }else if (AJAX.readyState == 4 && AJAX.status != 200) {
    alert('Something went wrong...');
  }
}

function show(){
  AJAX.onreadystatechange = handler;
  AJAX.open("GET", "vm/vmlist");
  AJAX.send("");
}
  
  
</script>
    
      <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <title><fmt:message key="welcome.title"/></title>
  <link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
  <link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
 
</head>
<body bgcolor="#FF0000">
  <IMG SRC='/resources/banner1.png'><!--<H1><CENTER>Cloud Space</CENTER><H2><P>Welcome to Cloud Space</P></H2>-->
<form name="form" method="post" >
  <div class="wrap">
  <div class="floatleft">
<td><b>Select Guest OS template:</b>&nbsp;</td>
<select name="sel" id="sel" onChange="displayConfiguration1()"><option value=""><---Select---></option>
  <%
   java.util.ArrayList<String> guestOSList = new ArrayList<String>();
   guestOSList.add("Windows 2000");
   guestOSList.add("Linux");
   guestOSList.add("Solaris");
   guestOSList.add("Windows XP");
   for(String os: guestOSList)
   {
       System.out.println("Adding OS ---->" + os);
  %>
<option value="<%= os%>">
  <% out.println(os);%>
</option>
  <%}
  %>
</select><br>
  <input type="radio" checked id='radio1' onClick="tog('radio2')" />
<label>Default Configuration</label>
<textbox name="guestOSconfiguration" id="guestOSconfiguration" ></textbox>
  <br>
   <input type="radio" id='radio2' onClick="tog('radio1')" />
  <label>Custom Configuration</label>
  <br>
  <label>RAM</label>
  <span id="range1">0</span>
   <input type="range" id="range1" min="1" max="10" onchange="showValue('range1', this.value)">
  <br>
     <label>Disk space</label>
   <span id="range2">0</span>
   <input type="range" id="range2" min="1" max="10" onchange="showValue('range2', this.value)">
  <br> 
  
    <label>CPU</label>
  <span id="range3">0</span>
   <input type="range" id="range3" min="1" max="10" onchange="showValue('range3', this.value)">
  <br>
 <input type="submit" value="Create Virtual Machine"/><br>
  </div>
      <div class="floatright">
        <label>Inventory</label>
        <select name="vm" id="vm" onChange="displayConfiguration2()">
  <%
   java.util.ArrayList<String> vmList= new ArrayList<String>();
   vmList.add("Windows 2000 VM");
   vmList.add("Linux VM");
   vmList.add("Solaris VM");
   vmList.add("Windows XP VM");
   for(String os: vmList)
   {
       System.out.println("Adding OS ---->" + os);
  %>
<option value="<%= os%>">
  <% out.println(os);%>
</option>
  <%}
  %>
         
</select>
           <textbox  name="vmconfig" id="vmconfig"  ></textbox>
          <br>
          <label>VM Current State:  </label><label>Running</label><br>
          
           <input type="button" value="Power On"/>
           <input type="button" value="Power off"/>
           <input type="button" value="Suspend"/>
           <input type="button" value="Remove VM"/>
           <input type="button" value="Cloud Burst"/>
          
        
  </div>
</div>
  
</form>
<%String option=request.getParameter("sel");
if(option==null){
}
else{
out.println("You have selected: <b>"+option+"</b>"+"<br>");
}
%>
  </body>
</html>
