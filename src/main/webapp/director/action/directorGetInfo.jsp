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
            <a class="nav-link active" id="v-pills-director-tab" href="${pageContext.request.contextPath}/info"
               role="tab"
               aria-controls="v-pills-diretor" aria-selected="false">Info</a>
            <a class="nav-link" id="v-pills-permissions-tab" data-toggle="pill" href="#v-pills-permissions" role="tab"
               aria-controls="v-pills-permissions" aria-selected="true">Nadaj uprawnienia</a>
            <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab"
               aria-controls="v-pills-messages" aria-selected="false">Wiadomości</a>
            <a class="nav-link" id="v-pills-logout-tab" href="${pageContext.request.contextPath}/logout" role="tab"
               aria-controls="v-pills-logout" aria-selected="false">Wyloguj</a>
        </div>
    </div>
    <%@ include file="/userGetInfo.jsp" %>
    <div class="col-11">
        <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade" id="v-pills-permissions" role="tabpanel"
                 aria-labelledby="v-pills-permissions-tab">
                Tutaj będą nadawane uprawnienia dla użytkowników
            </div>
            <div class="tab-pane fade" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab">
                Tutaj będą wysyłane uwagi/komunikaty dla użytkowników
            </div>
        </div>
    </div>
</div>