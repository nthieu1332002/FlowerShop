/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clone.dao;

import clone.dto.Order;
import clone.dto.OrderDetail;
import clone.utils.DBUtilss;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author nthie
 */
public class OrderDAO {
    public static boolean insertOrder(String email, HashMap<String,Integer> cart) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtilss.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                String sql = "SELECT * from Accounts WHERE email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs!=null && rs.next()) {
                    accid = rs.getInt("accID");
                }
                
                Date d = new Date(System.currentTimeMillis());
                sql = "INSERT Orders(OrdDate, status, AccID) VALUES(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();
                
                sql = "SELECT TOP 1 orderID from Orders order by orderID DESC";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs!=null && rs.next()) {
                    orderid = rs.getInt("orderID");
                }
                Set<String>pids = cart.keySet();
                for (String pid : pids) {
                    sql = "SELECT status FROM Plants WHERE PID = ?";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, Integer.parseInt(pid.trim()));
                    rs = pst.executeQuery();
                    if (rs!=null && rs.next()) {
                        int status = rs.getInt("status");
                        if (status == 0) {
                            return false;
                        }
                    }

                    sql = "INSERT OrderDetails VALUES(?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            } else {
                System.out.println("Ko chen Order dc");
            }
        } catch (Exception e) {
            try {
                if (cn!=null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    public static int getTotalOrder(String email) {
        Connection cn = null;
        try {
            cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "SELECT COUNT(*) FROM Orders o LEFT JOIN Accounts a ON o.accID = a.accID WHERE a.email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs!=null && rs.next()) {
                    return rs.getInt(1);
                }
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
        return 0;
    }

    public static ArrayList<Order> getOrders(String email, String datefrom, String dateto, int index) {
        Connection cn = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            cn = DBUtilss.makeConnection();
            if (cn != null) {
                PreparedStatement pst;
                if (!datefrom.equals("") && !dateto.equals("")) {
                    String sql = "SELECT * FROM Orders o LEFT JOIN Accounts a ON o.AccID = a.accID \n"
                            + "WHERE a.email = ? AND o.OrdDate BETWEEN ? AND ?\n"
                            + "ORDER BY o.OrderID OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
                    pst = cn.prepareStatement(sql);
                    pst.setString(1, email);
                    pst.setString(2, datefrom);
                    pst.setString(3, dateto);
                    pst.setInt(4, (index-1)*10);
                    ResultSet rs = pst.executeQuery();
                    while (rs != null && rs.next()) {
                        int orderid = rs.getInt("OrderID");
                        String orderdate = rs.getString("OrdDate");
                        String shipdate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accid = rs.getInt("AccID");
                        Order or = new Order(orderid, orderdate, shipdate, status, accid);
                        list.add(or);
                    }
                } else {
                    String sql = "SELECT * FROM Orders o LEFT JOIN Accounts a ON o.AccID = a.accID \n"
                            + "WHERE a.email = ?\n"
                            + "ORDER BY o.OrderID OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
                    pst = cn.prepareStatement(sql);
                    pst.setString(1, email);
                    pst.setInt(2, (index-1)*10);
                    ResultSet rs = pst.executeQuery();
                    while (rs != null && rs.next()) {
                        int orderid = rs.getInt("OrderID");
                        String orderdate = rs.getString("OrdDate");
                        String shipdate = rs.getString("shipdate");
                        int status = rs.getInt("status");
                        int accid = rs.getInt("AccID");
                        Order or = new Order(orderid, orderdate, shipdate, status, accid);
                        list.add(or);
                    }
                }
            }
            cn.close();
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
        return list;
    }
    
    public static boolean updateOrderStatus(int orderid) {
        try {
            Connection cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Orders SET status = 1 WHERE OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderid);
                pst.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean updateOrderStatusCancel(int orderid) {
        try {
            Connection cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Orders SET status = 3 WHERE OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderid);
                pst.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateOrderStatusComplete(int orderid) {
        try {
            Connection cn = DBUtilss.makeConnection();
            if (cn != null) {
                Date d = new Date(System.currentTimeMillis());
                String sql = "UPDATE Orders SET status = 2, shipdate = ? WHERE OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, orderid);
                pst.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getTotalOrder() {
        try {
            Connection cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "SELECT COUNT(*) FROM Orders";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public static ArrayList<Order> getOrders(String sortby, String order, int index) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql;
                if (sortby.equals("orderid")) {
                    if (order.equals("desc")) {
                        sql = "SELECT * FROM Orders ORDER BY OrderID DESC";
                    } else {
                        sql = "SELECT * FROM Orders ORDER BY OrderID";
                    }
                    sql = sql + " OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
                } else {
                    if (order.equals("desc")) {
                        sql = "SELECT * FROM Orders ORDER BY OrdDate DESC";
                    } else {
                        sql = "SELECT * FROM Orders ORDER BY OrdDate";                        
                    }
                    sql = sql + " OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
                }
                
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, (index-1)*10);
                ResultSet rs = pst.executeQuery();
                while (rs != null && rs.next()){
                    int orderID = rs.getInt("OrderID");
                    String orderDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("shipDate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    Order or = new Order(orderID, orderDate, shipDate, status, accID);
                    list.add(or);
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
        return list;
    }
    
    public static ArrayList<Order> getOrders(int index) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "SELECT OrderID, OrdDate, shipdate, status, AccID FROM Orders"
                        + " ORDER BY OrderID OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, (index-1)*10);
                ResultSet rs = pst.executeQuery();
                while (rs != null && rs.next()){
                    int orderID = rs.getInt("OrderID");
                    String orderDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("shipDate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    Order or = new Order(orderID, orderDate, shipDate, status, accID);
                    list.add(or);
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
        return list;
    }
    
     public static ArrayList<Order> getOrders(String email) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "SELECT o.OrderID, o.OrdDate, o.shipdate, o.status, a.AccID FROM Orders o LEFT JOIN Accounts a\n" +
                             "ON o.accID = a.accID WHERE a.email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                while (rs != null && rs.next()){
                    int orderID = rs.getInt("OrderID");
                    String orderDate = rs.getString("OrdDate");
                    String shipDate = rs.getString("shipDate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("AccID");
                    Order or = new Order(orderID, orderDate, shipDate, status, accID);
                    list.add(or);
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
        return list;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "SELECT DetailId, OrderID, PID, PName, price, imgPath, quantity FROM OrderDetails, Plants WHERE OrderID = ? AND OrderDetails.FID=Plants.PID";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null){
                    while (rs.next()) {
                        int detailID = rs.getInt("DetailId");
                        int PlantID = rs.getInt("PID");
                        String PlantName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        int quantity = rs.getInt("quantity");
                        
                        OrderDetail or = new OrderDetail(detailID, orderID, PlantID, PlantName, price, imgPath, quantity);
                        list.add(or);
                    }
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
        return list;
    }
}
