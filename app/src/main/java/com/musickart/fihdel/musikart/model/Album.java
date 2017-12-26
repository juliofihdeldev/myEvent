package com.musickart.fihdel.musikart.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by julio on 12/26/17.
 */

public class Album implements Serializable {
    private String mName;
    private boolean mOnline;

    public Album(String name, boolean online) {
        mName = name;
        mOnline = online;
    }

    public String getName() {
        return mName;
    }

    public boolean isOnline() {
        return mOnline;
    }

    private static int lastContactId = 0;

    public static ArrayList<Album> createContactsList(int numContacts) {
        ArrayList<Album> contacts = new ArrayList<Album>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Album("Album " + ++lastContactId, i <= numContacts / 2));
        }

        return contacts;
    }
}