<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="codesnippet.prototype.utils.SortUtils" %>

<!DOCTYPE html>
<html>
<head>
    <title>List Customers</title>
    <link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>
    <div id="container">
        <div id="content">

            <!-- add customer button -->
            <input type="button" value="Add Customer"
                onclick="window.location.href='showFormForAdd'; return false;"
                class="add-button"
            />

             <!--  add a search box -->
             <!-- GET http://localhost:8080/customers/search?theSearchName={value} -->
            <form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" />

                <input type="submit" value="Search" class="add-button" />
            </form:form>

            <!-- Sorting -->
            <!-- construct a sort link for first name -->
            <c:url var="sortLinkFirstName" value="/customers/list">
                <c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" />
            </c:url>
            <!-- construct a sort link for last name -->
				<c:url var="sortLinkLastName" value="/customers/list">
					<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>" />
				</c:url>
            <!-- construct a sort link for email -->
            <c:url var="sortLinkEmail" value="/customers/list">
                <c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>" />
            </c:url>

            <!-- cusotmers datatable -->
            <table>
                <!-- datatable header -->
                <tr>
					<th><a href="${sortLinkFirstName}">First Name</a></th>
					<th><a href="${sortLinkLastName}">Last Name</a></th>
					<th><a href="${sortLinkEmail}">Email</a></th>
					<th>Action</th>
				</tr>


                <!-- datatable contents -->
                <c:forEach var="element" items="${customers}">

                    <!-- construct an update link with customer id -->
                    <c:url var="updateLink" value="showFormForUpdate">
                        <!-- name="customerId" is the key name,
                        for example: http://localhost:8080/customers/showFormForUpdate?customerId=1 -->
                        <c:param name="customerId" value="${element.id}" />
                    </c:url>

                    <!-- construct an delete link with customer id -->
                    <c:url var="deleteLink" value="/customers/delete">
                        <!-- name="customerId" is the key name,
                        for example: http://localhost:8080/customers/delete?customerId=1 -->
                        <c:param name="customerId" value="${element.id}" />
                    </c:url>

                    <tr>
                        <td>${element.firstName}</td>
                        <td>${element.lastName}</td>
                        <td>${element.email}</td>

                        <!-- display the update link -->
                        <td>
                            <a href="${updateLink}">Update</a>
                            |
                            <!-- here we add javescript to create pop-up to user, so that user can confirm whether the customer is to be deleted -->
                            <a href="${deleteLink}"
                            onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>