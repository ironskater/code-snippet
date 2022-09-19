* Spring Data REST will scan JpaRepository in project, and expose REST APIs for each entity type for your JpaRepository

* Simple pluralized form for endpoints
    * First charactor of Entity type is lowercase
    * Then just adds an "s" to the entity
    * Example: entity Employee -> /employees

* CRUD
    * POST /employees
    * GET /employees
    * GET /employees/{employeeId}
    * PUT /employees/{employeeId}
    * DELETE /employees/{employeeId}

* We only need 3 items for creating Spring Data REST endpoints
    * entity, such as Employee
    * JpaRepository, such as EmployeeRepository extends JpaRepository
    * Maven Dependency: spring-boot-starter-data-rest

* Architecture
    * Before we use spring data rest, the architecture is: controller -> service -> repository -> db
    * After we use spring data rest, the architecture is: endpoints crerated by spring data rest -> repository -> db

* spring data rest endpoints are HATEOAS compliant

* Use @RepositoryRestResource to specify the plural name path

* Pagination
	* By default, spring data rest will return the first 20 elements(Page size =20)
	* we can naviate to the different pages of data using query param
	* Note that the pages are zero-based
		```
		http://localhost:8080/employees?page=0
		http://localhost:8080/employees?page=1
		```
* Sorting
	* Suppose we have the properties in Employee entity: firstName, lastName, and email
	* Sort by last name(ascending is default)
		```
		http://localhost:8080/employees?sort=lastName
		```
	* Sort by first name, descending
		```
		http://localhost:8080/employees?sort=firstName,desc
		```
	* Sort by last name, then first name, ascending
		```
		http://localhost:8080/employees?sort=lastName,firstName,asc
		```



