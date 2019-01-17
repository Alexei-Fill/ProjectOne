<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

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
                <form:input path="title" />
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
                <form:input path="content" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="date">
                    <spring:message text="date"/>
                </form:label>
            </td>
            <td>
                <form:input path="date" readonly="true" disabled="true"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty news.title}">
                    <input type="submit"
                           value="<spring:message text="Edit Person"/>" />
                </c:if>
                <c:if test="${empty news.title}">
                    <input type="submit"
                           value="<spring:message text="Add Person"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
