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

<div class="col-11">
    <div class="tab-content" id="v-pills-tabContent">
        <div class="tab-pane fade show active" id="v-pills-user" role="tabpanel"
             aria-labelledby="v-pills-user-tab">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">Pole</th>
                    <th scope="col">Wartość</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Imię</td>
                    <td>${user.name}</td>
                </tr>
                <tr>
                    <td>Nazwisko</td>
                    <td>${user.surname}</td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <td>Grupa</td>
                    <td>${user.membershipType}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>