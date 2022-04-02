/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clone.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author nthie
 */
public class DBUtilss {
    public static Connection makeConnection() throws Exception{
        Connection cn = null;
        String IP = "localhost";
        String instanceName = "MSI\\HIEUNGUYEN";
        String port = "1433";
        String uid = "sa";
        String pwd = "nthieu1332002";
        String db = "CloneWeb";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://" +IP+"\\"+ instanceName+":"+port
                +";databasename="+db+";user="+uid+";password="+pwd;
        Class.forName(driver);
        cn = DriverManager.getConnection(url);
        return cn;
    }
}
