/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.basic;

import java.util.ArrayList;

/**
 *
 * @author Chingo
 */
public abstract class AnimModel extends Model {

    private ArrayList<Bone> bones;

    public AnimModel(String name, String type, int id) {
        super(name, type, id);
        this.bones = new ArrayList<>();
    }

    public ArrayList<Bone> getBones() {
        return this.bones;
    }

    public void addBone(Bone b) {
        this.bones.add(b);
    }

    public void setBoneXPositionByBoneID(int boneID, double boneXpos) {
        for (Bone b : bones) {
            if (b.getBoneID() == boneID) {
                b.setPosX(boneXpos);
            }
        }
    }

    public void setBoneYPositionByBoneID(int boneID, double boneYpos) {
        for (Bone b : bones) {
            if (b.getBoneID() == boneID) {
                b.setPosY(boneYpos);
            }
        }
    }

    public void setBoneZPositionByBoneID(int boneID, double boneZpos) {
        for (Bone b : bones) {
            if (b.getBoneID() == boneID) {
                b.setPosZ(boneZpos);
            }
        }
    }

    public void setBoneXNormPositionByBoneID(int boneID, double boneXNormpos) {
        for (Bone b : bones) {
            if (b.getBoneID() == boneID) {
                b.setNormX(boneXNormpos);
            }
        }
    }

    public void setBoneYNormPositionByBoneID(int boneID, double boneYNormpos) {
        for (Bone b : bones) {
            if (b.getBoneID() == boneID) {
                b.setNormY(boneYNormpos);
            }
        }
    }

    public void setBoneZNormPositionByBoneID(int boneID, double boneZNormpos) {
        for (Bone b : bones) {
            if (b.getBoneID() == boneID) {
                b.setNormZ(boneZNormpos);
            }
        }
    }

    public Bone getBoneByID(int boneID) {
        Bone bone = null;
        for (Bone b : bones) {
            if (b.getBoneID() == boneID) {
                bone = b;
            }
        }
        return bone;
    }

    public Bone getBoneByName(String boneName) {
        Bone bone = null;
        for (Bone b : bones) {
            if (b.getBoneName().equals(boneName)) {
                return b;
            }
        }
        return bone;
    }

    public void setBones(ArrayList<Bone> bones) {
        this.bones = bones;
    }
}
