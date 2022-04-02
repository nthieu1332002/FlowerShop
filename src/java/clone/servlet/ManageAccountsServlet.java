/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clone.servlet;

import clone.dao.AccountDAO;
import clone.dto.Account;
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
public class ManageAccountsServlet extends HttpServlet {

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
            if (request.getParameter("email") != null && request.getParameter("status") != null){
                String email = request.getParameter("email");
                int status = Integer.parseInt(request.getParameter("status"));
                if (status == 1) {
                    AccountDAO.updateAccountStatus(email, 0);
                } else {
                    AccountDAO.updateAccountStatus(email, 1);
                }
            }
            
            String indexPage = request.getParameter("index");
            String txtSearch = request.getParameter("txtSearch");
            if (txtSearch == null) {
                txtSearch = "";
            }
            System.out.println(txtSearch);
            if (indexPage == null || indexPage.equals("")) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            int count = AccountDAO.getTotalAccount(txtSearch);
            int endPage = count/10;
            if (count % 10 != 0) {
                endPage++;
            }
            ArrayList<Account> list = AccountDAO.getAccounts(txtSearch, index);
            request.setAttribute("accountList", list);
            request.setAttribute("endP", endPage);
            request.getRequestDispatcher("ManageAccounts.jsp").forward(request, response);
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
