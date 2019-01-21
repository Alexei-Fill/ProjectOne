<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<LINK TYPE="text/css" rel="stylesheet" href="/resources/news.css"/>
<head>
    <c:if test="${!empty news.title}">
        <title>Edit news</title>
    </c:if>
    <c:if test="${empty news.title}">
        <title>Add news</title>
    </c:if>
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


<c:url var="editAddNews" value="/editAddNews" ></c:url>

<form:form action="${editAddNews}" modelAttribute="news" >
    <table>
        <c:if test="${!empty news.title}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8"  disabled="true" />
                    <form:hidden path="id" />
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="title">
                    <spring:message text="title"/>
                </form:label>
            </td>
            <td>
                <form:input path="title" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="brief">
                    <spring:message text="brief"/>
                </form:label>
            </td>
            <td>
                <form:input path="brief" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="content">
                    <spring:message text="content"/>
                </form:label>
            </td>
            <td>
                <form:input path="content" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="date">
                    <spring:message text="date"/>
                </form:label>
            </td>
            <td>
                <form:input path="date" required="required" />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty news.title}">
                    <input type="submit"
                           value="<spring:message text="Edit News"/>" />
                </c:if>
                <c:if test="${empty news.title}">
                    <input type="submit"
                           value="<spring:message text="Add News"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
