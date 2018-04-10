package com.td.fintech.rebo.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by julio on 2/10/18.
 */

public class Resto implements Serializable {

    /*
         "id_resto": "2",
         "nom_resto": "KUYA Restaurant",
         "address_resto": "Delmas 40",
         "etoile_resto": "5",
         "images_resto": "http://www.kuyaj.ph/wp-content/uploads/2017/03/logo.png",
         "user": "2"
    */

    public String id_resto;
    public String nom_resto;
    public String address_resto;
    public String images_resto;
    public String user;
    public String etoile_resto;


    public String getId_resto() {
        return id_resto;
    }

    public void setId_resto(String id_resto) {
        this.id_resto = id_resto;
    }

    public String getNom_resto() {
        return nom_resto;
    }

    public void setNom_resto(String nom_resto) {
        this.nom_resto = nom_resto;
    }

    public String getAddress_resto() {
        return address_resto;
    }

    public void setAddress_resto(String address_resto) {
        this.address_resto = address_resto;
    }

    public String getImages_resto() {
        return images_resto;
    }

    public void setImages_resto(String images_resto) {
        this.images_resto = images_resto;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public String getEtoile_resto() {
        return etoile_resto;
    }

    public void setEtoile_resto(String etoile_resto) {
        this.etoile_resto = etoile_resto;
    }

    public Resto(JSONObject jsonObject) throws JSONException {
        this.id_resto = jsonObject.getString("id_resto");
        this.user = jsonObject.getString("user");
        this.nom_resto = jsonObject.getString("nom_resto");
        this.address_resto = jsonObject.getString("address_resto");
        this.etoile_resto = jsonObject.getString("etoile_resto");
        this.images_resto = jsonObject.getString("images_resto");
    }

    public static ArrayList<Resto> fromJSONArray(JSONArray array) {
        ArrayList<Resto> results = new ArrayList<>();
        for (int x = 0; x < array.length(); x++){
            try {
                results.add (new Resto(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

}
