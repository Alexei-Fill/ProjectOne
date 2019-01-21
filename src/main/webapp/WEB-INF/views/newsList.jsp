<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<LINK TYPE="text/css" rel="stylesheet" href="/resources/news.css"/>
<head>
    <title>News</title>
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


<c:if test="${!empty newsList}">
    <c:url var="deleteNews" value="/deleteNews" ></c:url>
    <form action="${deleteNews}" modelAttribute="news" method="post" >
        <c:forEach items="${newsList}" var="news">
    <div class="content" align="center">
        <h4>Title news : ${news.title}  ${news.date}</h4>
        <h5>${news.brief}</h5>
        <a href="/news/${news.id}">View</a> <a href="/showEditNews/${news.id}">Edit</a>
        <input type="checkbox" value="${news.id}" name="removedNews" >
    </div>
        </c:forEach>

        <input type="submit" value="delete"/>
    </form>


</c:if>

</body>
</html>
