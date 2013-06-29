package parsers.smd;

import character.creator.FileManager;
import java.io.File;
import model.SMD.SMDModel;
import parsers.smd.runnables.BoneRunnable;
import parsers.smd.runnables.TriangleRunnable;

public class SMDParser {

    public SMDModel parseToSMDData(File f) throws InterruptedException {
        SMDModel model = new SMDModel();



        BoneRunnable bnr = new BoneRunnable(f, model);
        TriangleRunnable tnr = new TriangleRunnable(f, model);



        // Define threads
        Thread bnt = new Thread(bnr);
        Thread tnt = new Thread(tnr);


        // Start Threads
        bnt.start();
        tnt.start();

        // Join threads
        bnt.join();
        tnt.join();

        /*
         * All data has been imported
         */


        return model;
    }
}
