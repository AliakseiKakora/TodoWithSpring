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



<div class="container-liquid " style="margin-bottom: 5vh">
    <div class="row ">

        <div class="col-2">

        </div>

        <div class="col-8 rounded-3">

            <c:import url="/WEB-INF/template/header_templ.jsp"/>

        </div>

        <div class="col-2" >

        </div>


    </div>

</div>



<div class="container-liquid ">
    <div class="row ">

        <div class="col-2">

        </div>

        <div class="col-8 rounded-3">


            <c:if test="${!empty requestScope.task}">
                <form action="<c:url value="/"> <c:param name="command" value="EditTask"/> </c:url> " method="post" enctype="multipart/form-data">

                    <div class="input-group mb-4">
                        <span class="input-group-text" id="basic-addon1">Name</span>
                        <input type="text" class="form-control" placeholder="Todo" name="name" aria-describedby="basic-addon1" required value="${requestScope.task.name}">
                    </div>

                    <div class="input-group mb-4">
                        <span class="input-group-text">Description</span>
                        <textarea class="form-control" name="description">${requestScope.task.description}</textarea>
                    </div>

                    <div class="input-group mb-4">
                        <span class="input-group-text">Date</span>
                        <input type="date" name="date" required value="${requestScope.task.dateCompletion.format(DateTimeFormatter.ofPattern("uuuu-MM-dd"))}">
                        <input type="time" name="time" required value="${requestScope.task.dateCompletion.format(DateTimeFormatter.ofPattern("HH:mm"))}">
                    </div>

                    <div class="mb-4">
                        <button type="submit" class="btn btn-info">Edit Task</button>
                        <input name="${ApplicationConstants.TASK_ID}" type="hidden" value="${requestScope.task.id}">
                    </div>

                </form>

                <c:choose>
                    <c:when test="${empty requestScope.task.fileInfo}" >
                        <form method="post" action="<c:url value="/"> <c:param name="command" value="UploadFile"/> </c:url>"
                              enctype="multipart/form-data">

                            <div class="input-group mb-4">
                                <span class="input-group-text">File</span>
                                <input class="form-control" type="file" name="file" required/>
                                <input name="${ApplicationConstants.TASK_ID}" type="hidden" value="${requestScope.task.id}"/>
                            </div>

                            <input type="submit" class="btn btn-info" value="Upload File"/>
                        </form>
                    </c:when>
                    <c:otherwise>

                        <%-- Download file--%>
                        <form action="<c:url value="/" > <c:param name="${ApplicationConstants.COMMAND_KEY}" value="DownloadFile"/>                                                                                 </c:url>" method="post">
                            <input name="fileName" type="hidden" value="${requestScope.task.fileInfo.name}">
                            <input name="filePath" type="hidden" value="${requestScope.task.fileInfo.path}">
                            <input style="text-decoration: none" class="btn btn-info btn-sm " type="submit" value="${requestScope.task.fileInfo.name}">
                        </form>

                        <form method="post" action="<c:url value="/"> <c:param name="command" value="DeleteFile"/> </c:url>" >
                            <div class="input-group mb-4">
                                <input name="${ApplicationConstants.TASK_ID}" type="hidden" value="${requestScope.task.id}"/>
                                <input name="${ApplicationConstants.FILE_ID}" type="hidden" value="${requestScope.task.fileInfo.id}"/>
                            </div>
                            <input type="submit" class="btn btn-danger" value="Delete File"/>
                        </form>
                    </c:otherwise>

                </c:choose>



            </c:if>

            <c:import url="/WEB-INF/template/successful_template.jsp"/>
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