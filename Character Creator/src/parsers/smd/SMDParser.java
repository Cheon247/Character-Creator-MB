package parsers.smd;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import model.basic.Bone;
import model.smd.SMDModel;

public class SMDParser {

    public SMDModel parseToSMDData(File f) throws InterruptedException {
        SMDModel model;
        SMDFileProcessor processor = new SMDFileProcessor(f);
        Thread processorThread = new Thread(processor);
        processorThread.start();
        processorThread.join();
        model = processor.getProcessedModel();

        for (Bone b : model.getBones()) {
            BoneBinderProcessor bonebin = new BoneBinderProcessor(model.getTriangles(), b);
            Thread boneThread = new Thread(bonebin);
            boneThread.start();
            boneThread.join();

            b.setVertices(bonebin.getResult());
        }



        return model;
    }
}
