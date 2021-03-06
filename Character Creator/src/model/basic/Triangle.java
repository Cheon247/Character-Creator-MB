package model.basic;

import java.io.Serializable;
import java.util.ArrayList;

public class Triangle implements Serializable {

    private String texture;
    private int id;
    private ArrayList<Vertex> vertices;

    public Triangle(int id) {
        this.id = id;
        this.vertices = new ArrayList<>();
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> children) {
        this.vertices = children;
    }

    public boolean containsVertex(Vertex v) {
        return vertices.contains(v);
    }

    public Vertex getVertex(int index) {
        return vertices.get(index);
    }

    public boolean addVertex(Vertex e) {
        return vertices.add(e);
    }

    public ArrayList<Vertex> removeVertex(Vertex v) {
        this.vertices.remove(v);
        return this.vertices;
    }

    public int verticesSize() {
        return this.vertices.size();
    }

    @Override
    public String toString() {
        String triangleString = "";
        triangleString += this.texture;
        for (Vertex v : vertices) {
            triangleString += "\n" + v.toString();
        }
        return triangleString;
    }
}
