package importer;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import parsers.SMDParser;

import constants.SMDConstants;
import character.creator.FileManager;

public class ModelImporter {

	private JFileChooser chooser;
	private static ModelImporter instance;
	

	public void loadFile(){
		File file = setSelectedTextFile();
		if (file == null){
			System.out.println(SMDConstants.FILE_NOT_FOUND);
		} else { 
                    String fileExtension = FileManager.getInstance().getFileExtension(file);
                    switch (fileExtension) {
                        case "smd": parseFiletoSMD(file); break;
                        // ROOM FOR OTHER EXTENSIONS TO SUPPORT
                        default: System.err.println("File not supported"); break; // Shouldnt happen 
                    }
		}
	}

        public static ModelImporter getInstance() {
		if (instance==null) {
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
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				return chooser.getSelectedFile();

			} else {
				return null;
			}
		} while (true);
	}
	
	private void parseFiletoSMD(File file){
            SMDParser smdParser = new SMDParser();
//            SMDModel model = smdParser.parseToSMDData(file);
//            parseSMDtoFile(model);
            // Do something with retrieved model
	}
//	
//	private void parseSMDtoFile(SMDModel model){
//            SMDParser smdParser = new SMDParser();
//            smdParser.parseToFile(model);
//            // Do something with file
//	}
	
}
