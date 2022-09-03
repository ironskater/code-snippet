<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <body>
        <h2>spring mvc - home page</h2>
        <br><br>
        <a href="student/showForm">student form</a>

        <hr>

            <p>
                <a href="${pageContext.request.contextPath}/leaders">LeaderShip Meeting</a>
                (Only for Manager peeps)
            </p>

            <p>
                <a href="${pageContext.request.contextPath}/systems">IT Admin Meeting</a>
                (Only for Admin peeps)
            </p>

        <hr>

        <br><br>
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input type="submit" value="Logout" />
        </form:form>
    </body>
</html>