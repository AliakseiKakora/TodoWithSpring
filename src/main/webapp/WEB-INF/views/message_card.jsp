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
    <div class="row" style="margin-bottom: 5vh">

        <div class="col-2">

        </div>

        <div class="col-8 rounded-3">

            <c:import url="/WEB-INF/views/template/header_template.jsp"/>

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

                            <li>
                                <a class="btn btn-danger btn-sm" href="<c:url value="/admin/message/delete/${message.id}" />">Delete message</a>
                            </li>

                    </ul>
                </div>
                <div class="card-body">
                    <h6 class="card-title">Sender</h6>
                    <a style="text-decoration: none" class="btn btn-link ms-2 mb-0"
                       href="<c:url value="/admin/user/${requestScope.message.user.id}" />">${requestScope.message.user.name} ${requestScope.message.user.surname}</a>

                    <h6 class="card-title">Message:</h6>
                    <p class="card-text">${requestScope.message.message}</p>
                    <h6 class="card-title">${requestScope.message.dateAdded.format(DateTimeFormatter.ofPattern("dd:MM:uuuu HH:mm"))}</h6>
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