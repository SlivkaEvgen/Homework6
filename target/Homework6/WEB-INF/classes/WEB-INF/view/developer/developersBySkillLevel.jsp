<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name='DC.Language' scheme='rfc1766' content='ru'/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>By Skill Level</title>
</head>
<body>
<jsp:include page="../homeView.jsp"></jsp:include>
<div class="w3-container w3-center w3-round w3-text-hover-light-blue fa fa-remove">
    <h2 style="color:lightskyblue" class="text w3-animate-fading w3-animate-opacity">Developers By Skill Level</h2>
</div>
<div class="w3-container w3-center">
    <a href="${pageContext.request.contextPath}/bySkillActivity/" class="w3-btn w3-hover-light-blue w3-round-xlarge">By Activity</a>
    <a href="${pageContext.request.contextPath}/fromOneProject/" class="w3-btn w3-hover-light-blue w3-round-xlarge">From One Project</a>
    <a href="${pageContext.request.contextPath}/sumSalaries/" class="w3-btn w3-hover-light-blue w3-round-xlarge">Sum Salaries</a>
</div>
<div class="w3-container w3-center w3-round w3-text-hover-light-blue fa fa-remove">
    <a href="${pageContext.request.contextPath}/developer/" class="w3-btn w3-btn w3-hover-light-blue w3-round-xlarge w3-center">Add New Developer</a>
</div>
<form method="POST" action="${pageContext.request.contextPath}/bySkillLevel/">
    <table border="0">
        <tr>
            <td></td>
            <th>By Skill Level</th>
            <td><label>
                <input type="text" name="level" class="w3-input  w3-hover-light-blue w3-round-xXlarge w3-center"
                       placeholder="ex: Junior , Middle , Senior " value="${skill.level}"/>
            </label></td>
            <td colspan="1">
                <input type="submit" class="w3-input w3-border w3-hover-green w3-round-xlarge w3-light-blue"
                       value="Submit"/>
            </td>
        </tr>
        <table class="w3-hoverable w3-padding 16 w3-table-all w3-card-4 w3-small w3-margin-top w3-round w3-centered"
               id="myTable">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Email</th>
                <th>Salary</th>
                <th>Company ID</th>
                <th>Update</th>
                <th>Delete</th>

            </tr>
            <c:forEach items="${list}" var="developer">
                <tr>
                    <td>${developer.id}</td>
                    <td>${developer.name}</td>
                    <td>${developer.age}</td>
                    <td>${developer.gender}</td>
                    <td>${developer.email}</td>
                    <td>${developer.salary}</td>
                    <td>${developer.companyId}</td>
                    <td>
                        <a class="w3-btn w3-btn w3-hover-light-blue w3-round-xlarge"
                           href="${pageContext.request.contextPath}/developer/developer?id=${developer.id}">Update</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/delete/developer?id=${developer.id}" class="w3-btn w3-btn w3-hover-red w3-round-xlarge">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <h4 style="text-align-all: center" class="text w3-red w3-round w3-margin-top w3-center">
                <c:out value="${error}" default=""></c:out>
            </h4>
        </div>
        <div>
            <h4 style="text-align-all: center; background-color:skyblue" class="text w3-round w3-margin-top w3-center">
                <c:out value="${error2}" default=""></c:out>
            </h4>
        </div>
        <jsp:include page="/WEB-INF/view/_footer.jsp"></jsp:include>
</body>
</html>