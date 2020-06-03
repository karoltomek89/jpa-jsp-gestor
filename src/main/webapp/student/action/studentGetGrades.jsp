<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="r" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page isELIgnored="false" %>
<%@ page %>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>

<div class="row">
    <div class="col-1">
        <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a class="nav-link" id="v-pills-student-tab" href="${pageContext.request.contextPath}/info" role="tab"
               aria-controls="v-pills-student" aria-selected="false">Info</a>

            <a class="nav-link  active" id="v-pills-grades-tab" href="${pageContext.request.contextPath}/showgrades"
               role="tab" aria-controls="v-pills-grades" aria-selected="true">Oceny</a>

            <a class="nav-link" id="v-pills-notes-tab" data-toggle="pill" href="#v-pills-notes" role="tab"
               aria-controls="v-pills-notes" aria-selected="false">Uwagi</a>
            <a class="nav-link" id="v-pills-messages-tab" href="${pageContext.request.contextPath}/messages" role="tab"
               aria-controls="v-pills-messages" aria-selected="false">Wiadomości</a>
            <a class="nav-link" id="v-pills-homework-tab" data-toggle="pill" href="#v-pills-homework" role="tab"
               aria-controls="v-pills-homework" aria-selected="false">Prace domowe</a>
            <a class="nav-link" id="v-pills-logout-tab" href="${pageContext.request.contextPath}/logout" role="tab"
               aria-controls="v-pills-logout" aria-selected="false">Wyloguj</a>
        </div>
    </div>

    <div class="col-11">
        <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade show active" id="v-pills-grades" role="tabpanel"
                 aria-labelledby="v-pills-grades-tab">


                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Przedmiot</th>
                        <th scope="col">Ocena</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="item" items="${grades}">
                        <tr>
                            <th scope="row">${item.name}</th>
                            <td>${item.value}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>


            </div>
        </div>
    </div>
</div>