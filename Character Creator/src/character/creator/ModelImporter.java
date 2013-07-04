package character.creator;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import parsers.smd.SMDParser;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.basic.Bone;
import model.basic.Model;
import model.smd.SMDModel;
import storage.ModelDataResource;
import writers.SMDBoneWriter;
import writers.SMDWriter;

public class ModelImporter {

    private JFileChooser chooser;
    private static ModelImporter instance;
    private final String FILE_NOT_FOUND = "file not found...";

    public Model loadFile() {
        File file = setSelectedTextFile();
        Model model = null;
        if (file != null) {
            String fileExtension = FileManager.getInstance().getFileExtension(file);

            switch (fileExtension) {
                case "smd":
                    model = parseFiletoSMD(file);

                    break;
                // ROOM FOR OTHER EXTENSIONS TO SUPPORT
                default:
                    System.err.println("File not supported");
                    break; // Shouldnt happen
            }
        }

        // returns null when no file was chosen
        return model;
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

    private SMDModel parseFiletoSMD(File file) {
        SMDParser smdParser = new SMDParser();
        SMDModel model = null;

        long startTime = System.nanoTime() / 1000000;
        try {
            model = smdParser.parseToSMDData(file);
        } catch (InterruptedException ex) {
            Logger.getLogger(ModelImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        long endTime = System.nanoTime() / 1000000;
        System.out.println("SMD ~ Parsed in: " + (endTime - startTime) + "ms, Triangles: " + model.getTriangles().size());
        return model;
    }

    private void writeBoneToFile(SMDModel model, Bone b) {
        SMDBoneWriter smdBonewriter = new SMDBoneWriter();
        smdBonewriter.writeModel(model, b);
    }

    private void parseSMDtoFile(SMDModel model) {
        assert model != null : "null model";
        SMDWriter smdWriter = new SMDWriter();

        long startTime = System.nanoTime() / 1000000;
        smdWriter.writeModel(model);
        long endTime = System.nanoTime() / 1000000;
        System.out.println("SMD ~ Written in: " + (endTime - startTime) + "ms");
    }
}
