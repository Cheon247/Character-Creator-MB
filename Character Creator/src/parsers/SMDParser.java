package parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;


import model.SMD.SMDData;

public class SMDParser {

        /**
         * Adds extra information to the data
         *      
         * @param file
         * @return
         * @throws FileNotFoundException 
         */
	private SMDData addKnowledge(File file) throws FileNotFoundException{
		SMDData smdData = new SMDData();
		Scanner lineScanner = new Scanner(file);
		if (lineScanner!= null) {
			int index = 0;
			int endCounter = 0;
			while (lineScanner.hasNextLine()) {
				String line = lineScanner.nextLine();
				ArrayList<String> theLine = new ArrayList<>();

				for (String s : line.split("\\s")){
					if(s.equals(line.split("\\s")[0])){ // Only compare first word
						switch (s){
						
                                                    
						}
					}
					theLine.add(s);
				}
//				smdData.add(theLine);
				index++;
			}
		}
		lineScanner.close();
		return smdData;
	}

	private void setEnd(SMDData smdData, int endCounter, int index){
		switch(endCounter){
//		case 0: smdData.setNodesEnd(index); break;
//		case 1: smdData.setJointsEnd(index); break;
//		case 2: smdData.setTrianglesEndLine(index); break;
		}
	}

//        public SMDModel parseToSMDData(File f){
//                // Create new SMDData
//		SMDData data = new SMDData();
//		try {
//			data = addKnowledge(f);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		          FileManager.getInstance().logData(data, "SMD_IMPORT.txt");
//           
//		// Processes data multithreaded
//		SMDParserThreadManager smdMng = new SMDParserThreadManager(data);
//		Thread smdMngThread = new Thread(smdMng);
//		smdMngThread.start();
//		try {
//			smdMngThread.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		SMDModel model = smdMng.getProcessedData();
//		
//                // Setting model version
//                model.setName(f.getName());
//		
//		return model;
//	}

        
        public String format(Number n) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setMinimumFractionDigits(6);
        format.setMaximumFractionDigits(6);
        String s = format.format(n);
        
        return s.replaceAll(",", ".");
    }



}
