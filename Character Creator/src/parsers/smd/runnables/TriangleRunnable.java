package parsers.smd.runnables;

import character.creator.Settings;
import constants.SMDConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SMD.SMDModel;
import model.SMD.SMDNumberFormat;


import model.Triangle;
import model.Vertex;

public class TriangleRunnable implements Runnable {

    private File file;
    private SMDModel model;
    private PrintWriter triangleWriter;
    private PrintWriter triangleVerticeWriter;

    public TriangleRunnable(File file, SMDModel model) {
        this.model = model;
        this.file = file;

    }

    @Override
    public void run() {
        getTriangles(file, model);
    }

    private void getTriangles(File file, SMDModel model) {
        Scanner scanner = null;

        // Step 1: Try to scan file
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BoneRunnable.class.getName()).log(Level.SEVERE, null, ex);

        }

        // Step 2: Read file
        if (scanner != null) {
            int triangleIndex = -1;
            int vertexIndex = 0;

            if (Settings.getInstance().triangleDebug()) {
                File logFolder = new File("log");
                logFolder.mkdir();
                File logTriangles = new File("log/trianglesVertices.txt");


                triangleWriter = null;
                try {
                    triangleWriter = new PrintWriter(logTriangles);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TriangleRunnable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            while (scanner.hasNextLine()) {


                if (scanner.nextLine().split("\\s")[0].equals(SMDConstants.SMD_TRIANGLES_HEADER)) {
                    String s = scanner.nextLine();
                    Triangle t = null;
                    while (!s.startsWith("end")) {


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

                                if (Settings.getInstance().triangleDebug()) {
                                    triangleWriter.print(v.toString() + "\n");
                                }

                                t.addVertex(v);
                                vertexIndex++;
                            }
                            model.addTriangle(t);
                        }
                        s = scanner.nextLine();
                    }
                }
            }
            scanner.close();
            if (triangleWriter != null) {
                triangleWriter.close();
            }
        }

        if (Settings.getInstance().triangleDebug()) {
            File logFolder = new File("log");
            logFolder.mkdir();
            File logTriangles = new File("log/triangles.txt");


            triangleVerticeWriter = null;
            try {
                triangleVerticeWriter = new PrintWriter(logTriangles);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TriangleRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (Triangle t : model.getTriangles()) {
                triangleVerticeWriter.println(t.toString());
            }
            triangleVerticeWriter.close();
        }

    }
}
