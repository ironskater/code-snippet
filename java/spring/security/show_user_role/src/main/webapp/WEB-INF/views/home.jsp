<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
    <head>
        <title>luv2code Company Home Page</title>
    </head>

    <body>
        <h2>luv2code Company Home Page</h2>
        <hr>

        <p>
            Welcome to the luv2code company home page!
        </p>
        <hr>
        <p>
            User: <security:authentication property="principal.username" />
            <br><br>
            Roles: <security:authentication property="principal.authorities" />
        </p>

        <!-- Add a logout button -->
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="submit" value="Logout" />
        </form:form>
    </body>
</html>