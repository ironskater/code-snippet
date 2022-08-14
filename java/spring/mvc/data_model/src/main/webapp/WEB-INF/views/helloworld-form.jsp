<!DOCTYPE html>
<html>
    <head>
        <title>
            hello world - input form
        </title>
    </head>
    <body>
        <!-- Example -->
        <!-- GET http://localhost:8080/upperCase?studentName=hyde -->
        <form action="upperCase" method="GET">
            <input type="text" name="studentName" placeholder="What's your name?"/>
            <input type="submit"/>
        </form>
    </body>
</html>