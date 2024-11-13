<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Save Customer</title>

        <link type="text/css"
            rel="stylesheet"
            href="${pageContext.request.contextPath}/resources/css/style.css" />

        <link type="text/css"
            rel="stylesheet"
            href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />
    </head>>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>CRM - Customer Relationship Manager</h2>
            </div>
        </div>
        <div id="container">
            <h3>Save Customer</h3>

            <!-- add new customer -->
            <!-- or show the pre-populate customer data -->
            <form:form action="saveCustomer" modelAttribute="customer" method="POST">
                <!-- need to associate this data with customer id -->
                <!-- when this form is loaded, call customer.id and place it in hidden form field,
                so that when we submit data, we can update data by id -->
                <!-- without this line, we will lose the id information in this context -->
                <form:hidden path="id" />

                <table>
                    <tbody>
                        <tr>
                            <td><label>First name:</label></td>
                            <td><form:input path="firstName" /></td>
                        </tr>
                        <tr>
                            <td><label>Last name:</label></td>
                            <td><form:input path="lastName" /></td>
                        </tr>
                        <tr>
                            <td><label>Email:</label></td>
                            <td><form:input path="email" /></td>
                        </tr>
                        <tr>
                            <td><label></label></td>
                            <td><input type=submit value="Save" class="save" /></td>
                        </tr>
                    </tbody>
                </table>
            </form:form>

            <!-- add go back button -->
            <div style="clear; both;"></div>
            <p>
                <a href="${pageContext.request.contextPath}/customers/list" >Back to List</a>
            </p>
        </div>
    </body>
</html>