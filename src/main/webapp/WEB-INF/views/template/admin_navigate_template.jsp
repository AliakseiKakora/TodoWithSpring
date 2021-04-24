<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div class="">

    <form action="<c:url value="/"> <c:param name="command" value="AllUsers"/> </c:url>" method="post">
        <input type="submit" class="btn btn-outline-primary form-control" value="Users">
    </form>


    <form action="<c:url value="/"> <c:param name="command" value="AllMessages"/> </c:url>" method="post">
        <input type="submit" class="btn btn-outline-primary form-control" value="Messages">
    </form>

</div>