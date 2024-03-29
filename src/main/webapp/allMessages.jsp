<%@ page import="by.itacademy.todolist.constants.ApplicationConstants" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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

            <c:import url="/WEB-INF/template/header_templ.jsp"/>

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

            <c:import url="/WEB-INF/template/admin_navigate_template.jsp"/>

        </div>

        <div class="col-6 text-center" >

            <c:if test="${!empty requestScope.messages}">
                <table class="table p-3 table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Sender</th>
                        <th scope="col">Date Added</th>
                        <th scope="col"></th>
                    </thead>
                    <tbody>

                    <c:forEach items="${requestScope.messages}" var="message">
                        <tr>
                            <td>
                                <form action="<c:url value="/"> <c:param name="${ApplicationConstants.COMMAND_KEY}" value="MessageCardView"/> </c:url>" method="post">
                                    <input name="${ApplicationConstants.MESSAGE_ID}" type="hidden" value="${message.id}">
                                    <input style="text-decoration: none" class="btn btn-link" type="submit" value="${message.user.name} ${message.user.surname}">
                                </form>

                            </td>
                            <td>${message.dateAdded.format(DateTimeFormatter.ofPattern("dd:MM:uuuu"))}</td>

                            <td>
                                <form action="<c:url value="/" > <c:param name="${ApplicationConstants.COMMAND_KEY}" value="DeleteMessage"/>
                                                    </c:url>" method="post">
                                    <input name="${ApplicationConstants.MESSAGE_ID}" type="hidden" value="${message.id}">
                                    <input class="btn btn-danger btn-sm" type="submit" value="Delete">
                                </form>

                            </td>

                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </c:if>

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