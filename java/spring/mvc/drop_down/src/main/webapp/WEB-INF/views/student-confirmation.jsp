<!DOCTYPE html>
<html>
    <head>
        <title>
            Student Confirmation Page
        </title>
    </head>
    <body>
        <!--
        ${student.firstName} equals student.getFirstName()
        ${student.lastName} equals student.getLastName() -->
        The student is confirmed: ${student.firstName} ${student.lastName}
        <br><br>
        <!-- spring will call student.getCountry() for ${student.country} -->
        His/Her country is ${student.country}
        <br><br>
        BloodType is ${student.bloodType}
    </body>
</html>