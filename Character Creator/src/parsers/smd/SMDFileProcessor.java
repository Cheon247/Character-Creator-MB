/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.smd;

import character.creator.FileManager;
import com.google.common.base.Charsets;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.basic.Bone;
import model.smd.SMDModel;

/**
 *
 * @author Chingo
 */
public class SMDFileProcessor implements Runnable {

    private File file;
    private SMDModel model;

    public SMDFileProcessor(File f) {
        this.file = f;

    }

    @Override
    public void run() {

        List<String> data = null;
        assert file != null;
        Path filepath = Paths.get(file.getAbsolutePath());
        try {
            data = Files.readAllLines(filepath, Charsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(SMDFileProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        BoneProcessor boneProcessor = new BoneProcessor(data);
        TriangleProcessor triangleProcessor = new TriangleProcessor(data);
        Thread boneThread = new Thread(boneProcessor);
        Thread triangleThread = new Thread(triangleProcessor);

        boneThread.start();
        triangleThread.start();

        try {
            boneThread.join();
            triangleThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SMDFileProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Need unique id from DB here in " + this.getClass().getName());

        this.model = new SMDModel(FileManager.getInstance().getFileName(file), // Model Name
                FileManager.getInstance().getFileExtension(file), // File Type
                0);                                                       // unique Model id


        model.setBones(boneProcessor.getResult());
        model.setTriangles(triangleProcessor.getResult());



    }

    public SMDModel getProcessedModel() {
        return this.model;
    }
}
