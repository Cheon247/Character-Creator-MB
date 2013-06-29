package parsers.smd.runnables;

import constants.SMDConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.Node;
import model.Bone;
import model.SMD.SMDModel;

public class BoneRunnable implements Runnable {

    private SMDModel model;
    private File file;

    public BoneRunnable(File file, SMDModel model) {
        this.file = file;
        this.model = model;
    }

    @Override
    public void run() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BoneRunnable.class.getName()).log(Level.SEVERE, null, ex);

        }

        if (scanner != null) {


            while (scanner.hasNextLine()) {
                boolean finished = false;

                if (scanner.nextLine().split("\\s")[0].equals(SMDConstants.SMD_NODES_HEADER)) {
                    String s = scanner.nextLine();

                    while (!s.startsWith("end")) {
                        Bone bone = new Bone();
                        String[] b = s.split("\\s");

                        bone.setBoneID(Integer.parseInt(b[0].trim()));
                        bone.setBoneName(b[1]);
                        bone.setParentBoneID(Integer.parseInt(b[2]));

                        model.addBone(bone);
//                        System.out.println(bone.getBoneID() + " " + bone.getBoneName() + " " + bone.getParentBoneID());

                        s = scanner.nextLine();
                    }
                } else if (model.getBones().size() > 0) { // if there weren't any bones no need to add positions
                    if (scanner.nextLine().split("\\s")[0].equals(SMDConstants.SMD_BONE_POSITIONS_HEADER)) {
                        String s = scanner.nextLine();
                        while (!s.startsWith("end")) {
                            String[] b = s.split("\\s");
                            int boneid = Integer.parseInt(b[0]);

                            // bone positions
                            Double boneXpos = Double.parseDouble(b[1]);
                            Double boneYpos = Double.parseDouble(b[2]);
                            Double boneZpos = Double.parseDouble(b[3]);

//                            System.out.println("X:" + boneXpos + "\tY:" + boneYpos + "\tZ:" + boneZpos);

                            // bone normals - positions
                            Double boneXNormpos = Double.parseDouble(b[4]);
                            Double boneYNormpos = Double.parseDouble(b[5]);
                            Double boneZNormpos = Double.parseDouble(b[6]);

//                            System.out.println("NX:" + boneXNormpos + "\tNY:" + boneYNormpos + "\tNZ:" + boneZpos);

                            model.setBoneXPositionByBoneID(boneid, boneXpos);
                            model.setBoneYPositionByBoneID(boneid, boneYpos);
                            model.setBoneZPositionByBoneID(boneid, boneZpos);
                            model.setBoneXNormPositionByBoneID(boneid, boneXNormpos);
                            model.setBoneYNormPositionByBoneID(boneid, boneYNormpos);
                            model.setBoneZNormPositionByBoneID(boneid, boneZNormpos);


                            s = scanner.nextLine();
                        }
                        finished = true; // no need to go to end of file
                    }


                }
                if (finished) {
                    break;
                }

            }


            scanner.close();
        }





    }
}
