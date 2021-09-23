package com.example.dancemashing.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dancemashing.databinding.ActivityConnexionBinding;
import com.example.dancemashing.utilitaire.WSUtils;

public class ConnexionActivity extends AppCompatActivity {

    /*************************     ATTRIBUTS     ****************************/

    private ActivityConnexionBinding binding;
    int counter = 3;

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
        //pour eviter d afficher msg d erreur precedents
        binding.tvError.setVisibility(View.INVISIBLE);

        //appel methode deportee si utilisation d une bdd externe
        //showData();

        String strEmailSql = "SELECT email FROM utilisateur";

        //TODO parcourir la liste des emails et mdp de la bdd pour verifier ce qui suit
        //on verifie la correspondance entre les donnees saisies lors de la tentative de connexion et ceux de la bdd
        if (binding.etMail.getText().toString().equals("") && binding.etMpd.getText().toString().equals("test")) {
            Toast.makeText(getApplicationContext(), "Identifiant et mot de passe valides", Toast.LENGTH_SHORT).show();
            //TODO retour sur son compte

        } else {
            Toast.makeText(getApplicationContext(), "Identifiant ou mot de passe invalide", Toast.LENGTH_SHORT).show();
            //on desincremente le nb de tentatives
            counter--;
            if (counter == 0) {
                Toast.makeText(getApplicationContext(), "Nombre de tentatives maximum atteint", Toast.LENGTH_SHORT).show();
                //retour sur la page d'accueil
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }

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