package parsers.smd.processors;

import java.util.ArrayList;
import java.util.List;
import model.Triangle;
import model.Vertex;

public class TriangleProcessor implements Runnable {

    private List<String> triangleData;
    private ArrayList<Triangle> result;

    public TriangleProcessor(List<String> data) {
        this.triangleData = new ArrayList<>(data);
        this.triangleData = this.triangleData.subList(this.triangleData.indexOf("triangles") + 1, this.triangleData.size() - 1);
        result = new ArrayList<>();
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
                result.add(t);
            }
        }

    }

    public ArrayList<Triangle> getResult() {
        return this.result;
    }
}
