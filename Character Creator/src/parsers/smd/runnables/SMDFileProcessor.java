/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.smd.runnables;

import com.google.common.base.Charsets;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SMD.SMDModel;

/**
 *
 * @author Chingo
 */
public class SMDFileProcessor {

    public SMDModel processFile(File file) {
        SMDModel model = new SMDModel();
        List<String> data = null;
        assert file != null;
        Path filepath = Paths.get(file.getAbsolutePath());
        try {
            data = Files.readAllLines(filepath, Charsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(SMDFileProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        BoneProcessor boneProcessor = new BoneProcessor(data, model);
        TriangleProcessor triangleProcessor = new TriangleProcessor(data, model);
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
        return model;
    }
}
