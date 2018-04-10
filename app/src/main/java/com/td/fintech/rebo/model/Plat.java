package com.td.fintech.rebo.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by julio on 12/21/17.
 */

public class Plat implements Serializable{

    public Plat() {
    }

    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName_plat() {
        return name_plat;
    }

    public void setName_plat(String name_plat) {
        this.name_plat = name_plat;
    }

    public String getDescription_plat() {
        return description_plat;
    }

    public void setDescription_plat(String description_plat) {
        this.description_plat = description_plat;
    }



    public String getPrix_plat() {
        return prix_plat;
    }

    public void setPrix_plat(String prix_plat) {
        this.prix_plat = prix_plat;
    }

    public String getImgPlat() {
        return imgPlat;
    }

    public void setImgPlat(String imgPlat) {
        this.imgPlat = imgPlat;
    }

    public String name_plat;
    public String description_plat;
    public String prix_plat;
    public String imgPlat;

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public Double star;


    public Plat(JSONObject jsonObject) throws JSONException {

        this.id = jsonObject.getString("id_plat");
        this.name_plat = jsonObject.getString("name_plat");
        this.description_plat = jsonObject.getString("description_plat");
        this.prix_plat = jsonObject.getString("price_plat");
        this.imgPlat = jsonObject.getString("photo_plat");
        this.star = jsonObject.getDouble("star_plat");


    }

    public static ArrayList<Plat> fromJSONArray(JSONArray array) {

        ArrayList<Plat> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++){
            try {
                results.add (new Plat(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
