/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.smd;

import constants.SMDConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bone;
import model.SMD.SMDModel;
import model.SMD.SMDNumberFormat;
import model.Triangle;

/**
 *
 * @author Chingo
 */
public class SMDWriter {

    private SMDModel model;
    private PrintWriter smdWriter;

    public SMDWriter() {
    }

    public void writeModel(SMDModel model) {
        this.model = model;

        File exportFolder = new File("Export");
        exportFolder.mkdir();

        File exportSMDFolder = new File("Export/SMD");
        exportSMDFolder.mkdir();

        File file = new File("Export/SMD/LAST_EXPORTED_SMD.smd");
        try {
            smdWriter = new PrintWriter(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SMDWriter.class.getName()).log(Level.SEVERE, null, ex);
        }


        writeVersion();
        writeNodes();
        writeSkeleton();
        writeTimeFrame();
        writeBonePositions();
        writeTriangles();
        smdWriter.close();

    }

    private void writeVersion() {
        smdWriter.println(SMDConstants.SMD_VERSION_HEADER + " " + model.getVersion());
    }

    private void writeNodes() {
        smdWriter.println(SMDConstants.SMD_NODES_HEADER);
        for (Bone b : model.getBones()) {
            smdWriter.println(b.getBoneID() + " " + b.getBoneName() + " " + b.getParentBoneID());
        }
        smdWriter.println(SMDConstants.SMD_END_HEADER);
    }

    private void writeSkeleton() {
        smdWriter.println(SMDConstants.SMD_SKELETON_HEADER);
    }

    private void writeTimeFrame() {
        smdWriter.println(SMDConstants.SMD_BONE_POSITIONS_HEADER + " " + model.getTimeframe());
    }

    private void writeBonePositions() {
        for (Bone b : model.getBones()) {
            smdWriter.print(b.getBoneID() + " ");
            smdWriter.print(SMDNumberFormat.format(b.getPosX()) + " " + SMDNumberFormat.format(b.getPosY()) + " " + SMDNumberFormat.format(b.getPosZ()) + " ");
            smdWriter.print(SMDNumberFormat.format(b.getNormX()) + " " + SMDNumberFormat.format(b.getNormY()) + " " + SMDNumberFormat.format(b.getNormZ()) + "\n");
        }
        smdWriter.println(SMDConstants.SMD_END_HEADER);
    }

    private void writeTriangles() {
        smdWriter.println(SMDConstants.SMD_TRIANGLES_HEADER);
        for (Triangle t : model.getTriangles()) {
            smdWriter.println(t.toString());
        }
        smdWriter.println(SMDConstants.SMD_END_HEADER);
    }
}
