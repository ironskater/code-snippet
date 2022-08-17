<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <body>
        <form:form action = "processForm" modelAttribute = "student">
            First name: <form:input path = "firstName" />
            <br><br>

            Last name: <form:input path = "lastName" />
            <br><br>

            Operating Systems:
            <form:checkbox path = "operatingSystems" value = "Linux" /> Linux
            <form:checkbox path = "operatingSystems" value = "MacOS" /> MacOS
            <form:checkbox path = "operatingSystems" value = "Windows" /> Windows

            <input type = "submit" value = "Submit_Button" />
        </form:form>
    </body>
</html>