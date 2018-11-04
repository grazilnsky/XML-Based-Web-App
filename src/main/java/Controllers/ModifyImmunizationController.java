/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
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
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Veronica Marinescu
 */
public class ModifyImmunizationController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param patientName
     * @param date
     */
    public void modifyImmunization(String patientName, String date) {

        try {
            String filepath = "C:\\Anul 4\\Semantic Web\\EHR\\src\\main\\webapp\\WEB-INF\\EHR.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            // Get the root element
            Node patients = doc.getFirstChild();
            Node immDate;

            NodeList patientList = doc.getElementsByTagName("patientName");
            for (int i = 0; i < patientList.getLength(); i++) {
                if (patientList.item(i).getTextContent().equals(patientName)) {
                    immDate = doc.getElementsByTagName("immunisationDate").item(i);
                    immDate.setTextContent(date);
                    System.out.println("Found " + patientName);
                }
                System.out.println("Patient " + i + ": " + patientList.item(i).getTextContent());
            }

            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

            System.out.println("Done");

        } catch (ParserConfigurationException | TransformerException | SAXException | IOException e) {


        }

        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        Enumeration en = request.getParameterNames();
        String date = "", patientName = "";
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-mm-dd");

        while (en.hasMoreElements()) {
            Object objOri = en.nextElement();
            String param = (String) objOri;
            String value = request.getParameter(param);
            
            if ("dateInput".equals(param)) {
                if (!value.equals("")) {
                    Date inputDate = sm.parse(value);
                    date = sm.format(inputDate);
                    System.out.println(param + ": " + date);

                }
                

            }
            if ("patientName".equals(param)) {
                    patientName = value;
                    System.out.println(param + ": " + patientName);
                }
        }
        
        modifyImmunization(patientName, date);
        request.getRequestDispatcher("patients.jsp").forward(request, response);
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
        } catch (ParseException ex) {
            Logger.getLogger(ModifyImmunizationController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(ModifyImmunizationController.class.getName()).log(Level.SEVERE, null, ex);
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
