<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <title>ToDo</title>
</head>
<body>

<div class="container-liqud text-center">

    <div class="row " style="background-color: #efe8f4; height:100vh">

        <div class="col-4">

        </div>

        <div class="col-4 align-self-center rounded-3" style="background-color:white">

            <div class="">
                <h1 class="text-muted">ToDo</h1>
            </div>

            <form action="<c:url value="/guest"> <c:param name="command" value="Registration"/> </c:url> " method="post">
                <div class="mb-4">
                    <input type="text" class="form-control " placeholder="Login" name="login" required>
                </div>

                <div class="mb-4">
                    <input type="password" class="form-control" placeholder="Password" name="password" required>
                </div>

                <div class="mb-4">
                    <input type="email" class="form-control" placeholder="Email" name="email" required>
                </div>

                <div class="mb-4">
                    <input type="submit" class="form-control" value="Sign Up">
                </div>

            </form>

            <a href="<c:url value="/guest"> <c:param name="command" value="LoginView"/> </c:url>">
                <h6>Have an account? Sign in</h6>
            </a>

            <c:import url="/WEB-INF/template/error_templ.jsp"/>
            <c:import url="/WEB-INF/template/successful_template.jsp"/>

        </div>
        <div class="col-4" >

        </div>

    </div>

</div>

</body>

</html>
