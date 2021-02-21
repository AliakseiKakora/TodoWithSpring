<%@ page import="by.itacademy.todolist.model.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light ">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">ToDo</a>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link" href="#">My profile</a>
                </li>

                <c:if test="${sessionScope.user.roles.contains(Role.ADMIN)}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/"/>">Admin Page</a>
                    </li>
                </c:if>

            </ul>

            <ul class="navbar-nav ms-auto mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/">
                        <c:param name="command" value="Logout"/>
                        </c:url> ">
                        Log out</a>
                </li>
            </ul>

        </div>
    </div>
</nav>
