/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.basic.Model;

/**
 * Handles Modeldata in database
 *
 * @author Chingo
 */
public class ModelDataResource {

    private final String UNDEFINED_GENRE = "Other";
    private final String DB_Driver = "org.sqlite.JDBC";
    private final String DB_CONNECTION = "jdbc:sqlite:models.db";
    private static ModelDataResource instance;
    private ModelManager modelMng;

    public static ModelDataResource getInstance() throws Exception {
        if (instance == null) {
            instance = new ModelDataResource();


        }
        return instance;
    }

    public ModelDataResource() throws Exception {
        modelMng = ModelManager.getInstance();

        Class.forName(DB_Driver);
        Connection conn = DriverManager.getConnection(DB_CONNECTION);
        Statement stat = conn.createStatement();
        stat.executeUpdate("CREATE TABLE IF NOT EXISTS CHARACTER_MODELS (NAME,GENRE,KIND,MODELTYPE,PATH);");
        conn.close();
    }

    private boolean nameExists(String name) throws SQLException {
        boolean nameExists = true;
        Connection conn = DriverManager.getConnection(DB_CONNECTION);
        conn.setAutoCommit(false);

        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("SELECT NAME FROM CHARACTER_MODELS WHERE NAME = '" + name + "';");

        if (rs.next()) {
            while (rs.next()) {
                if (rs.getString("name").equals(name)) {
                    nameExists = true;
                }
            }
        } else {
            nameExists = false;
        }
        conn.close();
        rs.close();
        return nameExists;
    }
    /*
     * Name has to be unique, therefore returns false if wasnt unique, returns true if
     * Name was unique and succesfully added
     */

    public boolean CommitCharacterModel(Model model) throws SQLException {
        if (nameExists(model.getName())) {
            System.out.println("Name Already exists...");
            return false;
        } else {
            // Write Model to file
            modelMng.Store(model);

            String modelName = model.getName();
            String modelType = model.getType();
            String modelGenre = model.getGenre();
            if (model.getGenre() == null) {
                modelGenre = UNDEFINED_GENRE;
            }
            String modelKind = "CHARACTER";
            String modelPath = ModelManager.CHARACTER_MODELS_FILE_PATH + modelGenre + "/" + modelName + "/";
            Connection conn = DriverManager.getConnection("jdbc:sqlite:models.db");
            PreparedStatement prep = conn.prepareStatement("insert into CHARACTER_MODELS values (?,?,?,?,?);");
            prep.setString(1, modelName); // NAME
            prep.setString(2, modelGenre); // GENRE
            prep.setString(3, modelKind); // KIND (CHARACTER,VEHICLE,SCENERY,etc)
            prep.setString(4, modelType); // MODELTYPE
            prep.setString(5, modelPath); // PATH
            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);
            conn.close();
        }
        return true;
    }

    private ArrayList<String> getFilePaths(File folder) {
        ArrayList<String> filePaths = new ArrayList<>();
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                getFilePaths(fileEntry);
            } else {
                filePaths.add(fileEntry.getName());
            }
        }
        return filePaths;
    }
}
