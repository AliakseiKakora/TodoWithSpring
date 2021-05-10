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

            <c:import url="/WEB-INF/views/template/header_template.jsp"/>

        </div>

        <div class="col-2">

        </div>


    </div>

</div>


<div class="container-liquid ">
    <div class="row ">

        <div class="col-2">

        </div>

        <div class="col-8 rounded-3">

            <form action="<c:url value="/task/add" /> " method="post" enctype="multipart/form-data">

                <input name="section" type="hidden" value="${requestScope.section}">

                <div class="input-group mb-4">
                    <label class="input-group-text" for="name">Name</label>
                    <input type="text" class="form-control" placeholder="Todo" id="name" name="name" required>
                </div>

                <div class="input-group mb-4">
                    <label class="input-group-text" for="description">Description</label>
                    <textarea class="form-control" id="description" name="description"></textarea>
                </div>

                <c:if test="${(!empty requestScope.section) && requestScope.section == ApplicationConstants.SECTION_SOME_DAY}">
                    <div class="input-group mb-4">
                        <label class="input-group-text">Date</label>
                        <input type="date" name="date" id="date" required>
                    </div>
                </c:if>

                <div class="input-group mb-4">
                    <span class="input-group-text">File</span>
                    <input class="form-control" type="file" name="file"/>
                </div>

                <div class="mb-4">
                    <input class="btn btn-info" type="submit" value="Add Task">
                </div>

            </form>




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