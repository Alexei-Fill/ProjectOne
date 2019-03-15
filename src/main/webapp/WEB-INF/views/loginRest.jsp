<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<LINK TYPE="text/css" rel="stylesheet" href=<c:url value="/resources/news.css"/>/>
<head>
    <title><spring:message code="key.signOut"/></title>
</head>
<body>
<div class="roof">
    <b style="margin-bottom: 20px;"><spring:message code="key.newsManagment"/></b>
    <b style="margin-left: 65%;"><a href="?lang=ru" style="margin: 15px;">Russian</a> <a href="?lang=en"
                                                                                         style="margin: 15px;">English</a></b>
    <security:authorize access="isAnonymous()">
        <a href="/showReg"> <spring:message code="key.registration"/></a>
    </security:authorize>
    <security:authorize access="isAuthenticated()">
        <spring:message code="key.youLoggedAs"/><b>   <sec:authentication property="principal.username"/></b>
        <form action="/logout" method="post" style="float: right;">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input value="<spring:message code="key.signOut"/>" type="submit">
        </form>
    </security:authorize>
</div>
<c:url  value = "/login"  var = "loginUrl" />
<form name="authorization" accept-charset="UTF-8"  method="post" action="${loginUrl}" style = "margin-left: 40%;">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <h3><spring:message code="key.login"/></h3>
    <input  name = "login" id = "login" type="text"  />
    <h3><spring:message code="key.password"/></h3>
    <input   name = "password" id = "password" type="password" />
    <div style = "margin-bottom:10px;"></div>
    <input type ="submit" value ="<spring:message code="key.signIn"/>"/>
</form>

<c:if  test = "${param.error!= null}" >
    <p>
        <spring:message code="key.incLoginOrPass"/>
    </p> </c:if>


</body>
</html>
