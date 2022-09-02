* In fancy-login jsp, it uses regular HTML form tag in line35,
so that it will not have csrf protection.
* When we try to login, we will get 403 forbidden
* Note that this result only can be observed if we use outside tomcat server,
if we use the embedded tomcat, it just redirect to the same login page
<br><br>
* If we want to keep csrf protection and use regular HTML form tag, we can add token manually.
Just like the example in line78 of fancy-login.jsp
* Note that we only add token manually in fancy-login.jsp, so that we can login normally. However, because in main-menu.jsp also use regular HTML form tag, and it doesn't add token manually, it will face the same 403 forbidden when logout.