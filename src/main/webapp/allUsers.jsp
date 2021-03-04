<%@ page import="by.itacademy.todolist.model.Role" %>
<%@ page import="by.itacademy.todolist.constants.ApplicationConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">



    <title>ToDo</title>
</head>
<body>



<div class="container-liquid " style="margin-bottom: 5vh">
    <div class="row" style="margin-bottom: 5vh">

        <div class="col-2">

        </div>

        <div class="col-8 rounded-3">

            <c:import url="/WEB-INF/template/header_templ.jsp"/>

        </div>

        <div class="col-2" >

        </div>


    </div>

    <div class="row" style="margin-bottom: 5vh">

        <div class="col-2">

        </div>

        <div class="col-8 rounded-3 text-center">

            <h3>Admin page</h3>

        </div>

        <div class="col-2" >

        </div>


    </div>

    <div class="row ">

        <div class="col-2">

        </div>

        <div class="col-2">

            <div class="">

                <form action="<c:url value="/"> <c:param name="command" value="AllUsers"/> </c:url>" method="post">
                    <input type="submit" class="btn btn-outline-primary form-control" value="Show all users">
                </form>

            </div>

        </div>

        <div class="col-6 text-center" >
            <c:if test="${!empty sessionScope}">

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Role</th>
                        <th scope="col">Status</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${requestScope.users}" var="user">
                        <tr>
                            <td>${user.name} ${user.surname}</td>

                            <c:choose>
                                <c:when test="${user.roles.contains(Role.ADMIN)}">
                                    <td>${Role.ADMIN}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${Role.USER}</td>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${user.profile.enable}">

                                    <td>${ApplicationConstants.USER_STATUS_ACTIVE}</td>

                                </c:when>
                                <c:otherwise>

                                    <td>${ApplicationConstants.USER_STATUS_BLOCKED}</td>

                                </c:otherwise>
                            </c:choose>

                            <c:if test="${sessionScope.user.id != user.id}">

                                <c:choose>
                                    <c:when test="${user.profile.enable}">

                                        <td>
                                            <form action="<c:url value="/"> <c:param name="command" value="UpdateUser"/> </c:url>" method="post">
                                                <input name="${ApplicationConstants.USER_ID_KEY}" type="hidden" value="${user.id}">
                                                <input name="${ApplicationConstants.ACTION_KEY}" type="hidden" value="${ApplicationConstants.USER_ACTION_BLOCK}">
                                                <input class="btn btn-warning btn-sm" type="submit" value="Blocked">
                                            </form>
                                        </td>
                                    </c:when>
                                    <c:otherwise>

                                        <td>
                                            <form action="<c:url value="/"> <c:param name="command" value="UpdateUser"/> </c:url>" method="post">
                                                <input name="${ApplicationConstants.USER_ID_KEY}" type="hidden" value="${user.id}">
                                                <input name="${ApplicationConstants.ACTION_KEY}" type="hidden" value="${ApplicationConstants.USER_ACTION_UNBLOCK}">
                                                <input class="btn btn-success btn-sm" type="submit" value="Unblock">
                                            </form>
                                        </td>
                                    </c:otherwise>
                                </c:choose>

                                <td>
                                    <form action="<c:url value="/"> <c:param name="command" value="DeleteUser"/> </c:url>" method="post">
                                        <input name="${ApplicationConstants.USER_ID_KEY}" type="hidden" value="${user.id}">
                                        <input class="btn btn-danger btn-sm" type="submit" value="Delete">
                                    </form>
                                </td>

                            </c:if>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>

            </c:if>


        </div>

        <div class="col-2">

        </div>


    </div>

</div>









<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
        integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js"
        integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj"
        crossorigin="anonymous"></script>

</body>

</html>