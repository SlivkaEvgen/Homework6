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
    <jsp:include page="_header.jsp"></jsp:include>
</head>
<body>
<div class="w3-container w3-center w3-xlarge">
    <div class="w3-container w3-center w3-xlarge w3-text-blue">
        <a href="${pageContext.request.contextPath}/" class="w3-btn w3-hover-light-blue w3-round-xlarge "
           style="text-align-all: center ">Home</a>
    </div>
    <a href="${pageContext.request.contextPath}/all/developer/" class="w3-btn w3-hover-light-blue w3-round-xlarge">Developer</a>
    <a href="${pageContext.request.contextPath}/all/project/" class="w3-btn w3-hover-light-blue w3-round-xlarge">Project</a>
    <a href="${pageContext.request.contextPath}/all/company/" class="w3-btn w3-hover-light-blue w3-round-xlarge">Company</a>
    <a href="${pageContext.request.contextPath}/all/customer/" class="w3-btn w3-hover-light-blue w3-round-xlarge">Customer</a>
    <a href="${pageContext.request.contextPath}/all/skill/" class="w3-btn w3-hover-light-blue w3-round-xlarge">Skill</a>
</div>
</body>
</html>
