package com.td.fintech.rebo.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by julio on 4/10/18.
 */

public class Categorie  implements Serializable{
    public Categorie(){

    }

    public String getNameCategorie() {
        return nameCategorie;
    }

    public void setNameCategorie(String nameCategorie) {
        this.nameCategorie = nameCategorie;
    }

    public String getImageCategorie() {
        return ImageCategorie;
    }

    public void setImageCategorie(String imageCategorie) {
        ImageCategorie = imageCategorie;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    String nameCategorie ;
    String ImageCategorie;
    String idCategorie;


    public Categorie(JSONObject jsonObject) throws JSONException {

        this.idCategorie = jsonObject.getString("id_categorie");
        this.nameCategorie = jsonObject.getString("name_categorie");
        this.ImageCategorie = jsonObject.getString("photo_categorie");
    }

    public static ArrayList<Categorie> fromJSONArray(JSONArray array) {

        ArrayList<Categorie> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++){
            try {
                results.add (new Categorie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
