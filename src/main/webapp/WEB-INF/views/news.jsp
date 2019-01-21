<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<LINK TYPE="text/css" rel="stylesheet" href="/resources/news.css"/>
<head>
    <title>News View</title>

</head>
<body>
<div class = "roof">
    <b style="margin-bottom: 20px;" >News Management</b>
    <b style="margin-left: 65%;"><a href="#" style="margin: 15px;">Russian</a> <a href="#" style="margin: 15px;">English</a></b>
</div>
<div class = "smenu">
    <h2 align="center">News</h2>
    <h4><a href="/newsList">News List</a></h4>
    <h4><a href="/showAddNews">Add news</a></h4>
</div>


<form:form action="/deleteNews" modelAttribute="news" >


    <spring:message text="Title news"/>
    <p>${news.title}</p>
    <h6></h6>
    <spring:message text="Brief"/>    <p>${news.brief}</p>
    <h6></h6>
        <spring:message text="Date"/>
    <p>${news.date}</p>
    <h6></h6>
    <spring:message text="Content"/>
    <p>${news.content}</p>
    <h6></h6>
    <a href="/showEditNews/${news.id}"><input type="button" value="Edit"/></a>
    <input type="checkbox" value="${news.id}" name="removedNews" checked="true" hidden="true">
    <input type="submit" value="delete"/>



</form:form>
</body>
</html>
