<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Delete Allergy - EHR</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css"> </link>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        <%@include file="/WEB-INF/EHR.html" %>	
        <br/>
        <form action="DeleteAllergyController" method="post">


            <label for="patientName">Select the name of the patient.</label>
            <select name="patientName" id="patientName">
                <c:forEach items="${patientList}" var="patient">
                    <option value = "<c:out value="${patient}"/>"><c:out value="${patient}"/></option>
                </c:forEach>
            </select>
            <br/>
            <br/>

            <label for="dateInput">Select allergy you want to delete.</label>
            <select name="allergyInput" id="patientName">
                <c:forEach items="${allergyList}" var="allergy">
                    <option value = "<c:out value="${allergy}"/>"><c:out value="${allergy}"/></option>
                </c:forEach>
            </select>


            <div class="form-element">
                <input type="submit" value="submit">
            </div>
        </form>
    </body>
</html>
