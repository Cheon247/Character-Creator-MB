package model.SMD;

import model.Bone;
import model.Triangle;
import java.util.ArrayList;

public class SMDModel {

    private int timeframe = -1;
    private int version = -1;
    private String name;
    private ArrayList<Bone> bones;
    private ArrayList<Triangle> triangles;

    public SMDModel() {
        bones = new ArrayList<>();
        triangles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTriangle(Triangle t) {
        this.triangles.add(t);
    }

    public ArrayList<Triangle> getTriangles() {
        return this.triangles;
    }

    public int getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(int timeframe) {
        this.timeframe = timeframe;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public void setTriangles(ArrayList<Triangle> triangles) {
        this.triangles = triangles;
    }
}
