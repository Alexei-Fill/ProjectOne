<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/deleteNews" modelAttribute="news" >

<form:label path="title">
    <spring:message text="title"/>
</form:label>
<form:label path="title" />

<form:label path="brief">
    <spring:message text="brief"/>
</form:label>
<form:label path="brief" />
    <form:label path="date">
        <spring:message text="date"/>
    </form:label>
    <form:label path="date" />
<form:label path="content">
    <spring:message text="content"/>
</form:label>
<form:label path="content" />

    <input type="checkbox" value="${news.id}" name="removedNews" checked="true" hidden="true">
    <input type="submit" value="delete"/>
<a href="/showEditNews/${news.id}"><input type="button" value="Edit"/></a>


</form:form>
</body>
</html>
