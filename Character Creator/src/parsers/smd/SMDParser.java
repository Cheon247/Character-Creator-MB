package parsers.smd;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import model.Bone;
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


//        for (Bone b : model.getBones()) {
//            System.out.println(b.getBoneID() + "\t" + format(b.getPosX()) + "\t" + format(b.getPosY()) + "\t" + format(b.getPosZ()) + "\t" + format(b.getNormX()) + "\t" + format(b.getNormY()) + "\t" + format(b.getNormZ()));
//        }

        return model;
    }
}
