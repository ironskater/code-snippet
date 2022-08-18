<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Customer Registration Form</title>

        <!-- define css style, define the color of error message -->
        <style>
            .error {color:red}
        </style>

    </head>
    <body>
        <i>Fill out the form. Asterisk(*) means required.</i>

        <form:form action = "processForm" modelAttribute = "customer">
            First name: <form:input path = "firstName" />
            <br><br>

            Last name(*): <form:input path = "lastName" />
            <!-- "error" refers to error class defined in head.style -->
            <form:errors path = "lastName" cssClass = "error" />
            <br><br>

            Course Code: <form:input path = "courseCode" />
            <form:errors path = "courseCode" cssClass = "error" />
            <br><br>

            <input type = "submit" value = "Submit_Button" />
        </form:form>
    </body>
</html>