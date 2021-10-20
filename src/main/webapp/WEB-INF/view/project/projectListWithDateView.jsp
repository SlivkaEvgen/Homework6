<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name='DC.Language' scheme='rfc1766' content='ru'/>
<!DOCTYPE html>
<html>
<style>
    html, body, h1, h2, h3, h4, h5, h6 {
        font-family: "Roboto", sans-serif;
    }

    .w3-table-all {
        z-index: 1;
        width: 50px;
        line-height: initial;
    }
</style>
<meta charset="UTF-8">
<title>Project List With Date</title>
<body>
<jsp:include page="../homeView.jsp"></jsp:include>
<div class="w3-container w3-center w3-round w3-text-hover-light-blue fa fa-remove">
    <h2 style="color:lightskyblue" class="text w3-animate-fading w3-animate-opacity ">Project List With Date</h2>
    <a href="${pageContext.request.contextPath}/project/" class="w3-btn w3-btn w3-hover-light-blue w3-round-xlarge w3-center">Add New Project</a>
</div>
<form method="Get" action="${pageContext.request.contextPath}/projectWithDate/">
    <nav class="w3-bar-block  w3-center w3-white w3-animate-top w3-card w3-round w3-display-bottommiddle">
        <table class="w3-hoverable w3-padding 16 w3-table-all w3-card-4 w3-margin-top w3-round w3-centered"
               id="myTable">
            <tr>
                <th>List Projects</th>
            </tr>
            <c:forEach items="${projectsWithDate}" var="string">
                <tr>
                    <td>${string}</td>
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
    </nav>
</form>
<jsp:include page="/WEB-INF/view/_footer.jsp"></jsp:include>
</body>
</html>