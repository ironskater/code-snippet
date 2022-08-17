<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <body>
        <form:form action = "processForm" modelAttribute = "student">
            First name: <form:input path = "firstName" />
            <br><br>

            Last name: <form:input path = "lastName" />
            <br><br>

            Favorite Language:
            Java <form:radiobutton path = "favoriteLanguage" value = "Java" />
            C# <form:radiobutton path = "favoriteLanguage" value = "C#" />
            PHP <form:radiobutton path = "favoriteLanguage" value = "PHP" />
            Ruby <form:radiobutton path = "favoriteLanguage" value = "Ruby" />

            <input type = "submit" value = "Submit_Button" />
        </form:form>
    </body>
</html>