<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${!empty requestScope.successful}" >
    <h3 style="color:  #76e471">Successful - ${requestScope.successful}</h3>
</c:if>