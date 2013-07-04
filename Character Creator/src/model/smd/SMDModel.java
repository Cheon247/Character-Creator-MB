package model.smd;

import model.basic.AnimModel;

public class SMDModel extends AnimModel {

    private int timeframe = -1;
    private int version = -1;
    private String genre;

    public SMDModel(String theName, String type, int id) {
        super(theName, type, id);



    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(int timeframe) {
        this.timeframe = timeframe;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
