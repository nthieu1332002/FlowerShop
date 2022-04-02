/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clone.dao;

import clone.dto.Plant;
import clone.utils.DBUtilss;
import java.sql.Connection;
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
public class PlantDAO {
    public static ArrayList<Plant> getPlants(String txtSearch, String searchby, int index) {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtilss.makeConnection();
            if (cn != null && searchby != null) {
                String sql = "SELECT PID, PName, price, imgPath, description, status, Plants.CateID AS 'CateID', CateName\n" +
                            "FROM Plants JOIN Categories ON Plants.CateID=Categories.CateID";
                if (searchby.equals("byname")) {
                    sql = sql + " WHERE PName LIKE ? ORDER BY PID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
                } else {
                    sql = sql + " WHERE CateName LIKE ? ORDER BY PID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%"+txtSearch+"%");
                pst.setInt(2, (index-1)*6);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid);
                        list.add(plant);
                    }
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int getTotalPlant() {
        try {
            Connection cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "SELECT COUNT(*) FROM Plants";
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
    
    public static Plant getPlant(int proid) {
        Plant plant = null;
        Connection cn = null;
        try {
            cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "SELECT PID, PName, price, imgPath, description, status, Plants.CateID AS 'CateID'\n"
                    + "FROM Plants JOIN Categories ON Plants.CateID=Categories.CateID WHERE PID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, proid);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    //int itemid = rs.getInt("ItemId");
                    String itemname = rs.getString("PName");
                    int price = rs.getInt("price");
                    String image = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt("CateID");
                    plant = new Plant(proid, itemname, price, image, description, status, cateid);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plant;
    }
    
    public static boolean updatePlantStatus(int status, int pid) {
        try {
            Connection cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "UPDATE Plants SET status = ? WHERE PID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, pid);
                pst.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static ArrayList<Plant> getPlants(String keyword, int index) {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtilss.makeConnection();
            System.out.println(keyword + index);
            if (cn != null) {
                String sql = "SELECT * FROM Plants WHERE PName LIKE ? ORDER BY PID OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%"+keyword+"%");
                pst.setInt(2, (index-1)*10);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid);
                        list.add(plant);
                    }
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean insertPlant(String name, int price, String image, String description, int status, int cateid) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtilss.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);
                String sql = "INSERT INTO Plants VALUES(?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setInt(2, price);
                pst.setString(3, image);
                pst.setString(4, description);
                pst.setInt(5, status);
                pst.setInt(6, cateid);
                pst.executeUpdate();
                cn.commit();
                cn.setAutoCommit(true);
                return true;
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
}
