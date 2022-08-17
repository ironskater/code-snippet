* flow chart
	* GET http://localhost:8080/
		-> Go to HomeController.home(), and return main-menu.jsp
		-> GET http://localhost:8080/student/showForm
		-> Go to StudentController.showForm(),
			and create model, then set attribute "student",
			and return "student-form"
		-> fill out the form-data: first name, last name, then submit
		-> POST http://localhost:8080/student/processForm
		-> GO to StudentController.processForm(), return student-confirmation
		-> show ${student.firstName} ${student.lastName}