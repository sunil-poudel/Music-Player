package Model;

public class SongsList {
    private String title;
    private String leadArtist;
    private StringBuilder  duration;

    public SongsList(String title, String leadArtist, StringBuilder duration) {
        this.title = title;
        this.leadArtist = leadArtist;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLeadArtist() {
        return leadArtist;
    }

    public void setLeadArtist(String leadArtist) {
        this.leadArtist = leadArtist;
    }

    public StringBuilder getDuration() {
        return duration;
    }

    public void setDuration(StringBuilder duration) {
        this.duration = duration;
    }
}
