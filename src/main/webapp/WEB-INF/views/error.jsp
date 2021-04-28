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


<div class="container-liqud text-center">
    <div class="row " style="height:100vh">

        <div class="col-2">

        </div>

        <div class="col-8 rounded-3">
            <div style="margin-bottom: 5vh">
                <c:import url="/WEB-INF/views/template/header_templ.jsp"/>
            </div>

            <p class="lead">
                An unknown error has occurred! Please describe the error, it will help us a lot!
            </p>

            <form action="<c:url value="/message/add" /> " method="post">

                <div class="input-group mb-4">
                    <span class="input-group-text">Message</span>
                    <label>
                        <textarea class="form-control" name="message" required></textarea>
                    </label>
                </div>

                <input name="${ApplicationConstants.USER_ID_KEY}" type="hidden" value="${requestScope.userId}">

                <div class="mb-4">
                    <input type="submit" class="form-control" value="Submit">
                </div>

            </form>



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