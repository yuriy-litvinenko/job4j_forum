<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>

<html>
<body>

<form name='login' action="<c:url value='/save'/>" method='POST'>
    <input type='hidden' name='id' value="${post.id}">
    <s:hidden path="post.created" />

    <table>
        <tr>
            <td>Название:</td>
            <td><input type='text' name='name' value="${post.name}"></td>
        </tr>
        <tr>
            <td>Описание:</td>
            <td><input type='text' name='description' value="${post.description}"/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>

</form>
</body>
</html>
