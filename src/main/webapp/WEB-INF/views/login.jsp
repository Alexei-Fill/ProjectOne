<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form name="authorization" accept-charset="UTF-8"  method="post" action="/login" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <h3>login</h3>
    <input  name = "login" type="text" required />
    <h3>password</h3>
    <input   name = "password" type="password"   required />
    <div style = "margin-bottom:10px;"></div>
    <input type ="submit" value ="log in"/>
</form>

</body>
</html>
