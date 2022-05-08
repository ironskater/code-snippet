<!DOCTYPE html>
<html>
	<head>
		<title>Hello World - Input Form</title>
	</head>
	<body>
		<!-- Request URL: http://localhost:8080/processFormWithName?studentName=hyde
				Request Method: GET -->
		<form action="processFormWithName" method="GET">
			<input type="text" name="studentName"
					placeholder="What's your name?"/>
			<input type="submit" />
		</form>
	</body>
</html>