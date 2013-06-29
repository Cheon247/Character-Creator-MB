package character.creator;

public class Settings {

    private boolean debugMode = false;
    private boolean triangleDebug = false;
    private static Settings instance;
    private boolean textureToLowerCase = false;

    public boolean triangleDebug() {
        return this.triangleDebug;
    }

    public boolean debugMode() {
        return this.debugMode;
    }

    public void enableDebugMode() {
        debugMode = true;
    }

    public void disableDebugMode() {
        debugMode = false;
    }

    /**
     * Returns instance of Settings
     *
     * @return instance
     */
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public boolean textureToLowerCase() {
        return textureToLowerCase;
    }
}
