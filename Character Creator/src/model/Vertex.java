package model;

import java.util.ArrayList;
import model.SMD.SMDNumberFormat;

public class Vertex {

    private int boneID = -1;
    private int id;
    private Double posX;
    private Double posY;
    private Double posZ;
    private Double normX;
    private Double normY;
    private Double normZ;
    private Double normUV;
    private Double normWeight;
    private ArrayList<Integer> triangleIDs; 	// one vertex could be in multiple triangles
    private ArrayList<Vertex> neighbours; 		// if vertexes share a triangle they are neighbours

    public Vertex() {

        triangleIDs = new ArrayList<>();
        neighbours = new ArrayList<>();
    }

    public Double getNormUV() {
        return normUV;
    }

    public void setNormUV(Double normUV) {
        this.normUV = normUV;
    }

    public Double getNormWeight() {
        return normWeight;
    }

    public void setNormWeight(Double normWeight) {
        this.normWeight = normWeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private boolean isNeigbourVertex(Vertex v) {
        boolean isNeighbourVertex = false;
        for (Integer i : triangleIDs) {
            if (v.getTriangleIDs().contains(i)) {
                isNeighbourVertex = true;
            }
        }
        return isNeighbourVertex;
    }

    public boolean isTwin(Vertex v) {
        boolean isTwin = false;
        if (hasSameXPos(v) && hasSameYPos(v) && hasSameZPos(v)) {
            isTwin = true;
        }
        return isTwin;
    }

    private boolean hasSameXPos(Vertex v) {
        boolean sameXPosition = false;
        if (this.posX == v.posX) {
            sameXPosition = true;
        }
        return sameXPosition;
    }

    private boolean hasSameYPos(Vertex v) {
        boolean sameYPosition = false;
        if (this.posY == v.posY) {
            sameYPosition = true;
        }
        return sameYPosition;
    }

    private boolean hasSameZPos(Vertex v) {
        boolean sameZPosition = false;
        if (this.posZ == v.posZ) {
            sameZPosition = true;
        }
        return sameZPosition;
    }

    public int getBoneID() {
        return boneID;
    }

    public void setBoneID(int boneID) {
        this.boneID = boneID;
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

    /**
     * @return the triangleIDs
     */
    public ArrayList<Integer> getTriangleIDs() {
        return triangleIDs;
    }

    /**
     * @param triangleIDs the triangleIDs to set
     */
    public void setTriangleIDs(ArrayList<Integer> triangleIDs) {
        this.triangleIDs = triangleIDs;
    }

    public void addParent(int triangleID) {
        triangleIDs.add(triangleID);
    }

    public boolean isNeighBour(Vertex v) {
        boolean isNeighbour = false;
        for (Integer i : v.getTriangleIDs()) {
            for (Integer j : v.getTriangleIDs()) {
                if (i == j) {
                    isNeighbour = true;
                    break;
                }
            }
        }
        return isNeighbour;
    }

    /**
     * @param arg0
     *
     * @see java.util.ArrayList#add(int, java.lang.Object)
     */
    public void addNeighBour(Vertex v) {
        neighbours.add(v);
    }

    /**
     * @param arg0
     * @return
     * @see java.util.ArrayList#contains(java.lang.Object)
     */
    public boolean contains(Vertex v) {
        return neighbours.contains(v);
    }

    public ArrayList<Vertex> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Vertex> neighbours) {
        this.neighbours = neighbours;
    }

    @Override
    public String toString() {
        String vertexString = "";
        vertexString += this.boneID + " ";
        vertexString += SMDNumberFormat.format(this.posX) + " ";
        vertexString += SMDNumberFormat.format(this.posY) + " ";
        vertexString += SMDNumberFormat.format(this.posZ) + " ";
        vertexString += SMDNumberFormat.format(this.normX) + " ";
        vertexString += SMDNumberFormat.format(this.normY) + " ";
        vertexString += SMDNumberFormat.format(this.normZ) + " ";
        vertexString += SMDNumberFormat.format(this.normUV) + " ";
        vertexString += SMDNumberFormat.format(this.normWeight) + " ";

        return vertexString;
    }
}
