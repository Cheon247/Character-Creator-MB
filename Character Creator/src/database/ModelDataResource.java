/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import character.creator.FileManager;
import java.sql.*;
import sun.font.CreatedFontTracker;

/**
 *
 * @author Chingo
 */
public class ModelDataResource {

    private static ModelDataResource instance;

    public static ModelDataResource getInstance() throws Exception {
        if (instance == null) {
            instance = new ModelDataResource();

        }
        return instance;
    }

    public ModelDataResource() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:models.db");
        Statement stat = conn.createStatement();
        stat.executeUpdate("create table if not exists models (type,id,name);");
        conn.close();
    }
//        if (rs1.) {
//
//            stat.executeUpdate("drop table if exists Models;");
//            stat.executeUpdate("create table Models (name, type) if !exists Models;");
//        }
////        PreparedStatement prep = conn.prepareStatement(
////                "insert into people values (?, ?);");
////
////        prep.setString(1, "Gandhi");
////        prep.setString(2, "politics");
////        prep.addBatch();
////        prep.setString(1, "Turing");
////        prep.setString(2, "computers");
////        prep.addBatch();
////        prep.setString(1, "Wittgenstein");
////        prep.setString(2, "smartypants");
////        prep.addBatch();
////
////        conn.setAutoCommit(false);
////        prep.executeBatch();
////        conn.setAutoCommit(true);
//
//        ResultSet rs = stat.executeQuery("select * from Models;");
//        while (rs.next()) {
//            System.out.println("name = " + rs.getString("name"));
//            System.out.println("job = " + rs.getString("occupation"));
//        }
//        rs.close();
}
