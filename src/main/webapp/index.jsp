<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>Register Patient - EHR</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css"> </link>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        <div class="container">
            <h2 id="login-heading" class="text-center">Register health record</h2>



            <form action="RecordController" method="post">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" name="name" id="name"
                           placeholder="Name" class="form-control" required>
                </div>
                <h3>Diagnostic</h3>
                <br>
                <div class="form-group">
                    <label for="diagnostic">Diagnostic</label>
                    <input type="text" name="diagnostic" id="diagnostic"
                           placeholder="Diagnostic" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="diagnosticState">Diagnostic State</label>
                    <input type="text" name="diagnosticState" id="diagnosticState"
                           placeholder="Diagnostic" class="form-control" required>
                </div>
                <br>
                <h3>Treatment</h3>
                <br>
                <div class="form-group">
                    <label for="medication">Medication</label>
                    <input type="text" name="medication" id="medication"
                           placeholder="Medication" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="usage">Way of usage</label>
                    <input type="text" name="usage" id="usage"
                           placeholder="Usage" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="startDate">Start Date</label>
                    <input type="date" name="startDate" id="startDate"
                           placeholder="StartDate" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="endDate">End Date</label>
                    <input type="date" name="endDate" id="endDate"
                           placeholder="EndDate" class="form-control" required>
                </div>
                <br><br>
                <div class="form-group">
                    <label for="immunisation">Immunisation Date</label>
                    <input type="date" name="immunisation" id="immunisation"
                           placeholder="Immunisation" class="form-control" required>
                </div>
                <h3>Allergies</h3>
                <br><br>
                <div class="form-group">
                    <label for="allergy">Allergy</label>
                    <input type="text" name="allergy" id="allergy"
                           placeholder="allergy" class="form-control" >
                </div>


                <div class="form-group">
                    <label for="radioimage">Radio image </label>
                    Select a file: <input type="file" name="path" id="path"><br>
                </div>
                <br>
                <h3>Test Results</h3>
                <br>
                <div class="form-group">
                    <label for="testDate">Test Date</label>
                    <input type="date" name="testDate" id="testDate"
                           placeholder="testDate" class="form-control" >
                </div>

                <div class="form-group">
                    <label for="details">Details</label>
                    <input type="text" name="details" id="details"
                           placeholder="details" class="form-control" >
                </div>

                <div class="form-group">
                    <label for="laboratory">Laboratory</label>
                    <input type="text" name="laboratory" id="laboratory"
                           placeholder="laboratory" class="form-control" >
                </div>
                <div class="form-element">
                    <input type="submit" value="Add record">
                </div>
            </form>

    </body>
</html>
