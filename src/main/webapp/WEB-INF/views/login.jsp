<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form name="authorization" accept-charset="UTF-8"  method="post" action="<c:url value="/login"/>">

    <h3>login</h3>
    <input  name = "login_s" type="text" required />
    <h3>password</h3>
    <input   name = "password_s" type="password"   required />
    <div style = "margin-bottom:10px;"></div>
    <input type ="submit" value ="log in"/>
</form>

</body>
</html>
