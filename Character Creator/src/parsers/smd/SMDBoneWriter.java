/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers.smd;

import constants.SMDConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bone;
import model.SMD.SMDModel;
import model.SMD.SMDNumberFormat;
import model.Triangle;
import model.Vertex;

/**
 *
 * @author Chingo
 */
public class SMDBoneWriter {
    
    private SMDModel model;
    private Bone bone;
    private PrintWriter smdWriter;
    
    public SMDBoneWriter() {
    }
    
    public void writeModel(SMDModel model, Bone b) {
        this.bone = b;
        this.model = model;
        
        File exportFolder = new File("Export");
        exportFolder.mkdir();
        
        File exportSMDFolder = new File("Export/SMD");
        exportSMDFolder.mkdir();
        
        File file = new File("Export/SMD/LAST_EXPORTED_SMDBone.smd");
        try {
            smdWriter = new PrintWriter(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SMDBoneWriter.class.getName()).log(Level.SEVERE, null, ex);
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
            Triangle temp = new Triangle(t.getId());
            temp.setTexture(t.getTexture());
            for (Vertex v : t.getVertices()) {
                if (v.getBoneID() == bone.getBoneID()) {
                    temp.addVertex(v);
                }
            }
            if (temp.getVertices().size() > 1) {
                smdWriter.write(temp.toString());
            }
            
        }
        
        
        smdWriter.println(SMDConstants.SMD_END_HEADER);
    }
}
