/*import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.facebook.login.LoginManager;

import jfsl.jobetrouve.R;
import jfsl.jobetrouve.activities.DrawerActivity;
import jfsl.jobetrouve.profil.EditProfil;

import static jfsl.jobetrouve.utils.BackendlessSetting.APP_ID;
import static jfsl.jobetrouve.utils.BackendlessSetting.SECRET_KEY;
import static jfsl.jobetrouve.utils.BackendlessSetting.VERSION;
/*package com.musickart.fihdel.musikart.activity;



public class Traitment_FBLogin extends AppCompatActivity {
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bntSignToBackendless();
    }
    public void showLoading(){
        progress = ProgressDialog.show(this, "Connexion","Patientez...",false,false);
    }

    public void dimissLoading(){
        progress.dismiss();
    }

    public void bntSignToBackendless() {
        // Get all data from facebook loginpage
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String nameUser = extras.getString("name") +" "+ extras.getString("surname");
            final String passwordUser = extras.getString("fb_id");
            // final String birthday = extras.getString("fb_birthday");
            final String emailUser = extras.getString("fb_email");
            final String imageUrl = extras.getString("imageUrl");
            final String telephone = extras.getString("g_phone");
            final String emailMaker = passwordUser+"@jobetrouve.com";
            showLoading();

            BackendlessUser user = new BackendlessUser();
            user.setProperty("name", nameUser);
            user.setProperty("email", emailMaker);
            user.setProperty("emailContact", emailUser);
            user.setProperty("photo", imageUrl);
            // user.setProperty("dateNaissance", birthday);
            user.setProperty("telephone", telephone);
            user.setPassword(passwordUser);

            Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                public void handleResponse(BackendlessUser registeredUser) {

                    //use the backendless login api
                    Backendless.UserService.login(emailMaker, passwordUser, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {

                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Traitment_FBLogin.this);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("name", response.getProperty("name").toString());
                            editor.putString("email", response.getEmail());
                            editor.putString("ID_users", response.getObjectId());
                            editor.commit();

                            Intent i = new Intent(getApplicationContext(), EditProfil.class);
                            startActivity(i);
                            finish();
                            dimissLoading();
                        }

                        @Override
                        public void handleFault(BackendlessFault backendlessFault) {
                            dimissLoading();
                            String code = backendlessFault.getCode();
                            if (code.equals("3040")) {
                                Toast.makeText(getApplicationContext(), " L'adresse e-mail est incorrecte ", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, true);
                }

                public void handleFault(BackendlessFault fault) {
                    String code = fault.getCode();
                    String codeMessage = fault.getDetail();

                    loginIfalready(emailMaker,passwordUser);
                     */
/*
                        String code = fault.getCode();
                        if (code.equals("3033")) {
                            loginIfalready(emailUser,passwordUser,nameUser);
                        }
                    *//*

                }
            });
        }else{
           // No data passing
        }
    }
    public void loginIfalready(final String theEmail , final String thePassword){
        showLoading();
        Backendless.UserService.login(theEmail, thePassword, new AsyncCallback<BackendlessUser>() {
        @Override
        public void handleResponse(BackendlessUser response) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Traitment_FBLogin.this);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("name", response.getProperty("name").toString());
            // editor.putString("email", response.getEmail());
            editor.putString("ID_users", response.getObjectId());
            editor.commit();
            */
/* Send it to a update profile *//*

            Intent i = new Intent(getApplicationContext(), DrawerActivity.class);
            startActivity(i);
            finish();
            dimissLoading();
        }

        @Override
        public void handleFault(BackendlessFault backendlessFault) {
            dimissLoading();
             String code = backendlessFault.getCode();
             String detcode = backendlessFault.getDetail();

                new AlertDialog.Builder(Traitment_FBLogin.this)
                        .setTitle("Continuer avec Facebook")
                        .setMessage(R.string.ne_peut_connecter_avec_facebook)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                               LoginManager.getInstance().logOut();
                                finish();
                            }
                        })
                        .setIcon(R.drawable.ic_action_alert)
                        .show();

        }
    }, true);
}
}
*/
