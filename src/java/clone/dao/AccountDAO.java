/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clone.dao;

import clone.dto.Account;
import clone.utils.DBUtilss;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nthie
 */
public class AccountDAO {

    public static boolean insertAccount(String email, String password, String fullname, String phone, int status, int role) {
        Connection cn = null;
        try {
            cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "INSERT INTO Accounts (email, password, fullname, phone, status, role) VALUES (?,?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                pst.setString(3, fullname);
                pst.setString(4, phone);
                pst.setInt(5, status);
                pst.setInt(6, role);
                pst.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    public static Account getAccount(String email, String password) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "SELECT * FROM Accounts WHERE status = 1 AND email = ? AND password = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                while (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    String Token = rs.getString("token");
                    acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role, Token);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }
    
    public static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }

        return sb.toString();
    }
    
    public static void updateToken(String token, String email) {
        try {
            Connection cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Accounts SET token = ? WHERE email = ?";
                PreparedStatement prs = cn.prepareStatement(sql);
                prs.setString(1, token);
                prs.setString(2, email);
                prs.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean updateAccountStatus(String email, int status) {
        try {
            Connection cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Accounts SET status = ? WHERE email = ?";
                PreparedStatement prs = cn.prepareStatement(sql);
                prs.setInt(1, status);
                prs.setString(2, email);
                prs.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static ArrayList<Account> getAccounts(String txtSearch, int index){
        ArrayList<Account> list = new ArrayList<>();
        try {
            Connection cn = DBUtilss.makeConnection();
            if (cn != null) {
                if (!txtSearch.equals("")) {
                    String sql = "SELECT accID, email, password, fullname, phone, status, role, token FROM Accounts WHERE email LIKE ?\n" +
                            "ORDER BY accID OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, "%"+txtSearch+"%");
                    pst.setInt(2, (index-1)*10);
                    ResultSet rs = pst.executeQuery();
                    while (rs != null && rs.next()){
                        int AccID = rs.getInt("accID");
                        String Email = rs.getString("email");
                        String Password = rs.getString("password");
                        String Fullname = rs.getString("fullname");
                        String Phone = rs.getString("phone");
                        int Status = rs.getInt("status");
                        int Role = rs.getInt("role");
                        String Token = rs.getString("token");
                        Account acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role, Token);
                        list.add(acc);
                    }
                } else {
                    String sql = "SELECT accID, email, password, fullname, phone, status, role, token FROM Accounts\n" +
                            "ORDER BY accID OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setInt(1, (index-1)*10);
                    ResultSet rs = pst.executeQuery();
                    while (rs != null && rs.next()){
                        int AccID = rs.getInt("accID");
                        String Email = rs.getString("email");
                        String Password = rs.getString("password");
                        String Fullname = rs.getString("fullname");
                        String Phone = rs.getString("phone");
                        int Status = rs.getInt("status");
                        int Role = rs.getInt("role");
                        String Token = rs.getString("token");
                        Account acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role, Token);
                        list.add(acc);
                    }
                }
                
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static int getTotalAccount(String txtSearch) {
        try {
            Connection cn = DBUtilss.makeConnection();
            if (cn != null) {                
                if (!txtSearch.equals("")) {
                    String sql = "SELECT COUNT(*) FROM Accounts WHERE email LIKE ?";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.setString(1, "%" + txtSearch + "%");
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        return rs.getInt(1);
                    }
                } else {
                    String sql = "SELECT COUNT(*) FROM Accounts";
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
                        return rs.getInt(1);
                    }
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static boolean updateAccount(String email, String newFullname, String newPhone) {
        try {
            Connection cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Accounts SET fullname = ?, phone = ? WHERE email = ?";
                PreparedStatement prs = cn.prepareStatement(sql);
                prs.setString(1, newFullname);
                prs.setString(2, newPhone);
                prs.setString(3, email);
                prs.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
