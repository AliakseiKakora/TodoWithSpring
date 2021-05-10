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

            <c:import url="/WEB-INF/views/template/header_templ.jsp"/>

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

            <c:import url="/WEB-INF/views/template/admin_navigate_template.jsp"/>

        </div>

        <div class="col-6" >


            <div class="card">
                <div class="card-header">
                    <ul class="nav nav-pills card-header-pills">

                        <c:if test="${!user.roles.contains(applicationScope.adminRole)}">
                            <li>
                                <a class="btn btn-danger btn-sm ms-2 mb-0" href="<c:url value="/admin/user/delete/${user.id}" />">Delete</a>
                            </li>

                            <li class="nav-item">
                                <c:choose>
                                    <c:when test="${requestScope.user.profile.enable}">

                                        <td>
                                            <a class="btn btn-warning btn-sm ms-2 mb-0" href="<c:url value="/admin/user/block/${user.id}" />">Blocked</a>
                                        </td>
                                    </c:when>
                                    <c:otherwise>

                                        <td>
                                            <a class="btn btn-success btn-sm ms-2 mb-0" href="<c:url value="/admin/user/unblock/${user.id}" />">Unblock</a>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </c:if>
                    </ul>
                </div>

                <div class="card-body">
                    <h4 class="card-title">${requestScope.user.name} ${requestScope.user.surname}</h4>
                    <c:if test="${empty requestScope.user.name or empty requestScope.user.surname}">
                        <h4 class="card-title">No name</h4>
                    </c:if>


                    <p class="card-text"><strong>Email:</strong> ${requestScope.user.email}</p>
                    <p class="card-text"><strong>Login:</strong> ${requestScope.user.profile.login}</p>
                    <p class="card-text"><strong>Password:</strong> ${requestScope.user.profile.password}</p>

                    <p class="card-text"> <strong>Role:</strong>
                        <c:choose>
                            <c:when test="${requestScope.user.roles.contains(applicationScope.adminRole)}">
                                <td>ADMIN</td>
                            </c:when>
                            <c:otherwise>
                                <td>USER</td>
                            </c:otherwise>
                        </c:choose>
                    </p>

                    <p class="card-text"><strong>Status:</strong>
                        <c:choose>
                            <c:when test="${requestScope.user.profile.enable}">

                                <td>${ApplicationConstants.USER_STATUS_ACTIVE}</td>

                            </c:when>
                            <c:otherwise>

                                <td>${ApplicationConstants.USER_STATUS_BLOCKED}</td>

                            </c:otherwise>
                        </c:choose>

                    </p>

                </div>
            </div>



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