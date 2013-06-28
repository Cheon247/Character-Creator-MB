package model.SMD;

import model.Bone;
import model.Triangle;
import java.util.ArrayList;


public class SMDModel {

    private int timeframe = -1;
    private int version = -1;
    private String name;
    private ArrayList<Bone> bones;          // TODO Create tree of bones
    private ArrayList<Triangle> triangles;  // TODO Create tree of triangles

    public SMDModel() {
        bones = new ArrayList<>();
        triangles = new ArrayList<>();
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


}
