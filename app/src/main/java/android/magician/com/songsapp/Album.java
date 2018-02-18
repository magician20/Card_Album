package android.magician.com.songsapp;

/**
 * Created by magic on 2/17/2018.
 */

public class Album {
    private String name;
    private int numbOfSongs;
    private int thumbnail;

    public void setName(String name) {
        this.name = name;
    }

    public void setNumbOfSongs(int numbOfSongs) {
        this.numbOfSongs = numbOfSongs;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Album(String name, int numbOfSongs, int thumbnail) {

        this.name = name;
        this.numbOfSongs = numbOfSongs;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public int getNumbOfSongs() {
        return numbOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }
}
