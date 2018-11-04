<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Misc. Info - EHR</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style.css"> </link>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/navbar.jspf" %>
        <p>Filtered by Immunization Date ${requestScope.inputDate}.</p>
        <p>- ${requestScope.filterImmunization}</p>
        <p></p>
        <p>Patients who have done their lab tests in the last 3 months.</p>
        <p>- ${requestScope.lastThreeMonths}</p>
        <p></p>
        <p>Labs visited in the last 5 years.</p>
        <p>- ${requestScope.fiveYears}</p>
        <p>Allergies and patients suffering from them</p>
        <p>- ${requestScope.allergyCounter}</p>
        <p>Information about ${requestScope.medInput}.</p>
        <p>- ${requestScope.medicationInfo}</p>
    </body>
</html>
