<!DOCTYPE html>
<html>
	<head>
		<title>Student Confirmation</title>
	</head>

	<body>
		The student is confirmed: ${student.firstName} ${student.lastName}

		<br><br>

		<!-- spring will call student.getCountry() -->
		Country: ${student.country}
	</body>
</html>