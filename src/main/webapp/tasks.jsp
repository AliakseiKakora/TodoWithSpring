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
                <c:import url="/WEB-INF/template/header_templ.jsp"/>
            </div>

            <h3 class="p-2">${requestScope.title} tasks</h3>

            <c:if test="${!empty requestScope.section && (requestScope.section == ApplicationConstants.SECTION_SOME_DAY
                            || requestScope.section == ApplicationConstants.SECTION_TODAY
                            || requestScope.section == ApplicationConstants.SECTION_TOMORROW)}">
                <a class="btn btn-info btn-sm" href="<c:url value="/"> <c:param name="command" value="AddTaskView"/> <c:param name="section" value="${requestScope.section}"/> </c:url>" role="button">Add Task</a>
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

                                <form action="<c:url value="/"> <c:param name="command" value="TaskView"/> </c:url>" method="post">
                                    <input name="${ApplicationConstants.TASK_ID}" type="hidden" value="${task.id}">
                                    <input name="${ApplicationConstants.SECTION_KEY}" type="hidden" value="${requestScope.section}">
                                    <input style="text-decoration: none" class="btn btn-link" type="submit" value="${task.name}">
                                </form>

                            </td>
                            <td>${task.dateAdded.format(DateTimeFormatter.ofPattern("dd:MM:uuuu"))}</td>
                            <td>${task.dateCompletion.format(DateTimeFormatter.ofPattern("dd:MM:uuuu"))}</td>
                            <td>
                                <form action="<c:url value="/" >
                                                    <c:param name="${ApplicationConstants.COMMAND_KEY}" value="UpdateTask"/>
                                                    <c:choose>
                                                        <c:when test="${requestScope.section == ApplicationConstants.SECTION_DELETED}">
                                                             <c:param name= "${ApplicationConstants.TASK_ACTION_KEY}" value="${ApplicationConstants.TASK_ACTION_FULL_DELETE}"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                             <c:param name= "${ApplicationConstants.TASK_ACTION_KEY}" value="${ApplicationConstants.TASK_ACTION_DELETE}"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    </c:url>" method="post">
                                    <input name="${ApplicationConstants.TASK_ID}" type="hidden" value="${task.id}">
                                    <input name="${ApplicationConstants.SECTION_KEY}" type="hidden" value="${requestScope.section}">
                                    <input class="btn btn-danger btn-sm" type="submit" value="Delete">
                                </form>

                            </td>

                            <c:if test="${!empty requestScope.section && (requestScope.section == ApplicationConstants.SECTION_SOME_DAY
                                        || requestScope.section == ApplicationConstants.SECTION_TODAY
                                        || requestScope.section == ApplicationConstants.SECTION_TOMORROW)}">

                                <td>
                                    <form action="<c:url value="/" >
                                                   <c:param name="${ApplicationConstants.COMMAND_KEY}" value="EditTaskView"/>
                                                 </c:url>" method="post">
                                        <input name="${ApplicationConstants.TASK_ID}" type="hidden" value="${task.id}">
                                        <input class="btn btn-warning btn-sm" type="submit" value="Edit">
                                    </form>
                                </td>

                                <td>
                                    <form action="<c:url value="/" >
                                                   <c:param name="${ApplicationConstants.COMMAND_KEY}" value="UpdateTask"/>
                                                    <c:param name= "${ApplicationConstants.TASK_ACTION_KEY}" value="${ApplicationConstants.TASK_ACTION_FIXED}"/>
                                                 </c:url>" method="post">
                                        <input name="${ApplicationConstants.TASK_ID}" type="hidden" value="${task.id}">
                                        <input name="${ApplicationConstants.SECTION_KEY}" type="hidden" value="${requestScope.section}">
                                        <input class="btn btn-success btn-sm" type="submit" value="Fixed">
                                    </form>

                                </td>
                            </c:if>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </c:if>
            <c:import url="/WEB-INF/template/error_templ.jsp"/>
        </div>

        <div class="col-2" >

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