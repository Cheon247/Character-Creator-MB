package parsers.smd.processors;

import java.util.ArrayList;
import java.util.TreeMap;
import model.Bone;

import model.Triangle;
import model.Vertex;

public class BoneBinderProcessor implements Runnable {

    private int MAX_THREADS = 250 / Runtime.getRuntime().availableProcessors();
    private ArrayList<Triangle> triangles;
    private int leafThreadIndicator = 0;
    private Bone bone;
    private TreeMap boneTree;

    public BoneBinderProcessor(ArrayList<Triangle> triangles, Bone b) {
        this.bone = b;
        this.triangles = triangles;
        boneTree = new TreeMap();
    }

    @Override
    public void run() {
        if (Thread.activeCount() < MAX_THREADS) {

            ArrayList<Triangle> ls1 = new ArrayList<>(triangles.subList(0, triangles.size() / 2));
            ArrayList<Triangle> ls2 = new ArrayList<>(triangles.subList((triangles.size() / 2), triangles.size()));

            BoneBinderProcessor r1 = new BoneBinderProcessor(ls1, bone);
            BoneBinderProcessor r2 = new BoneBinderProcessor(ls2, bone);

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


            boneTree.putAll(r1.getResult());
            boneTree.putAll(r2.getResult());

            this.leafThreadIndicator += r1.getThreadIndicator() + r2.getThreadIndicator();

        } else {
            leafThreadIndicator = 1;
            setVerticesToBones(this.triangles, bone);
        }
    }

    public int getThreadIndicator() {
        return leafThreadIndicator;
    }

    private void setVerticesToBones(ArrayList<Triangle> triangles, Bone bone) {
        for (Triangle t : triangles) {
            for (Vertex v : t.getVertices()) {
                if (v.getBoneID() == bone.getBoneID()) {
                    boneTree.put(v.getId(), v);
                }
            }
        }
    }

    public TreeMap getResult() {
        return this.boneTree;
    }
}
