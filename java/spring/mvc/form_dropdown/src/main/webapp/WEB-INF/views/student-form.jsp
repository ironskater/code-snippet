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
				<!-- The label is what the user will actually see on the screen -->
				<!-- The value is the actual code that you will pass over when you submit -->
				<form:option value="Brazil valueeeeeee" label="Brazil" />
				<form:option value="France valueeeeeee" label="France" />
				<form:option value="Germany valueeeeeee" label="Germany" />
				<form:option value="India valueeeeeee" label="India" />
			</form:select>

			<br><br>

			<!-- when submit, spring will call setFirstName and setLastName setter methods -->
			<input type="submit" value="Submit" />

		</form:form>
	</body>
</html>