package com.example.dancemashing.controller;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dancemashing.DataBaseSQLite;
import com.example.dancemashing.databinding.ActivityConnexionBinding;
import com.example.dancemashing.utilitaire.WSUtils;

public class ConnexionActivity extends AppCompatActivity {

    /*************************     ATTRIBUTS     ****************************/

    private ActivityConnexionBinding binding;
    private DataBaseSQLite dataBaseSQLite = new DataBaseSQLite(this);
    ;

    /*****************     PAGE D ACCUEIL DE L ACTIVITE     ******************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConnexionBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }

    /*****************     REDIRECTIONS CLIC SUR BOUTON    *******************/

    public void onIvLogoDMClic(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onIvLogoJDClic(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onBtConnexionClic(View view) throws Exception {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //pour eviter d afficher msg d erreur precedents
                binding.tvError.setVisibility(View.INVISIBLE);
            }
        });

        //appel methode deportee si utilisation d une bdd externe
        //showData();

        new Thread() {
            @Override
            public void run() {
                String strMail = binding.etMail.getText().toString();
                String strMdp = binding.etMdp.getText().toString();
                String strSql = "SELECT mdp FROM utilisateur WHERE email = ?";
                //a l aide du Curseur on recupere le resultat de la requete sql
                //WHERE mail =? : ? correspond au contenu du new String, cad au mail saisi par l utilisateur
                Cursor cursor = dataBaseSQLite.getReadableDatabase().rawQuery(strSql, new String[]{strMail});

                try { //dans ce cas le mail saisi existe dans la bdd
                    //on se place sur le 1er element du curseur
                    cursor.moveToFirst();
                    //on stocke le resultat de la requete : une seule colonne (0) qui contient une chaine de caractere (mdp)
                    String strBdd = cursor.getString(0);
                    //on verifie la correspondance du mdp saisi avec celui sauvegarde dans la bdd
                    if (WSUtils.sha256(strBdd).equals(WSUtils.sha256(strMdp))) {
                        showErrorMsg("Identifiant et mot de passe valides. Bienvenu(e)!");
                        //Toast.makeText(getApplicationContext(), "Identifiant et mot de passe valides. Bienvenu(e)!", Toast.LENGTH_SHORT).show();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.etMdp.setText("");
                            }
                        });
                        showErrorMsg("Mot de passe invalide. Veuillez le ressaissir.");
                        //Toast.makeText(getApplicationContext(), "Identifiant et mot de passe invalides. Veuillez recommencer.", Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                } catch (Exception e) { //dans ce cas le mail saisi n existe pas dans la bdd
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.etMail.setText("");
                            binding.etMdp.setText("");
                        }
                    });
                    e.printStackTrace();
                    showErrorMsg("Email inexistant.");
                }
            }
        }.start();
    }

    //TODO redirection sur une nouvelle activite si mot de passe oublie

    public void onLienSinscrire(View view) {
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }

    /**********************     METHODES DEPORTEES POUR ALLEGER LE CODE    **************************/
    //si utilisation d une bdd externe
    //cette methode utilise un thread secondaire pour ne pas ralentir le thread principal lors de l'appel de WSUtils.loadBDD()
    //WSUtils.loadBDD() parse le JSON, recu de la requete OkHttpUtils.sendCetOkHttpRequest, et le transforme en objet
    public void showData() {
        new Thread() {
            @Override
            public void run() {
                try {
                    WSUtils.loadBDD();
                } catch (Exception e) {
                    e.printStackTrace();
                    showErrorMsg(e.getMessage());
                }
            }
        }.start();
    }

    //cette methode utilise un runOnUiThread car fait appel a des composants graphiques
    private void showErrorMsg(String errorMsg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.tvError.setVisibility(View.VISIBLE);
                //affiche dans l'appli le msg d'erreur
                binding.tvError.setText(errorMsg);
            }
        });
    }


}