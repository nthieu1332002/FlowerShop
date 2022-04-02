/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clone.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nthie
 */
public class mainController extends HttpServlet {
    private String url = "errorpage.html";
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            switch (action) {
                case "register":
                    url = "RegisterServlet";
                    break;
                case "manageIndex":
                    url = "ManageIndexServlet";
                    break;
                case "managePersonal":
                    url = "ManagePersonalServlet";
                    break;
                case "addtocart":
                    url = "AddToCartServlet";
                    break;
                case "updatecart":
                    url = "UpdateCartServlet";
                    break;
                case "deletecart":
                    url = "DeleteCartServlet";
                    break;
                case "saveOrder":
                    url = "SaveCartServlet";
                    break;
                case "login":
                    url = "SignInServlet";
                    break;
                case "logout":
                    url = "LogOutServlet";
                    break;
                case "manageAccounts":
                    url = "ManageAccountsServlet";
                    break;
                case "manageOrders":
                    url = "ManageOrdersServlet";
                    break;
                case "managePlants":
                    url = "ManagePlantsServlet";
                    break;
                case "manageCategories":
                    url = "ManageCategoriesServlet";
                    break;
                case "addnewplant":
                    url = "AddNewPlantServlet";
                    break;
                case "detailProduct":
                    url = "GetPlantServlet";
                    break;
                case "manageOrderDetail":
                    url = "ManageOrderDetailServlet";
                    break;
                case "Update":
                    url = "UpdateProfileServlet";
                    break;
                case "cancelorder":
                    url = "CancelOrderServlet";
                    break;
                case "reorder":
                    url = "ReorderServlet";
                    break;
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
