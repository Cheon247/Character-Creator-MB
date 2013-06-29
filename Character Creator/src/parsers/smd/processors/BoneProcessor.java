package parsers.smd.processors;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.Bone;
import model.SMD.SMDModel;

/**
 * Should be Faster than old method
 *
 * @author Chingo
 */
public class BoneProcessor implements Runnable {

    private File file;
    private List<String> boneList;
    private List<String> bonePositionsList;
    private ArrayList<Bone> result;

    public BoneProcessor(List<String> data) {
        this.boneList = new ArrayList<>(data);
        this.bonePositionsList = new ArrayList<>(data);

        this.boneList = this.boneList.subList(this.boneList.indexOf("nodes") + 1, this.boneList.indexOf("end"));

        this.bonePositionsList.remove(this.bonePositionsList.indexOf("end"));
        this.bonePositionsList = this.bonePositionsList.subList(this.bonePositionsList.indexOf("skeleton") + 2, this.bonePositionsList.indexOf("end"));
        result = new ArrayList<>();
    }

    @Override
    public void run() {
        getBones();
    }

    private void getBones() {
        for (String s : this.boneList) {
            Bone bone = new Bone();
            String[] b = s.split("\\s");

            bone.setBoneID(Integer.parseInt(b[0].trim()));
            bone.setBoneName(b[1]);
            bone.setParentBoneID(Integer.parseInt(b[2]));

            result.add(bone);
        }
        getBonePositions();
    }

    private void getBonePositions() {
        for (String s : this.bonePositionsList) {
            String[] b = s.split("\\s");
            int boneid = Integer.parseInt(b[0]);

            Double boneXpos = Double.parseDouble(b[1]);
            Double boneYpos = Double.parseDouble(b[2]);
            Double boneZpos = Double.parseDouble(b[3]);

            Double boneXNormpos = Double.parseDouble(b[4]);
            Double boneYNormpos = Double.parseDouble(b[5]);
            Double boneZNormpos = Double.parseDouble(b[6]);

            for (Bone bone : result) {
                if (bone.getBoneID() == boneid) {
                    bone.setPosX(boneXpos);
                    bone.setPosY(boneYpos);
                    bone.setPosZ(boneZpos);

                    bone.setNormX(boneXNormpos);
                    bone.setNormY(boneYNormpos);
                    bone.setNormZ(boneZNormpos);
                }
            }

        }
    }

    public ArrayList<Bone> getResult() {
        return this.result;
    }
}
