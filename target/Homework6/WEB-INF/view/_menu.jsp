<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name='DC.Language' scheme='rfc1766' content='ru'/>
<style>
    body, h1, h2, h3, h4, h5, h6 {
        font-family: Roboto, sans-serif;
    }
</style>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home page</title>
    <jsp:include page="homeView.jsp"></jsp:include>
</head>
<body>
<a class="w-display-buttommiddle w3-center">
    <h4 class="date-cell w3-center" style="color: cornflowerblue; animation-timing-function: ease-in-out">
        <% out.println(new Date().toString()); %></h4>
</a>
<h4></h4>
<h1><b>
</b></h1>
<bin>
    <h2 class="text-area w3-center w3-display-middle" style="color:indianred">"Make it as simple as possible, but not
        simpler."
    </h2><a class="w3-serif w3-display-bottommiddle w3-display-hover" style="color:black"></a>
</bin>
<bin>
    <p class="text-area w3-center w3-display-bottommiddle">
        /
        <h8><b class="font-menu-button w3-center">GITHUB</b></h8>
        /
        <h8><b class="font-menu-button ">HTTP </b></h8>
        /
        <h8><b class="font-menu-button ">JAVA</b></h8>
        /
        <h8><b class="font-menu-button ">TOMCAT</b></h8>
        /
        <h8><b class="font-menu-button ">JSP</b></h8>
        /
        <h8><b class="font-menu-button ">JDBC</b></h8>
        /
        <h8><b class="font-menu-button ">WEB</b></h8>
        \
        <h8><b class="font-menu-button ">SERVLET</b></h8>
        \
        <h8><b class="font-menu-button ">HTML</b></h8>
        \
        <h8><b class="font-menu-button ">SCRIPT</b></h8>
        \
        <h8><b class="font-menu-button ">IDEA</b></h8>
        \
        <h8><b class="font-menu-button ">MySQL</b></h8>
        \
        <h8><b class="font-menu-button ">YOUTUBE</b></h8>
        /
        <h8><b class="font-menu-button ">SLACK</b></h8>
        /
        <h8><b class="font-menu-button ">MAVEN</b></h8>
        \
        <h8><b class="font-menu-button ">APACHE</b></h8>
        \
        <h8><b class="font-menu-button ">MVC</b></h8>
        \
    <p class="text-area"><a><b class="font-menu-button w3-display-bottommiddle w3-red">GO-IT</b></a></p>

</bin>
<footer class="w3-container w3-padding-48 w3-center w3-opacity w3-white w3-display-bottommiddle">- Albert Einstein -
    <p> &copy;Copyright <a href="https://github.com/SlivkaEvgen/JpaServletsMVChw6" target="_blank">Slivka</a></p>
</footer>
</body>
</html>