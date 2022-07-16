<!DOCTYPE html>
<html>
	<head>
		<title>Hello World - Input Form</title>
	</head>
	<body>
		<!-- At this point the browser URL/path is: http://localhost:8080/hello,
		so it needn't to add /hello path to action="processFormWithName" -->
		<form action="processFormWithName" method="GET">
			<input type="text" name="studentName"
					placeholder="What's your name?"/>
			<input type="submit" />
		</form>
	</body>
</html>