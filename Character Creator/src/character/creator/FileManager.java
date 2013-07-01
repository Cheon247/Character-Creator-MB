package character.creator;

import java.io.File;

public class FileManager {

    private static FileManager instance;

    /**
     * Returns instance of Settings
     *
     * @return instance
     */
    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    public String getFileExtension(File f) {
        String fileName = f.getName();
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    public String getFileName(File f) {
        String fileName = f.getName();
        String name = "";

        int i = fileName.lastIndexOf('.');

        if (i > 0) {
            name = fileName.subSequence(0, i) + "";
        }
        return name;
    }
}
