<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>
            Student Confirmation Page
        </title>
    </head>
    <body>
        <!--
        ${student.firstName} equals student.getFirstName()
        ${student.lastName} equals student.getLastName() -->
        The student is confirmed: ${student.firstName} ${student.lastName}
        <br><br>

        Operating Systems:
            <!-- ul means unordered list -->
            <ul>
                <c:forEach var = "temp" items = "${student.operatingSystems}">
                    <li> ${temp} </li>
                </c:forEach>
            </ul>
    </body>
</html>