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
      <div class="mb-4">
        <h1 class="text-muted">Welcome to ToDo</h1>
      </div>

      <div class="mb-2">
        <a href="<c:url value="/guest?command=LoginView" />" class="d-grid gap-2 mb-3"> <button type="button" class="btn btn-primary btn-lg">Sign In</button></a>
        <a href="<c:url value="/guest?command=RegistrationView" />" class="d-grid gap-2"> <button type="button" class="btn btn-primary btn-lg">Sign Up</button></a>
      </div>

    </div>

    <div class="col-4" >

    </div>

  </div>

</div>
</body>
</html>
