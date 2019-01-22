<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<LINK TYPE="text/css" rel="stylesheet" href="/resources/news.css"/>
<head>
    <c:if test="${!empty news.title}">
        <title><spring:message code="key.edit"/></title>
    </c:if>
    <c:if test="${empty news.title}">
        <title><spring:message code="key.addNews"/></title>
    </c:if>
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


<c:url var="editAddNews" value="/editAddNews" ></c:url>

<form:form action="${editAddNews}" modelAttribute="news" >
    <table>
        <tr>
            <td>
                <form:input path="id" readonly="true" hidden = "true"/>
                <spring:message code="key.title"/>
            </td>
            <td>
                <form:input path="title" />
                <form:errors path="title" element="div"/>
            </td>
            <form:errors path="title" element="h1"/>
        </tr>
        <tr>
            <td>
                <spring:message code="key.brief"/>
            </td>
            <td>
                <form:input path="brief" />
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="key.content"/>
            </td>
            <td>
                <form:textarea path="content" />
            </td>
            <form:errors path="content"/>

        </tr>
        <tr>
            <td>
                <spring:message code="key.date"/>
            </td>
            <td>
                <form:input path="date" required="required" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty news.title}">
                    <input type="submit" value="<spring:message code="key.edit"/>" />
                </c:if>
                <c:if test="${empty news.title}">
                    <input type="submit" value="<spring:message code="key.addNews"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
