<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/main"> </c:url>">ToDo</a>

        <div class="collapse navbar-collapse">
            <ul class="navbar-nav mb-2 mb-lg-0">

                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/profile"> </c:url>">My profile</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown">
                        My tasks
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="<c:url value="/tasks/today" /> ">Today</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/tasks/tomorrow" /> ">Tomorrow</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/tasks/someDay" /> ">Some day</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/tasks/fixed" /> ">Fixed</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/tasks/deleted" /> ">Deleted</a></li>
                    </ul>
                </li>

                <c:if test="${sessionScope.user.roles.contains(applicationScope.adminRole)}">
                    <li class="nav-item">

                        <a class="nav-link" href="<c:url value="/"> <c:param name="command" value="AdminPageView"/> </c:url>">Admin Page</a>

                    </li>
                </c:if>

            </ul>

            <ul class="navbar-nav ms-auto mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/logout"/>">Log out </a>
                </li>
            </ul>

        </div>
    </div>
</nav>
