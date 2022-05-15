<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Student registration title</title>
	</head>

	<body>
		<form:form action="processForm" modelAttribute="student">
			<!-- firstName is the property of Student class -->
			First Name: <form:input path="firstName" />

			<br><br>

			<!--lastName is also the property of Student class -->
			Last Name: <form:input path="lastName" />

			<br><br>

			Country:

			<!-- on submit, spring will call student.setCountry(...) -->
			<form:select path="country">
				<!-- 'items' refer to the collection of data -->
				<form:options items="${countryByOptions}" />
			</form:select>

			<br><br>

			<!-- when submit, spring will call setFirstName and setLastName setter methods -->
			<input type="submit" value="Submit" />

		</form:form>
	</body>
</html>