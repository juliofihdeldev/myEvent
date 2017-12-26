package com.musickart.fihdel.musikart.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by julio on 12/21/17.
 */

public class Music  implements Serializable{
    public String id;
    public Boolean bol;

    public Music() {
    }

    public Music(String s, boolean b) {
        title = s;
        bol = b;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum() {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getListen() {
        return listen;
    }

    public void setListen(String listen) {
        this.listen = listen;
    }

    public String title;
    public String lien;
    public String album;
    public String duration;
    public String listen;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int img;

    public String getImagesMusic() {
        return ImagesMusic;
    }

    public void setImagesMusic(String imagesMusic) {
        ImagesMusic = imagesMusic;
    }

    public String ImagesMusic;

    private static int lastContactId = 0;

    public static ArrayList<Music> createMusicList(int numContacts) {
        ArrayList<Music> contacts = new ArrayList<Music>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Music("Musiccc " + ++lastContactId, i <= numContacts / 2));
        }
        return contacts;
    }

/*
    public Music (JSONObject jsonObject) throws JSONException {
        this.title = jsonObject.getString("title");
        this.lien = jsonObject.getString("lien");
        this.ImagesMusic = jsonObject.getString("images");
    }

    public static ArrayList<Music> fromJSONArray(JSONArray array) {
        ArrayList<Music> results = new ArrayList<>();
        for (int x = 0; x < array.length(); x++){
            try {
                results.add (new Music(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
*/

}
