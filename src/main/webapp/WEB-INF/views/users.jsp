<%@ page import="by.itacademy.todolist.constants.ApplicationConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

        <div class="col-2">

        </div>


    </div>

    <div class="row" style="margin-bottom: 5vh">

        <div class="col-2">

        </div>

        <div class="col-8 rounded-3 text-center">

            <h3>Admin page</h3>

        </div>

        <div class="col-2">

        </div>


    </div>

    <div class="row ">

        <div class="col-2">

        </div>

        <div class="col-2">

            <c:import url="/WEB-INF/views/template/admin_navigate_template.jsp"/>

        </div>

        <div class="col-6 text-center">
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
                            <td>

                                <c:choose>
                                    <c:when test="${!empty user.name or !empty user.surname}">
                                        <a style="text-decoration: none" class="btn btn-link"
                                           href="<c:url value="/admin/user/${user.id}" />">${user.name} ${user.surname}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a style="text-decoration: none" class="btn btn-link"
                                           href="<c:url value="/admin/block/${user.id}" />">No name</a>
                                    </c:otherwise>
                                </c:choose>

                            </td>

                            <c:choose>
                                <c:when test="${user.roles.contains(applicationScope.adminRole)}">
                                    <td>ADMIN</td>
                                </c:when>
                                <c:otherwise>
                                    <td>USER</td>
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

                            <c:if test="${!user.roles.contains(applicationScope.adminRole)}">

                                <c:choose>
                                    <c:when test="${user.profile.enable}">

                                        <td>
                                            <a class="btn btn-warning btn-sm"
                                               href="<c:url value="/admin/user/block/${user.id}" />">Blocked</a>
                                        </td>
                                    </c:when>
                                    <c:otherwise>

                                        <td>
                                            <a class="btn btn-success btn-sm"
                                               href="<c:url value="/admin/user/unblock/${user.id}" />">Unblock</a>
                                        </td>
                                    </c:otherwise>
                                </c:choose>

                                <td>
                                    <a class="btn btn-danger btn-sm"
                                       href="<c:url value="/admin/user/delete/${user.id}" />">Delete</a>
                                </td>



                            </c:if>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>

            </c:if>

            <c:import url="/WEB-INF/views/template/error_templ.jsp"/>
            <c:import url="/WEB-INF/views/template/successful_template.jsp"/>


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