package character.creator;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import parsers.smd.SMDParser;

import constants.SMDConstants;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SMD.SMDModel;
import parsers.smd.SMDWriter;

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
        System.out.println("REMINDER: remove auto export in " + this.getClass().getName());

        SMDParser smdParser = new SMDParser();
        SMDModel model = null;

        long startTime = System.nanoTime() / 1000000;
        model = smdParser.parseToSMDData(file);
        long endTime = System.nanoTime() / 1000000;
        System.out.println("Parsed in: " + (endTime - startTime) + "ms");

        parseSMDtoFile(model);
    }
//

    private void parseSMDtoFile(SMDModel model) {
        assert model != null : "null model";
        SMDWriter smdWriter = new SMDWriter();

        long startTime = System.nanoTime() / 1000000;
        smdWriter.writeModel(model);
        long endTime = System.nanoTime() / 1000000;
        System.out.println("Written in: " + (endTime - startTime) + "ms");
    }
}
