<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<h1><a href="/showAddNews">ADD</a></h1>
<c:if test="${!empty newsList}">
    <table class="tg">
        <tr>
            <th width="80"> ID</th>
            <th width="120"> Title</th>
            <th width="120"> Brief</th>
            <th width="120"> Content</th>
            <th width="120"> Date</th>
            <th width="120"> <a>Edit</a></th>
            <th width="120"> <a>Delete</a></th>

        </tr>
        <c:forEach items="${newsList}" var="news">
            <tr>
                <td>${news.id}</td>
                <td>${news.title}</td>
                <td>${news.brief}</td>
                <td>${news.content}</td>
                <td>${news.date}</td>
                <td><a>Edit</a>${news.date}</td>
                <td><a>Delete</a>${news.date}</td>

            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
