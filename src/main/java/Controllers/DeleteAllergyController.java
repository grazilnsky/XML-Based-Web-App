/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Maria
 */
public class DeleteAllergyController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void deleteAllergy(String patientName, String allergyName) {

        try {
            String filepath = "C:\\Anul 4\\Semantic Web\\EHR\\src\\main\\webapp\\WEB-INF\\EHR.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            // Get the root element
            Node patients= doc.getFirstChild();
            Node allergies;
            NodeList allergyList;

       
            
            NodeList patientList = doc.getElementsByTagName("patientName");
            for(int i = 0; i < patientList.getLength(); i++)
            {
                
                if(patientList.item(i).getTextContent().equals(patientName)) {
                    System.out.println(patientList.item(i).getTextContent());
                    allergies = doc.getElementsByTagName("allergies").item(i);
                    allergyList = allergies.getChildNodes();
                    for(int j = 0; j<allergyList.getLength(); j++)
                    {
                        
                        System.out.println(j + " " + allergyList.item(j).getTextContent());
                        if(allergyList.item(j).getTextContent().equals(allergyName))
                        {
                            System.out.println("REMOVED.");
                            allergyList.item(j).getParentNode().removeChild(allergyList.item(j));
                        }
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

            System.out.println("Done");

        } catch (ParserConfigurationException | TransformerException | SAXException | IOException e) {
            // TODO Auto-generated catch block

        }
        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Enumeration en = request.getParameterNames();
            String allergyName = "", patientName = "";
            SimpleDateFormat sm = new SimpleDateFormat("yyyy-mm-dd");

            while (en.hasMoreElements()) {
                Object objOri = en.nextElement();
                String param = (String) objOri;
                String value = request.getParameter(param);

                if ("allergyInput".equals(param)) {
                    allergyName = value;
                    System.out.println(param + ": " + allergyName);
                }
                if ("patientName".equals(param)) {
                    patientName = value;
                    System.out.println(param + ": " + patientName);
                }
            }

            deleteAllergy(patientName, allergyName);
            request.getRequestDispatcher("patients.jsp").forward(request, response);
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
            processRequest(request, response);
        } catch (ParserConfigurationException | SAXException | XPathExpressionException ex) {
            Logger.getLogger(DeleteAllergyController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParserConfigurationException | SAXException | XPathExpressionException ex) {
            Logger.getLogger(DeleteAllergyController.class.getName()).log(Level.SEVERE, null, ex);
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
