<%@ page import="java.time.format.DateTimeFormatter" %>
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

<div class="container-liquid ">
    <div class="row " style="height:100vh">

        <div class="col-2">

        </div>

        <div class="col-8 rounded-3">

            <div style="margin-bottom: 5vh">
                <c:import url="/WEB-INF/views/template/header_template.jsp"/>
            </div>

            <c:if test="${section == ApplicationConstants.SECTION_DELETED}">
                <h3 class="p-2">Deleted tasks</h3>
            </c:if>

            <c:if test="${section == ApplicationConstants.SECTION_FIXED}">
                <h3 class="p-2">Fixed tasks</h3>
            </c:if>

            <c:if test="${section == ApplicationConstants.SECTION_TOMORROW}">
                <h3 class="p-2">Tomorrow tasks</h3>
            </c:if>

            <c:if test="${section == ApplicationConstants.SECTION_TODAY}">
                <h3 class="p-2">Today tasks</h3>
            </c:if>

            <c:if test="${section == ApplicationConstants.SECTION_SOME_DAY}">
                <h3 class="p-2">Someday tasks</h3>
            </c:if>

            <c:if test="${!empty requestScope.section && (requestScope.section != ApplicationConstants.SECTION_DELETED
                            && requestScope.section != ApplicationConstants.SECTION_FIXED)}">
                <a class="btn btn-info btn-sm"
                   href="<c:url value="/task/add"> <c:param name="section" value="${requestScope.section}"/> </c:url>"
                   role="button">Add Task</a>
            </c:if>

            <c:if test="${!empty requestScope.section && (requestScope.section == ApplicationConstants.SECTION_DELETED)}">
                <a class="btn btn-info btn-sm"
                   href="<c:url value="/task/clearAll" /> " role="button">Clear list</a>
            </c:if>


            <c:if test="${empty requestScope.tasks}">
                <h4 class="text-center">The list is still empty.</h4>
            </c:if>

            <c:if test="${!empty requestScope.tasks}">
                <table class="table p-3 table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Date Added</th>
                        <th scope="col">Date Completion</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${requestScope.tasks}" var="task">
                        <tr>

                            <td>
                                <a style="text-decoration: none" class="btn btn-link" href="<c:url value="/task">
                                        <c:param name="${ApplicationConstants.TASK_ID}" value="${task.id}"/>
                                        <c:param name="${ApplicationConstants.SECTION_KEY}" value="${section}"/>
                                </c:url>">${task.name}</a>
                            </td>
                            <td>${task.dateAdded.format(DateTimeFormatter.ofPattern("dd:MM:uuuu"))}</td>
                            <td>${task.dateCompletion.format(DateTimeFormatter.ofPattern("dd:MM:uuuu"))}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${requestScope.section == ApplicationConstants.SECTION_DELETED}">
                                        <a class="btn btn-danger btn-sm" href="<c:url value="/task/fulDelete">
                                                <c:param name="${ApplicationConstants.TASK_ID}" value="${task.id}"/>
                                                <c:param name="${ApplicationConstants.SECTION_KEY}" value="${section}"/>
                                            </c:url>">Delete</a>

                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-danger btn-sm" href="<c:url value="/task/delete">
                                                <c:param name="${ApplicationConstants.TASK_ID}" value="${task.id}"/>
                                                <c:param name="${ApplicationConstants.SECTION_KEY}" value="${section}"/>
                                            </c:url>">Delete</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <c:if test="${!empty requestScope.section && (requestScope.section == ApplicationConstants.SECTION_SOME_DAY
                                        || requestScope.section == ApplicationConstants.SECTION_TODAY
                                        || requestScope.section == ApplicationConstants.SECTION_TOMORROW)}">

                                <td>
                                    <a class="btn btn-warning btn-sm" href="<c:url value="/task/edit">
                                        <c:param name="${ApplicationConstants.TASK_ID}" value="${task.id}"/> </c:url>">Edit</a>
                                </td>

                                <td>
                                    <a class="btn btn-success btn-sm" href="<c:url value="/task/fix">
                                                <c:param name="${ApplicationConstants.TASK_ID}" value="${task.id}"/>
                                                <c:param name="${ApplicationConstants.SECTION_KEY}" value="${section}"/>
                                            </c:url>">Fixed</a>
                                </td>
                            </c:if>

                            <c:if test="${!empty requestScope.section && (requestScope.section == ApplicationConstants.SECTION_DELETED
                                            || requestScope.section == ApplicationConstants.SECTION_FIXED)}">
                                <td>
                                    <a class="btn btn-success btn-sm" href="<c:url value="/task/restore">
                                                <c:param name="${ApplicationConstants.TASK_ID}" value="${task.id}"/>
                                                <c:param name="${ApplicationConstants.SECTION_KEY}" value="${section}"/>
                                            </c:url>">Restore</a>
                                </td>

                            </c:if>


                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </c:if>

            <c:import url="/WEB-INF/views/template/successful_template.jsp"/>
            <c:import url="/WEB-INF/views/template/error_template.jsp"/>
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