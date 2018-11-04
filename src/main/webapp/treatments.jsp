<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Treatments - EHR</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css"> </link>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        <%@include file="/WEB-INF/treatments.html" %>	
        <p></p>
        <form action="FilterController" method="post">

            <label for="testDate">Select the date you want to filter immunizations by.</label>
            <input type="date" name="dateInput" id="dateInput"
                   placeholder="dateInput" class="form-control" >

            <br/>
            <br/>
            <label for="testDate">Search for a medication to display information about it.</label>
            <input type="text" name="medInput" id="medInput"
                   placeholder="medInput" class="form-control" >

            <div class="form-element">
                <input type="submit" value="submit">
            </div>
        </form>
    </body>
</html>
