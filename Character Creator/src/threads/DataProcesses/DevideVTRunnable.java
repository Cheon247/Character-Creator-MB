package threads.DataProcesses;

import java.util.ArrayList;

import model.Triangle;
import model.Vertex;

public class DevideVTRunnable implements Runnable {

    private int MAX_THREADS = 250 / Runtime.getRuntime().availableProcessors();
    private ArrayList<Triangle> triangles;
    private ArrayList<Vertex> vertices;
    private ArrayList<Triangle> result;
    private int leafThreadIndicator = 0;

    public DevideVTRunnable(ArrayList<Triangle> triangles, ArrayList<Vertex> vertices) {
        this.triangles = triangles;
        this.vertices = vertices;
        result = new ArrayList<>();
    }

    public void run() {
        if (Thread.activeCount() < MAX_THREADS) {

            ArrayList<Triangle> ls1 = new ArrayList<>(triangles.subList(0, triangles.size() / 2));
            ArrayList<Triangle> ls2 = new ArrayList<>(triangles.subList((triangles.size() / 2), triangles.size()));

            DevideVTRunnable r1 = new DevideVTRunnable(ls1, vertices);
            DevideVTRunnable r2 = new DevideVTRunnable(ls2, vertices);

            Thread th1 = new Thread(r1);
            Thread th2 = new Thread(r2);

            th1.start();
            th2.start();

            try {
                th1.join();
                th2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.addAll(r1.getProcessedData());
            result.addAll(r2.getProcessedData());
            this.leafThreadIndicator += r1.getThreadIndicator() + r2.getThreadIndicator();

        } else {
            leafThreadIndicator = 1;
            result = setTriangles(vertices);
        }
    }

    public int getThreadIndicator() {
        return leafThreadIndicator;
    }

    private ArrayList<Triangle> setTriangles(ArrayList<Vertex> vertices) {
//		for(Triangle t : triangles){
//			for (Vertex v : vertices){
//				if(t.isParentOf(v.getTriangleIDs())){
//					t.addVertex(v);
//				}
//			}
//		}
        return this.triangles;
    }

    public ArrayList<Triangle> getProcessedData() {
        return result;
    }
}
