/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.*;
import org.w3c.dom.*;

/**
 *
 * @author Veronica Marinescu
 */
public class FilterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param date
     * @return
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws IOException if an I/O error occurs
     * @throws javax.xml.xpath.XPathExpressionException
     */
    public NodeList filterImmunization(String date) throws ParserConfigurationException, SAXException, IOException,
            XPathExpressionException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse("C:\\Anul 4\\Semantic Web\\EHR\\src\\main\\webapp\\WEB-INF\\EHR.xml");
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        XPathExpression expr = xpath.compile("//patients/patient[immunisationDate/text()='" + date + "']/patientName");

        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        return nodes;
    }

    public NodeList lastThreeMonths() throws ParserConfigurationException, SAXException, IOException,
            XPathExpressionException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse("C:\\Anul 4\\Semantic Web\\EHR\\src\\main\\webapp\\WEB-INF\\EHR.xml");
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        int month = Calendar.getInstance().get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String monthStr;
        month = month + 1;
        if (month <= 3) {
            month = 12 - month;
            year = year - 1;
        } else {
            month = month - 3;
        }
        if (month < 10) {
            monthStr = "0" + Integer.toString(month);
        } else {
            monthStr = Integer.toString(month);
        }
        //System.out.println(monthStr + Integer.toString(year));
        XPathExpression expr = xpath.compile("//patients/patient/testResults[number(translate(testDate,'-','')) > " + Integer.toString(year) + monthStr + "01]/parent::patient/patientName");

        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        return nodes;
    }

    public String fiveYears() throws ParserConfigurationException, SAXException, IOException,
            XPathExpressionException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse("C:\\Anul 4\\Semantic Web\\EHR\\src\\main\\webapp\\WEB-INF\\EHR.xml");
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        String finalString = "";
        int year = Calendar.getInstance().get(Calendar.YEAR) - 5;
        XPathExpression expr = xpath.compile("//patients/patient/testResults[number(translate(testDate,'-','')) > " + Integer.toString(year) + "0101]/lab/labInfo");

        Map<String, Integer> appearances = new HashMap<String, Integer>();
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            if (appearances.containsKey(nodes.item(i).getTextContent())) {
                appearances.put(nodes.item(i).getTextContent(), appearances.get(nodes.item(i).getTextContent()) + 1);
                // System.out.println("Incremented " + nodes.item(i).getTextContent()+ "'s value by 1: " + appearances.get(nodes.item(i).getTextContent()));
            } else {
                appearances.put(nodes.item(i).getTextContent(), 1);
                // System.out.println("Added new entry: " + nodes.item(i).getTextContent());
            }

        }

        for (Map.Entry<String, Integer> entry : appearances.entrySet()) {
            if (entry.getValue() > 1) {
                if (finalString.equals("")) {
                    finalString = entry.getKey();
                } else {
                    finalString = finalString + ", " + entry.getKey();
                }
            }
        }
        return finalString;
    }

    public String allergyCounter() throws ParserConfigurationException, SAXException, IOException,
            XPathExpressionException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse("C:\\Anul 4\\Semantic Web\\EHR\\src\\main\\webapp\\WEB-INF\\EHR.xml");
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        XPathExpression expr = xpath.compile("//patients/patient/allergies/allergy");

        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        Map<String, Integer> allergyCounter = new HashMap<String, Integer>();
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            if (allergyCounter.containsKey(nodes.item(i).getTextContent())) {
                allergyCounter.put(nodes.item(i).getTextContent(), allergyCounter.get(nodes.item(i).getTextContent()) + 1);
                // System.out.println("Incremented " + nodes.item(i).getTextContent() + "'s value by 1: " + allergyCounter.get(nodes.item(i).getTextContent()));
            } else {
                allergyCounter.put(nodes.item(i).getTextContent(), 1);
                //System.out.println("Added new entry: " + nodes.item(i).getTextContent());
            }

        }
        String finalString = "";
        for (Map.Entry<String, Integer> entry : allergyCounter.entrySet()) {
            if (finalString.equals("")) {
                finalString = entry.getKey() + ": " + entry.getValue();
            } else {
                finalString = finalString + "<br/>- " + entry.getKey() + ": " + entry.getValue();

            }
        }
        return finalString;
    }

    String medicationInfo(String medInput) throws ParserConfigurationException, SAXException, IOException,
            XPathExpressionException {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse("C:\\Anul 4\\Semantic Web\\EHR\\src\\main\\webapp\\WEB-INF\\EHR.xml");
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        XPathExpression expr = xpath.compile("//patients/patient/treatments/treatment/medication[medName='" + medInput + "']/wayOfUsage");

        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;

        List<String> duplicateRemoval = new ArrayList<String>();
        for (int i = 0; i < nodes.getLength(); i++) {
            //System.out.println(nodes.item(i).getTextContent());
            if (!duplicateRemoval.contains(nodes.item(i).getTextContent())) {
                duplicateRemoval.add(nodes.item(i).getTextContent());
            }
        }

        String finalString = "";

        for (String entry : duplicateRemoval) {
            finalString = finalString + entry;
        }

        return finalString;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParserConfigurationException, SAXException, XPathExpressionException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

        Enumeration en = request.getParameterNames();
        String date = "", medInput = "";
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-mm-dd");
        String fiveYearsResult = "", lastThreeMonthsResult = "", filterImmunizationResult = "", allergyResult = "", medicationInfoResult = "";

        while (en.hasMoreElements()) {
            Object objOri = en.nextElement();
            String param = (String) objOri;
            String value = request.getParameter(param);
            System.out.print(param + " " + value);
            if ("dateInput".equals(param)) {
                if (!value.equals("")) {
                    Date inputDate = sm.parse(value);
                    date = sm.format(inputDate);
                    System.out.println(date);

                    NodeList filterImmunization = filterImmunization(date);

                    for (int i = 0; i < filterImmunization.getLength(); i++) {
                        if (i != 0) {
                            filterImmunizationResult = filterImmunizationResult + ", " + filterImmunization.item(i).getTextContent();
                        } else {
                            filterImmunizationResult = filterImmunizationResult + filterImmunization.item(i).getTextContent();
                        }
                    }
                } else {
                    filterImmunizationResult = "No date entered. I am a sad search engine now :(.";
                }

            }
            if ("medInput".equals(param)) {
                medInput = value;
            }

        }

        String fiveYears = fiveYears();
        NodeList lastThreeMonths = lastThreeMonths();

        for (int i = 0; i < lastThreeMonths.getLength(); i++) {
            if (i != 0) {
                lastThreeMonthsResult = lastThreeMonthsResult + ", " + lastThreeMonths.item(i).getTextContent();
            } else {
                lastThreeMonthsResult = lastThreeMonthsResult + lastThreeMonths.item(i).getTextContent();
            }
        }

        if (fiveYears.equals("")) {
            fiveYearsResult = "No lab was visited more than once in the last 5 years.";
        } else {
            fiveYearsResult = fiveYears;
        }

        if (allergyCounter().equals("")) {
            allergyResult = "There are no patients with allergies currently registered.";
        } else {
            allergyResult = allergyCounter();
        }

        if (medicationInfo(medInput).equals("")) {
            if (!medInput.equals("")) {
                medicationInfoResult = "There is no such medication as " + medInput + ".";
                request.setAttribute("medInput", medInput);
            } else {
                medicationInfoResult = "You must enter a medication name for this to work.";
                request.setAttribute("medInput", "NO INPUT MEDICATION");
            }
        } else {
            medicationInfoResult = medicationInfo(medInput);
            request.setAttribute("medInput", medInput);
        }
        request.setAttribute("inputDate", date);
        request.setAttribute("fiveYears", fiveYearsResult);
        request.setAttribute("lastThreeMonths", lastThreeMonthsResult);
        request.setAttribute("filterImmunization", filterImmunizationResult);
        request.setAttribute("allergyCounter", allergyResult);
        
        request.setAttribute("medicationInfo", medicationInfoResult);
        request.getRequestDispatcher("filteredData.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TreatmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(TreatmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(TreatmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(FilterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TreatmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(TreatmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(TreatmentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(FilterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
