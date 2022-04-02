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

/**
 *
 * @author nthie
 */
public class ManageOrdersServlet extends HttpServlet {

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
            if (request.getParameter("orderid") != null && request.getParameter("do") != null){
                int orderid = Integer.parseInt(request.getParameter("orderid"));
                String action = request.getParameter("do");
                if (action.equals("approve")) {
                    OrderDAO.updateOrderStatusComplete(orderid);
                } else {
                    OrderDAO.updateOrderStatusCancel(orderid);
                }
            }
            
            String indexPage = request.getParameter("index");
            String sortby = request.getParameter("sortby");
            String order = request.getParameter("order");
            if (indexPage == null || indexPage.equals("")) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int count = OrderDAO.getTotalOrder();
            int endPage = count/10;
            if (count % 10 != 0) {
                endPage++;
            }
            ArrayList<Order> list;
            if (sortby != null && !sortby.equals("") && order != null && !order.equals("")) {
                list = OrderDAO.getOrders(sortby,order,index);
            } else {
                list = OrderDAO.getOrders(index);
            }

            request.setAttribute("orderList", list);
            request.setAttribute("endP", endPage);
            request.getRequestDispatcher("ManageOrders.jsp").forward(request, response);
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
