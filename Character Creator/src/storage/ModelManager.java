/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.basic.Model;

/**
 * Stores the model on the System
 *
 * @author Chingo
 */
public class ModelManager {

    protected final static String CHARACTER_MODELS_FILE_PATH = "storage/characters/models/";
    protected final static String CHARACTER_MODEL_FILE_EXTENSION = ".CCharModel";
    private static ModelManager instance;
    private final File modelFolder = new File(CHARACTER_MODELS_FILE_PATH);
    private List<String> filePaths;

    protected ModelManager() {
        filePaths = new ArrayList<>();
        modelFolder.mkdirs();
        getFilePaths(modelFolder);
    }

    protected static ModelManager getInstance() {
        if (instance == null) {
            instance = new ModelManager();

        }
        return instance;
    }

    protected void getFilePaths(File folder) {
        filePaths = new ArrayList<>();
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                getFilePaths(fileEntry);
            } else {
                filePaths.add(fileEntry.getName());
            }
        }
    }

    protected void Store(Model model) {
        // Prepare file destination
        File file = new File(getModelPath(model) + model.getName() + CHARACTER_MODEL_FILE_EXTENSION);
        File exportFolder = new File(getModelPath(model));
        exportFolder.mkdirs();

        // Write Object to destination
        FileOutputStream fos = null;
        ObjectOutputStream objos = null;
        try {
            fos = new FileOutputStream(file);
            objos = new ObjectOutputStream(fos);
            objos.writeObject(model);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModelManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
                objos.close();
            } catch (IOException ex) {
                Logger.getLogger(ModelManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void Remove(Model model) {
        String modelPath = getModelPath(model) + model.getName();
        File file = new File(modelPath);
        if (file.exists()) {
            file.delete();
            filePaths.remove(modelPath);
        }
    }

    private String getModelPath(Model model) {
        String modelName = model.getName() + "/";
        String modelGenre;
        if (model.getGenre() == null) {
            modelGenre = "Other/";
        } else {
            modelGenre = model.getGenre();
        }
        String modelPath = CHARACTER_MODELS_FILE_PATH + modelGenre + "/" + modelName + "/";
        return modelPath;
    }

    /*
     * returns all modelFilePaths
     */
    public List<String> getModels() {
        return this.filePaths;
    }

    public ArrayList<String> getAllCharacters() {
        File folder = new File(CHARACTER_MODELS_FILE_PATH);
        ArrayList<String> characters = new ArrayList<>();
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                getFilePaths(fileEntry);
            } else {
                characters.add(fileEntry.getName());
            }
        }
        return characters;
    }
}
