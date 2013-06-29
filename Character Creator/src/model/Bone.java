package model;

import java.util.TreeMap;

public class Bone {

    private int boneID;
    private String boneName;
    private int parentBoneID;
    // Starting positions
    private double posX;
    private double posY;
    private double posZ;
    private double normX;
    private double normY;
    private double normZ;
//    private ArrayList<Vertex> vertices; // Make tree of bones
    private TreeMap vertices;

    public Bone() {
        vertices = new TreeMap();
    }

    public void setVertices(TreeMap vertices) {
        this.vertices = vertices;
    }

    public void putAll(TreeMap m) {
        vertices.putAll(m);
    }

    public TreeMap getVertices() {
        return this.vertices;
    }

    public int SizeOfBones() {
        return vertices.size();
    }

    public void putVertex(Vertex v) {
        vertices.put(v.getId(), v);
    }

    public void getVertex(Vertex v) {
        vertices.get(v.getId());
    }

    public void removeVertex(Vertex v) {
        vertices.remove(v.getId());
    }

    public boolean hasVertexWithID(int vertexid) {
        boolean hasVertex = false;
        if (vertices.containsKey(vertexid)) {
            hasVertex = true;
        }
        return hasVertex;
    }

    public int getBoneID() {
        return boneID;
    }

    public void setBoneID(int boneID) {
        this.boneID = boneID;
    }

    public String getBoneName() {
        return boneName;
    }

    public void setBoneName(String boneName) {
        this.boneName = boneName;
    }

    public int getParentBoneID() {
        return parentBoneID;
    }

    public void setParentBoneID(int parentBoneID) {
        this.parentBoneID = parentBoneID;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getPosZ() {
        return posZ;
    }

    public void setPosZ(double posZ) {
        this.posZ = posZ;
    }

    public double getNormX() {
        return normX;
    }

    public void setNormX(double normX) {
        this.normX = normX;
    }

    public double getNormY() {
        return normY;
    }

    public void setNormY(double normY) {
        this.normY = normY;
    }

    public double getNormZ() {
        return normZ;
    }

    public void setNormZ(double normZ) {
        this.normZ = normZ;
    }

    public String toString() {
        return "BoneID: " + boneID + "\tboneName:" + boneName + "\tParentBoneID:" + parentBoneID;
    }
}
