package model;

import java.util.ArrayList;

public class Triangle {
	private String texture;
	private int id;
	private ArrayList <Vertex> children;
	private ArrayList <Triangle> neighbours;

	public Triangle (int id){
		this.id = id;
		this.children = new ArrayList<>();
                this.neighbours  = new ArrayList<>();
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

    public ArrayList<Vertex> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Vertex> children) {
        this.children = children;
    }

    public ArrayList<Triangle> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Triangle> neighbours) {
        this.neighbours = neighbours;
    }

    public boolean containsVertex(Vertex v) {
        return children.contains(v);
    }

    public Vertex getVertex(int index) {
        return children.get(index);
    }

    public boolean addVertex(Vertex e) {
        return children.add(e);
    }
    
    public int childrenSize(){
        return this.children.size();
    }

    public int neighbourSize() {
        return neighbours.size();
    }

    public boolean containsNeighbour(Triangle t) {
        return neighbours.contains(t);
    }

    public Triangle getNeighbour(int index) {
        return neighbours.get(index);
    }

    public Triangle setNeighbour(int index, Triangle element) {
        return neighbours.set(index, element);
    }

    public boolean addNeighbour(Triangle e) {
        return neighbours.add(e);
    }
    
    public boolean isParentOf(ArrayList<Integer> ids){
    boolean isChild = false;
    
      isChild = true;
    
    return isChild;
    }
    
        
        
        
        
        
}
	
	