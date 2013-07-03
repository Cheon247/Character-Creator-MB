package model.basic;

import java.io.Serializable;
import java.util.ArrayList;
import model.smd.SMDNumberFormat;

public class Vertex implements Serializable {

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

    public Vertex() {
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

    public boolean isSameAs(Vertex v) {
        boolean isSame = false;
        if (this.getBoneID() == v.getBoneID() && this.getPosX() == v.getPosX()
                && this.getPosY() == v.getPosY() && this.getPosZ() == this.getPosZ()) {
            isSame = true;
        }
        return isSame;
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
