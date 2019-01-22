<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<LINK TYPE="text/css" rel="stylesheet" href="/resources/news.css"/>
<head>
    <title><spring:message code="key.newsView"/></title>

</head>
<body>
<div class = "roof">
    <b style="margin-bottom: 20px;" ><spring:message code="key.newsManagment"/></b>
    <b style="margin-left: 65%;"><a href="?lang=ru" style="margin: 15px;">Russian</a> <a href="?lang=en" style="margin: 15px;">English</a></b>
</div>
<div class = "smenu">
    <h2 align="center"><spring:message code="key.news"/></h2>
    <h4><a href="/newsList"><spring:message code="key.newsList"/></a></h4>
    <h4><a href="/showAddNews"><spring:message code="key.addNews"/></a></h4>
</div>


<form:form action="/deleteNews" modelAttribute="news" >


    <spring:message code="key.title"/>
    <p>${news.title}</p>
    <h6></h6>
    <spring:message code="key.brief"/>   <p>${news.brief}</p>
    <h6></h6>
    <spring:message code="key.content"/>
    <p>${news.date}</p>
    <h6></h6>
    <spring:message code="key.date"/>
    <p>${news.content}</p>
    <h6></h6>
    <a href="/showEditNews/${news.id}"><input type="button" value="<spring:message code="key.edit"/>"/></a>
    <input type="checkbox" value="${news.id}" name="removedNews" checked="true" hidden="true">
    <input type="submit" value="<spring:message code="key.delete"/>"/>



</form:form>
</body>
</html>
