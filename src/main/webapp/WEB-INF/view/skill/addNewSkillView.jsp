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
            z-index: 2;
            width: 300px;
            line-height: initial;
        }
    </style>
    <title>NEW Skill</title>
<body>
<jsp:include page="../homeView.jsp"></jsp:include>
<div class="w3-container w3-center w3-round w3-text-hover-light-blue fa fa-remove">
    <h1 style="color:lightskyblue" class="text w3-animate-fading w3-animate-opacity">NEW Skill</h1>
</div>
<form class="container m3-center" method="POST" action="${pageContext.request.contextPath}/skill/">
    <nav class="w3-bar-block  w3-light-grey w3-animate-top w3-card w3-round w3-display-bottommiddle">
        <table class="w3-table-all w3-card-4 w3-small w3-margin-top w3-round w3-centered" id="myTable">
            <tr>
                <td>ID</td>
                <td><label>
                    <input type="text" name="id" placeholder=" ID " value="${skill.id}"/>
                </label></td>
            </tr>
            <tr>
                <td>Activity</td>
                <td><label>
                    <input type="text" name="activity" placeholder=" Java , JS, C+, C# " value="${skill.activity}"/>
                </label>
                </td>
            </tr>
            <tr>
                <td>Level</td>
                <td><label>
                    <input type="text" name="level" placeholder=" Junior , Middle, Senior " value="${skill.level}"/>
                </label>
                </td>
            </tr>
            <tr>
                <td><a href="${pageContext.request.contextPath}/all/skill/" class="w3-btn w3-hover-red w3-round-xlarge">Cancel</a></td>
                <td colspan="1">
                    <input type="submit" class="w3-input w3-border w3-hover-green w3-round-xlarge w3-light-blue"
                           value="Submit"/>
                </td>
            </tr>
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