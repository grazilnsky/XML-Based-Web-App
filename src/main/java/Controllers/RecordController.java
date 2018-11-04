/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Maria
 */
public class RecordController extends HttpServlet {
        
    private static void addRecord(File xml,HttpServletRequest request) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(xml);
       
        Element root = document.getDocumentElement();
        Element patient = document.createElement("patient");
        root.appendChild(patient);

            Element patientName = document.createElement("patientName");
            patient.appendChild(patientName);
            Element diagnostics=document.createElement("diagnostics");
            patient.appendChild(diagnostics);
            Element diagnostic=document.createElement("diagnostic");
            diagnostics.appendChild(diagnostic);
            Element diagnosticName=document.createElement("diagnosticName");
            diagnostic.appendChild(diagnosticName);
            Element state =document.createElement("state");
            diagnostic.appendChild(state);
            Element treatments=document.createElement("treatments");
            patient.appendChild(treatments);
            Element treatment=document.createElement("treatment");
            treatments.appendChild(treatment);
            Element medication=document.createElement("medication");
            treatment.appendChild(medication);
            Element medName=document.createElement("medName");
            medication.appendChild(medName);
            Element wayOfUsage=document.createElement("wayOfUsage");
            medication.appendChild(wayOfUsage);
            Element startDate=document.createElement("startDate");
            treatment.appendChild(startDate);
            Element endDate=document.createElement("endDate");
            treatment.appendChild(endDate);
            Element immunisationDate=document.createElement("immunisationDate");
            patient.appendChild(immunisationDate);
            Element allergies=document.createElement("allergies");
            patient.appendChild(allergies);
            Element allergy=document.createElement("allergy");
            allergies.appendChild(allergy);
            Element radioImages=document.createElement("radioImages");
            patient.appendChild(radioImages);
            Element radioImage=document.createElement("radioImage");
            radioImages.appendChild(radioImage);
            Element path=document.createElement("path");
            radioImage.appendChild(path);
            Element resultTests=document.createElement("testResults");
            patient.appendChild(resultTests);
            Element testDate=document.createElement("testDate");
            resultTests.appendChild(testDate);
            Element details=document.createElement("details");
            resultTests.appendChild(details);
            Element lab=document.createElement("lab");
            resultTests.appendChild(lab);
            Element labInfo=document.createElement("labInfo");
            lab.appendChild(labInfo);
            


            patientName.appendChild(document.createTextNode(request.getParameter("name")));
            diagnosticName.appendChild(document.createTextNode(request.getParameter("diagnostic")));
            state.appendChild(document.createTextNode(request.getParameter("diagnosticState")));
            medName.appendChild(document.createTextNode(request.getParameter("medication")));
            wayOfUsage.appendChild(document.createTextNode(request.getParameter("usage")));
            startDate.appendChild(document.createTextNode(request.getParameter("startDate")));
            endDate.appendChild(document.createTextNode(request.getParameter("endDate")));
            immunisationDate.appendChild(document.createTextNode(request.getParameter("immunisation")));
            path.appendChild(document.createTextNode(request.getParameter("path")));
            testDate.appendChild(document.createTextNode(request.getParameter("testDate")));
            details.appendChild(document.createTextNode(request.getParameter("details")));
            labInfo.appendChild(document.createTextNode(request.getParameter("laboratory")));
            
            System.out.println(request.getParameter("name") + " " + request.getParameter("diagnostic"));
            
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("C:\\Anul 4\\Semantic Web\\EHR\\src\\main\\webapp\\WEB-INF\\EHR.xml"));
            transformer.transform(domSource, streamResult);
            System.out.println("Found destination, too");
            DOMSource source = new DOMSource(document);

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, ParserConfigurationException, TransformerException, SAXException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Set<String> param=(Set<String>) request.getParameterMap().keySet();
            List<String > errors=new ArrayList<>();
            File file =new File("C:\\Anul 4\\Semantic Web\\Big HW 1\\EHR Project\\web\\WEB-INF\\EHR.xml");
            System.out.println("Found source");
            addRecord(file,request);
            
            request.getRequestDispatcher("PatientController").forward(request, response);
        }
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
            try {
                processRequest(request, response);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
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
            try {
                processRequest(request, response);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RecordController.class.getName()).log(Level.SEVERE, null, ex);
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
