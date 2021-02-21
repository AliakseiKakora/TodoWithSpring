<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${!empty requestScope.error}" >
    <h3 style="color: red">Error - ${requestScope.error}</h3>
</c:if>