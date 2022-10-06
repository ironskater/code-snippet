* To use the default JDBC authentication, we need to follow the default JDBC authentication schema
* user: john, mary, and susan; pw: test123
* pw stored in DB folllow the format: {bcrypt}encodedPassword. By {bcrypt} hint, Spring Security know the passwords are stored as encrypted passwords