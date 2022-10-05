<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>
            Custome Login Page
        </title>
        <style>
            .failed {
                color: red;
            }
        </style>
    </head>
    <body>
        <h3>My Custom Login Page</h3>

        <form:form action = "${pageContext.request.contextPath}/authenticate-theUser" method = "POST">
            <!-- Check for login error -->
            <c:if test="${param.error != null}">
                <i class="failed">Sorry! you entered invalid username/password</i>
            </c:if>

            <p>
                <!-- Note that we should use input tag instead of form:input tag. They are different. -->
                User name: <input type="text" name="username" />
            </p>
            <p>
                Password: <input type="password" name="password" />
            </p>

            <input type="submit" value="Login" />
        </form:form>
    </body>
</html>