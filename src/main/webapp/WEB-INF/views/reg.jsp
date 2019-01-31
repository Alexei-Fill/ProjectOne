<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<LINK TYPE="text/css" rel="stylesheet" href="/resources/news.css"/>
<head>
    <title>Login</title>
</head>
<body>
<div class="roof">
    <b style="margin-bottom: 20px;"><spring:message code="key.newsManagment"/></b>
    <b style="margin-left: 65%;"><a href="?lang=ru" style="margin: 15px;">Russian</a> <a href="?lang=en"
                                                                                         style="margin: 15px;">English</a></b>
    <security:authorize access="isAnonymous()">
        <a href="showLogin"> <spring:message code="key.signIn"/></a>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
        <spring:message code="key.youLoggedAs"/><b>   <sec:authentication property="principal.username"/></b>
        <form action="/logout" method="post" style="float: right;">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input value="<spring:message code="key.signOut"/>" type="submit">
        </form>
    </security:authorize>
</div>
<form:form name="reg" action="/reg" modelAttribute="user"  style = "margin-left: 40%;">

    <h3>login</h3>
    <form:input path="login"/>
    <%--<input  name = "login_s" type="text"  />--%>
    <h3>password</h3>
    <form:password path="password"/>
    <%--<input   name = "password_s" type="password"    />--%>
    <div style = "margin-bottom:10px;"></div>
    <input type ="submit" value ="reg"/>
</form:form>

</body>
</html>
