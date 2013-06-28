package model.SMD;

import java.util.ArrayList;

/**
 * 
 *
 */
public class SMDData  {
    // RawData to a more header oriented data

    private String versionInfo;      
    private String timeInfo;
    private ArrayList<ArrayList<String>> nodes;
    private ArrayList<ArrayList<String>> nodepositions;
    private ArrayList<ArrayList<String>> triangles;
    
    public SMDData(){
        this.nodes = new ArrayList<>();
        this.nodepositions = new ArrayList<>();
        this.triangles = new ArrayList<>();
    }
    
    /**
     * 
     * Getters & Setters
     *  
     */
    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public String getTimeInfo() {
        return timeInfo;
    }

    public void setTimeInfo(String timeInfo) {
        this.timeInfo = timeInfo;
    }

    public ArrayList<ArrayList<String>> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<ArrayList<String>> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<ArrayList<String>> getNodepositions() {
        return nodepositions;
    }

    public void setNodepositions(ArrayList<ArrayList<String>> nodepositions) {
        this.nodepositions = nodepositions;
    }

    public ArrayList<ArrayList<String>> getTriangles() {
        return triangles;
    }

    public void setTriangles(ArrayList<ArrayList<String>> triangles) {
        this.triangles = triangles;
    }


   
    
    


}