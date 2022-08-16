<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <body>
        <form:form action = "processForm" modelAttribute = "student">
            First name: <form:input path = "firstName" />
            <br><br>

            Last name: <form:input path = "lastName" />
            <br><br>

            Country:
            <!-- Once submit, spring will call student.setCountry(...) -->
            <form:select path = "country">
                <form:option value = "value[Brazil]" label = "Brazil" />
                <form:option value = "value[France]" label = "France" />
                <form:option value = "value[Germany]" label = "Germany" />
                <form:option value = "value[India]" label = "India" />
            </form:select>
            <br><br>

            BloodType:
            <form:select path = "bloodType">
                <!-- 注意"${bloodTypeOptions}"需有double quote -->
                <form:options items = "${bloodTypeOptions}" />
            </form:select>

            <input type = "submit" value = "Submit_Button" />
        </form:form>
    </body>
</html>