<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <body>
        <form:form action = "processForm" modelAttribute = "student">
            First name: <form:input path = "firstName" />
            <br><br>
            Last name: <form:input path = "lastName" />
            <br><br>
            <input type = "submit" value = "Submit_Button" />
        </form:form>
    </body>
</html>