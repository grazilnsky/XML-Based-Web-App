<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Modify Immunization - EHR</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css"> </link>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        <form action="ModifyImmunizationController" method="post">


            <label for="patientName">Select the name of the patient.</label>
            <select name="patientName" id="patientName">
                <c:forEach items="${patientList}" var="patient">
                    <option value = "<c:out value="${patient}"/>"><c:out value="${patient}"/></option>
                </c:forEach>
            </select>
            <br/>
            <br/>

            <label for="dateInput">Select the new immunization date.</label>
            <input type="date" name="dateInput" id="dateInput"
                   placeholder="dateInput" class="form-control" >


            <div class="form-element">
                <input type="submit" value="submit">
            </div>
        </form>
    </body>
</html>
