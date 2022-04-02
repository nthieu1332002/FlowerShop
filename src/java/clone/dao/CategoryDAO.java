/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clone.dao;

import clone.dto.Category;
import clone.utils.DBUtilss;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nthie
 */
public class CategoryDAO {
    public static ArrayList<Category> getCategory(){
        ArrayList<Category> list = new ArrayList<>();
        try {
            Connection cn = DBUtilss.makeConnection();
            if (cn != null) {
                String sql = "SELECT * FROM Categories";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs != null && rs.next()){
                    int cateid = rs.getInt("cateid");
                    String catename = rs.getString("catename");
                    Category cate = new Category(cateid, catename);
                    list.add(cate);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
