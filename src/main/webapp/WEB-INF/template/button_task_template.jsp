<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="btn-group-vertical d-grid gap-2 p-3 col-8 ">
    <form action="<c:url value="/"> <c:param name="command" value="AllUsers"/> </c:url>" method="post">
        <input type="submit" class="btn btn-outline-primary form-control" value="Today">
    </form>

    <form action="<c:url value="/"> <c:param name="command" value="AllUsers"/> </c:url>" method="post">
        <input type="submit" class="btn btn-outline-primary form-control" value="Tomorrow">
    </form>

    <form action="<c:url value="/"> <c:param name="command" value="AllUsers"/> </c:url>" method="post">
        <input type="submit" class="btn btn-outline-primary form-control" value="Some day">
    </form>

    <form action="<c:url value="/"> <c:param name="command" value="AllUsers"/> </c:url>" method="post">
        <input type="submit" class="btn btn-outline-primary form-control" value="Fixed">
    </form>

    <form action="<c:url value="/"> <c:param name="command" value="AllUsers"/> </c:url>" method="post">
        <input type="submit" class="btn btn-outline-primary form-control" value="Deleted">
    </form>

</div>