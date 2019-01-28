<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<LINK TYPE="text/css" rel="stylesheet" href="/resources/news.css"/>
<head>
    <title><spring:message code="key.news"/></title>
</head>
<body>
<div class="roof">
    <b style="margin-bottom: 20px;"><spring:message code="key.newsManagment"/></b>
    <b style="margin-left: 65%;"><a href="?lang=ru" style="margin: 15px;">Russian</a> <a href="?lang=en"
                                                                                         style="margin: 15px;">English</a></b>
</div>
<div class="smenu">
    <h2 align="center"><spring:message code="key.news"/></h2>
    <h4><a href="/newsList"><spring:message code="key.newsList"/></a></h4>
    <security:authorize access="isAuthenticated()">
        <h4><a href="/showAddNews"><spring:message code="key.addNews"/></a></h4>
    </security:authorize>
</div>


<c:if test="${!empty newsList}">
    <c:url var="deleteNews" value="/deleteNews"></c:url>
    <form action="${deleteNews}" modelAttribute="news" method="post">
        <c:forEach items="${newsList}" var="news">
            <div class="content" align="center">
                <b><spring:message code="key.title"/></b>
                <span style="padding-left: 20px"> ${news.title} </span>
                <span style="float: right;"> <i><u>${news.date}</u></i></span>
                <h5>${news.brief}</h5>

                <span style="float: right;">
           <a style="padding-right: 20px" href="/news/${news.id}"><spring:message code="key.view"/></a>
           <security:authorize access="isAuthenticated()">
               <a style="padding-right: 20px" href="/showEditNews/${news.id}"><spring:message code="key.edit"/></a>
               <input type="checkbox" value="${news.id}" name="removedNews">
           </security:authorize>
       </span>
                <h5></h5>
            </div>
        </c:forEach>
        <security:authorize access="isAuthenticated()">
        <input style="float: right;" type="submit" value="<spring:message code="key.delete"/>"/>
        </security:authorize>
    </form>


</c:if>

</body>
</html>
