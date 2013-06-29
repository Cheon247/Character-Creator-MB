package parsers.smd;

import java.io.File;
import model.SMD.SMDModel;
import parsers.smd.runnables.SMDFileProcessor;

public class SMDParser {

    public SMDModel parseToSMDData(File f) {
        SMDModel model;
        SMDFileProcessor processor = new SMDFileProcessor();
        model = processor.processFile(f);

        return model;
    }
}
