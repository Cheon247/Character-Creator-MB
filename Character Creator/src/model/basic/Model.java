/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chingo
 */
public abstract class Model {

    private String name;
    private String type;
    private int id;
    private ArrayList<Triangle> triangles;

    public Model(String theName, String theType, int id) {
        this.triangles = new ArrayList<>();
        this.name = theName;
        this.type = theType;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addTriangle(Triangle t) {
        this.triangles.add(t);
    }

    public ArrayList<Triangle> getTriangles() {
        return this.triangles;
    }

    public void setTriangles(ArrayList<Triangle> triangles) {
        this.triangles = triangles;
    }

    public int getNumberOfVertices() {

        ArrayList<Vertex> myvertices = new ArrayList<>();
        for (Triangle t : this.getTriangles()) {
            for (Vertex v : t.getVertices()) {
                if (!hasVertex(myvertices, v)) {
                    myvertices.add(v);
                }
            }
        }

        return myvertices.size();
    }

    public boolean hasVertex(ArrayList<Vertex> vertices, Vertex v1) {
        boolean hasVertex = false;
        for (Vertex v2 : vertices) {
            if (v1.isSameAs(v2)) {
                hasVertex = true;
                break;

            }
        }
        return hasVertex;


    }

    public void logTriangles() {
        File file = new File("log/triangles.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Triangle t : triangles) {
            writer.println(t.getVertices().size());
        }
        writer.close();
    }
}
