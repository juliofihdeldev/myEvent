package com.musickart.fihdel.event.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by julio on 1/18/18.


public class Prog extends AppCompatActivity {
    ProgressDialog progress;
    Context context;
    String tilte ;
    String message ;
    public void showLoading( Context cont, String tilte,String message ){
        progress = ProgressDialog.show(cont, tilte,message,false,false);
    }
    public void dimissLoading(){
        progress.dismiss();
    }

}

*/