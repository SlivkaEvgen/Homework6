<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name='DC.Language' scheme='rfc1766' content='ru'/>
<!DOCTYPE html>
<html>
<head>
    <style>
        html, body, h1, h2, h3, h4, h5, h6 {
            font-family: "Roboto", sans-serif;
        }

        .w3-display-bottommiddle {
            z-index: 1;
            top: 50%;
            width: 370px;
            line-height: normal;
        }
    </style>
    <title>Sum Salaries</title>
<body>
<jsp:include page="../homeView.jsp"></jsp:include>
<div class="w3-container w3-center w3-round w3-text-hover-light-blue fa fa-remove">
    <h2 style="color:lightskyblue" class="text w3-animate-fading w3-animate-opacity">Sum Salaries</h2>
</div>
<div class="w3-container w3-center">
    <a href="${pageContext.request.contextPath}/bySkillActivity/" class="w3-btn w3-hover-light-blue w3-round-xlarge">By Activity</a>
    <a href="${pageContext.request.contextPath}/bySkillLevel/" class="w3-btn w3-hover-light-blue w3-round-xlarge">By Level</a>
    <a href="${pageContext.request.contextPath}/fromOneProject/" class="w3-btn w3-hover-light-blue w3-round-xlarge">From One Project</a>
</div>
<div class="w3-container w3-center w3-round w3-text-hover-light-blue fa fa-remove">
    <a href="${pageContext.request.contextPath}/developer/" class="w3-btn w3-btn w3-hover-light-blue w3-round-xlarge w3-center">Add New Developer</a>
</div>
<div class="w3-container w3-center w3-round w3-text-hover-light-blue fa fa-remove">
    <a href="${pageContext.request.contextPath}/developer/" class="w3-btn w3-btn w3-hover-light-blue w3-round-xlarge w3-center"></a>
</div>
<form method="POST" action="${pageContext.request.contextPath}/sumSalaries/">
    <nav class="w3-bar-block  w3-center w3-white w3-animate-top w3-card w3-round w3-display-bottommiddle">
        <table class="w3-table-all w3-card-4 w3-small w3-margin-top w3-round w3-centered" border="0">
            <tr>
                <td></td>
                <th>Project</th>
                <th>ID</th>
                <td><label>
                    <input type="text" name="projectId" class="w3-input  w3-hover-light-blue w3-round-xXlarge w3-center"
                           placeholder="Project ID   " value="${projectId}"/>
                </label></td>
                <td colspan="1">
                    <input type="submit" class="w3-input w3-border w3-hover-green w3-round-xlarge w3-light-blue"
                           value="Submit"/>
                </td>
            </tr>
            <table class="w3-padding 16 w3-table-all w3-card-4 w3-small w3-margin-top w3-round w3-centered"
                   id="myTable">
                <tr>
                    <th>Project = Sum Salaries</th>
                </tr>
                <c:forEach items="${sumSalaries}" var="sumSalaries">
                    <tr class="w3-text w3-serif">
                        <td style="background-color:skyblue">${sumSalaries}</td>
                    </tr>
                </c:forEach>
            </table>
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
    </nav>
</form>
</body>
<jsp:include page="/WEB-INF/view/_footer.jsp"></jsp:include>
</html>