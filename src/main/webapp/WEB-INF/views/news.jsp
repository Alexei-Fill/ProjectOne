<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<LINK TYPE="text/css" rel="stylesheet" href=<c:url value="/resources/news.css"/>/>
<head>
    <title><spring:message code="key.newsView"/></title>

</head>
<body>
<div class="roof">
    <b style="margin-bottom: 20px;"><spring:message code="key.newsManagment"/></b>
    <b style="margin-left: 65%;"><a href="?lang=ru" style="margin: 15px;">Russian</a> <a href="?lang=en"
                                                                                         style="margin: 15px;">English</a></b>
    <security:authorize access="isAnonymous()">
        <a href="/showLogin"> <spring:message code="key.signIn"/></a> / <a href="/showReg"> <spring:message code="key.registration"/></a>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
        <spring:message code="key.youLoggedAs"/><b>   <sec:authentication property="principal.username"/></b>
        <form action="/logout" method="post" style="float: right;">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input value="<spring:message code="key.signOut"/>" type="submit">
        </form>
    </security:authorize>
</div>
<div class="smenu">
    <h2 align="center"><spring:message code="key.news"/></h2>
    <h4><a href="/newsList"><spring:message code="key.newsList"/></a></h4>
    <security:authorize access="isAuthenticated()">
        <h4><a href="/showAddNews"><spring:message code="key.addNews"/></a></h4>
    </security:authorize>

</div>


<form:form action="/deleteNews" modelAttribute="news">


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
    <security:authorize access="isAuthenticated()">
        <a href="/showEditNews/${news.id}"><input type="button" value="<spring:message code="key.edit"/>"/></a>
        <input type="checkbox" value="${news.id}" name="removedNews" checked="true" hidden="true">
        <input type="submit" value="<spring:message code="key.delete"/>"/>
    </security:authorize>
</form:form>
</body>
</html>
