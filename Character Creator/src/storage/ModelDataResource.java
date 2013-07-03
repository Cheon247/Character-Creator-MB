/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import java.sql.*;
import model.basic.Model;

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

    public void addModel(Model model, String name, String genre) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:models.db");

        conn.close();
    }
}
