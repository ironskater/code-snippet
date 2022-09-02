<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <body>
        <h2>spring mvc - home page</h2>
        <br><br>
        <a href="student/showForm">student form</a>

        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="submit" value="Logout" />
        </form:form>
    </body>
</html>