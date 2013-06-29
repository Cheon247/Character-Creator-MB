package parsers.smd.runnables;

import character.creator.Settings;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SMD.SMDModel;
import model.Triangle;
import model.Vertex;

public class TriangleProcessor implements Runnable {

    private SMDModel model;
    private List<String> triangleData;

    public TriangleProcessor(List<String> data, SMDModel model) {
        this.model = model;
        this.triangleData = new ArrayList<>(data);
        this.triangleData = this.triangleData.subList(this.triangleData.indexOf("triangles") + 1, this.triangleData.size() - 1);
    }

    @Override
    public void run() {
        Triangle t = null;
        int triangleIndex = -1;
        int vertexIndex = 0;
        for (String s : triangleData) {

            String[] triangleLine = s.split("\\s");

            if (triangleLine.length == 1) {
                triangleIndex++;
                t = new Triangle(triangleIndex);
                t.setTexture(s);
            } else {
                if (t != null) {

                    Vertex v = new Vertex();
                    // Set BoneID
                    v.setBoneID(Integer.parseInt(triangleLine[0]));
                    // Set Vertex positions
                    v.setPosX(Double.parseDouble(triangleLine[1]));
                    v.setPosY(Double.parseDouble(triangleLine[2]));
                    v.setPosZ(Double.parseDouble(triangleLine[3]));
                    // Set Vertex Normals
                    v.setNormX(Double.parseDouble(triangleLine[4]));
                    v.setNormY(Double.parseDouble(triangleLine[5]));
                    v.setNormZ(Double.parseDouble(triangleLine[6]));
                    // Set Norm UV && NormWeight
                    v.setNormUV(Double.parseDouble(triangleLine[7]));
                    v.setNormWeight(Double.parseDouble(triangleLine[8]));
                    v.setId(vertexIndex);

                    t.addVertex(v);
                    vertexIndex++;
                }
                model.addTriangle(t);
            }
        }
        if (Settings.getInstance().triangleDebug()) {
            File logFolder = new File("log");
            logFolder.mkdir();
            File logTriangles = new File("log/trianglesVertices.txt");


            PrintWriter triangleWriter = null;
            try {
                triangleWriter = new PrintWriter(logTriangles);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TriangleProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (Triangle triangle : model.getTriangles()) {
                triangleWriter.println(triangle.toString());
            }
            triangleWriter.close();
        }
    }
}
