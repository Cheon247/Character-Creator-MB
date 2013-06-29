package character.creator;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import parsers.smd.SMDParser;

import constants.SMDConstants;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SMD.SMDModel;

public class ModelImporter {

    private JFileChooser chooser;
    private static ModelImporter instance;

    public void loadFile() {
        File file = setSelectedTextFile();
        if (file == null) {
            System.out.println(SMDConstants.FILE_NOT_FOUND);
        } else {
            String fileExtension = FileManager.getInstance().getFileExtension(file);
            switch (fileExtension) {
                case "smd":
                    parseFiletoSMD(file);
                    break;
                // ROOM FOR OTHER EXTENSIONS TO SUPPORT
                default:
                    System.err.println("File not supported");
                    break; // Shouldnt happen
            }
        }
    }

    public static ModelImporter getInstance() {
        if (instance == null) {
            instance = new ModelImporter();
        }
        return instance;
    }

    private File setSelectedTextFile() {
        do {
            chooser = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Supported Formats: *.smd", "smd");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                return chooser.getSelectedFile();

            } else {
                return null;
            }
        } while (true);
    }

    private void parseFiletoSMD(File file) {
        SMDParser smdParser = new SMDParser();
        try {
            SMDModel model = smdParser.parseToSMDData(file);

            //             parseSMDtoFile(model);
            //             parseSMDtoFile(model);
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//
//	private void parseSMDtoFile(SMDModel model){
//            SMDParser smdParser = new SMDParser();
//            smdParser.parseToFile(model);
//            // Do something with file
//	}
}
