* just add the following dependency
	```xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-actuator</artifactId>
	</dependency>
	```

* and add the following properties to application.properties
	```java
	info.app.name=My Custom App
	info.app.description=A crazy man
	info.app.version=9.5.2.7
	```

* In this example, we also add security; to do so, we need to add the following dependency
	```xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
	</dependency>
	```
	* The default username is: user
	* The default password will be showed in console

