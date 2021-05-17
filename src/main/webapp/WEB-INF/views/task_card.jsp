<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="by.itacademy.todolist.constants.ApplicationConstants" %>
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
    <div class="row ">

        <div class="col-2">

        </div>

        <div class="col-8 rounded-3">

            <c:import url="/WEB-INF/views/template/header_template.jsp"/>

        </div>

        <div class="col-2">

        </div>


    </div>

</div>


<div class="container-liqud">
    <div class="row " style="height:100vh">

        <div class="col-2">

        </div>

        <div class="col-8 rounded-3">

            <div class="card">
                <div class="card-header">
                    <ul class="nav nav-pills card-header-pills">
                        <li class="nav-item">
                            <c:choose>
                                <c:when test="${requestScope.section == ApplicationConstants.SECTION_DELETED}">
                                    <a style="text-decoration: none" class="btn btn-danger btn-sm ms-2 mb-0"
                                       href="<c:url value="/task/fulDelete">
                                                <c:param name="${ApplicationConstants.TASK_ID}" value="${task.id}"/>
                                                <c:param name="${ApplicationConstants.SECTION_KEY}" value="${section}"/>
                                            </c:url>">Delete</a>

                                </c:when>
                                <c:otherwise>
                                    <a style="text-decoration: none" class="btn btn-danger btn-sm ms-2 mb-0"
                                       href="<c:url value="/task/delete">
                                                <c:param name="${ApplicationConstants.TASK_ID}" value="${task.id}"/>
                                                <c:param name="${ApplicationConstants.SECTION_KEY}" value="${section}"/>
                                            </c:url>">Delete</a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                        <c:if test="${!empty requestScope.section && (requestScope.section == ApplicationConstants.SECTION_SOME_DAY
                                        || requestScope.section == ApplicationConstants.SECTION_TODAY
                                        || requestScope.section == ApplicationConstants.SECTION_TOMORROW)}">

                            <li class="nav-item">
                                <a class="btn btn-warning btn-sm ms-2 mb-0" href="<c:url value="/task/edit">
                                        <c:param name="${ApplicationConstants.TASK_ID}" value="${task.id}"/> </c:url>">Edit</a>
                            </li>

                            <li class="nav-item">
                                <a class="btn btn-success btn-sm ms-2 mb-0" href="<c:url value="/task/fix">
                                                <c:param name="${ApplicationConstants.TASK_ID}" value="${task.id}"/>
                                                <c:param name="${ApplicationConstants.SECTION_KEY}" value="${section}"/>
                                            </c:url>">Fixed</a>
                            </li>


                        </c:if>


                        <c:if test="${!empty requestScope.task.fileInfo}">

                            <li>
                                    <%-- Download file--%>
                                <a class="btn btn-info btn-sm ms-2 mb-0" href="<c:url value="/file/download">
                                        <c:param name="${ApplicationConstants.FILE_ID}" value="${task.fileInfo.id}"/>
                                 </c:url>">Download File</a>

                            </li>

                        </c:if>

                    </ul>
                </div>
                <div class="card-body">
                    <h4 class="card-title">${requestScope.task.name}</h4>
                    <p class="card-text">${requestScope.task.description}</p>
                    <h6 class="card-title">Date added
                        - ${requestScope.task.dateAdded.format(DateTimeFormatter.ofPattern("dd:MM:uuuu"))}</h6>
                    <h6 class="card-title">Date completion
                        - ${requestScope.task.dateCompletion.format(DateTimeFormatter.ofPattern("dd:MM:uuuu"))}</h6>
                </div>
            </div>


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