<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="r" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false" %>
<%@ page %>

<!doctype html>
<html lang="pl">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Gestor - Twój elektroniczny dziennik</title>
</head>
<body>

<%@ include file="../navBarHorizontal.jsp" %>

<c:set value="${sessionScope.membershipId}" var="membershipId"/>
<c:set value="1" var="student"/>
<c:set value="2" var="teacher"/>
<c:set value="3" var="parent"/>
<c:set value="4" var="director"/>

<c:choose>
    <c:when test="${student eq membershipId}">
        <%@ include file="../student/studentDefault.jsp" %>
    </c:when>
    <c:when test="${teacher eq membershipId}">
        <%@ include file="teacherAddGrade.jsp" %>
    </c:when>
    <c:when test="${parent eq membershipId}">
        <%@ include file="../parent/parentDefault.jsp" %>
    </c:when>
    <c:when test="${director eq membershipId}">
        <%@ include file="../director/directorDefault.jsp" %>
    </c:when>
    <c:otherwise>
        <%@ include file="../navBarVertical.jsp" %>
    </c:otherwise>
</c:choose>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>




