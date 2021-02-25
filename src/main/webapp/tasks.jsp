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

<header>
    <c:import url="/WEB-INF/template/header_templ.jsp"/>
</header>

<div class="container-liquid ">
    <div class="row " style="height:100vh">

        <div class="col-2">
            <c:import url="/WEB-INF/template/button_task_template.jsp"/>

        </div>

        <div class="col-8 rounded-3">
           <c:if test="${!empty requestScope.tasks}">
               <table class="table p-3 table-hover">
                   <thead>
                   <tr>
                       <th scope="col">Description</th>
                       <th scope="col">Date Added</th>
                       <th scope="col">Date Completion</th>
                   </tr>
                   </thead>
                   <tbody>

                   <c:forEach items="${requestScope.tasks}" var="task">
                       <tr class="table-primary">
                           <td>${task.description}</td>
                           <td>${task.dateAdded.format(DateTimeFormatter.ofPattern("dd:MM:uuuu"))}</td>
                           <td>${task.dateCompletion.format(DateTimeFormatter.ofPattern("dd:MM:uuuu"))}</td>
                           <td>
                              <%-- <form action="<c:url value="/"> <c:param name="command" value="DeleteTask"/> </c:url>"
                                     method="post">
                                   <input name="productId" type="hidden" value="${task.id}">
                                   <input type="submit" value="Delete task">
                               </form>
                               <form action="<c:url value="/"> <c:param name="command" value="EditTask"/> </c:url>"
                                     method="post">
                                   <input name="productId" type="hidden" value="${task.id}">
                                   <input type="submit" value="Edit task">
                               </form>--%>

                               <a class="btn btn-light" href="<c:url value="/"> <c:param name="command" value="DeleteTask"/> <c:param name="taskId" value="${task.id}"></c:param> </c:url>" role="button">Delete</a>

                               <a class="btn btn-light" href="<c:url value="/"> <c:param name="command" value="EditTask"/> </c:url>" role="button">Edit</a>
                           </td>

                       </tr>

                   </c:forEach>

                   </tbody>
               </table>



               <ul class="list-group">

                   <c:forEach items="${requestScope.tasks}" var="task">

                   <li class="list-group-item list-group-item-primary">${task.description}</li>


                   </c:forEach>


               </ul>

           </c:if>

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