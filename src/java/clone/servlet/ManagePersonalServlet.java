/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clone.servlet;

import clone.dao.OrderDAO;
import clone.dto.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nthie
 */
public class ManagePersonalServlet extends HttpServlet {

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
            if (request.getParameter("orderid") != null) {
                int orderid = Integer.parseInt(request.getParameter("orderid"));
                OrderDAO.updateOrderStatusCancel(orderid);
            }

            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            String indexPage = request.getParameter("index");
            if (indexPage == null || indexPage.equals("")) {
                indexPage = "1";
            }
            int count = OrderDAO.getTotalOrder(email);
            int endPage = count/10;
            if (count % 10 != 0) {
                endPage++;
            }
            int index = Integer.parseInt(indexPage);
            String datefrom = request.getParameter("from");
            String dateto = request.getParameter("to");
            if (request.getParameter("from") == null && request.getParameter("to") == null) {
                datefrom = "";
                dateto = "";
            }
            ArrayList<Order> list = OrderDAO.getOrders(email, datefrom, dateto, index);
            request.setAttribute("orderList", list);
            request.setAttribute("endP", endPage);
            request.getRequestDispatcher("PersonalPage.jsp").forward(request, response);
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
