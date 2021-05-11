<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
    <div class="row ">

        <div class="col-2">

        </div>

        <div class="col-8 rounded-3">
            <div style="margin-bottom: 5vh">
                <c:import url="/WEB-INF/views/template/header_template.jsp"/>
            </div>

            <h1 class="mb-3 p-2">My Profile</h1>
        </div>

        <div class="col-2">

        </div>


    </div>

    <div class="row ">

        <div class="col-2">

        </div>

        <div class="col-2">

            <div class="mb-3">
                <a class="btn btn-outline-primary form-control" href="<c:url value="/profile/update/password" />">Change Password </a>
            </div>

        </div>

        <div class="col-6 text-center" >

            <form action="<c:url value="profile/update" > </c:url>" method="post">

                <div class="input-group mb-3">
                    <label for="login" class="input-group-text">Login</label>
                    <input type="text" class="form-control" id="login" name="profile.login" required
                           value="${requestScope.user.profile.login}">
                </div>

                <div class="input-group mb-3">
                    <label for="email" class="input-group-text">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required
                           value="${requestScope.user.email}">
                </div>

                <div class="input-group mb-3">
                    <label for="name" class="input-group-text">Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${requestScope.user.name}">
                </div>

                <div class="input-group mb-3">
                    <label for="surname" class="input-group-text">Surname</label>
                    <input type="text" class="form-control" id="surname" name="surname"
                           value="${requestScope.user.surname}">
                </div>

                <button type="submit" class="btn btn-primary">Update</button>
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