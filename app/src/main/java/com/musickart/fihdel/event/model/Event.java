package com.musickart.fihdel.event.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by julio on 12/21/17.
 */

public class Event implements Serializable{
    public String id;
    public Boolean bol;

    public Event() {
    }

    public Event(String s, boolean b) {
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

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String nom_event;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String idUser;

    public String getPrix_event() {
        return prix_event;
    }

    public void setPrix_event(String prix_event) {
        this.prix_event = prix_event;
    }

    public String prix_event;

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
/*
    private static int lastContactId = 0;

    public static ArrayList<Event> createMusicList(int numContacts) {
        ArrayList<Event> contacts = new ArrayList<Event>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Event("Musiccc " + ++lastContactId, i <= numContacts / 2));
        }
        return contacts;
    }*/


    public Event(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getString("ID_event");
        this.idUser = jsonObject.getString("ID_users");
        this.nom_event = jsonObject.getString("nom_event");
        this.title = jsonObject.getString("nom");
        this.prix_event = jsonObject.getString("prix_event");
        this.ImagesMusic = jsonObject.getString("flye");
    }

    public static ArrayList<Event> fromJSONArray(JSONArray array) {
        ArrayList<Event> results = new ArrayList<>();
        for (int x = 0; x < array.length(); x++){
            try {
                results.add (new Event(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

}
