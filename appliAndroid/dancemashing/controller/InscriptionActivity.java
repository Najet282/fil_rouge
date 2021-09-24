package com.example.dancemashing.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dancemashing.DataBaseSQLite;
import com.example.dancemashing.databinding.ActivityInscriptionBinding;
import com.example.dancemashing.utilitaire.WSUtils;

public class InscriptionActivity extends AppCompatActivity {

    /*************************     ATTRIBUTS     ****************************/

    private ActivityInscriptionBinding binding;
    private DataBaseSQLite dataBaseSQLite;

    /*****************     PAGE D ACCUEIL DE L ACTIVITE     ******************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInscriptionBinding.inflate(getLayoutInflater());

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

    public void onBtInscriptionClic(View view) {
        binding.tvError.setVisibility(View.INVISIBLE);

        try {
            dataBaseSQLite = new DataBaseSQLite(this);
            //on insere dans la bdd le contenu du champ saisi; pour le mdp on sauvegarde sa cle de hashage pour le proteger
            dataBaseSQLite.insertUser(binding.etPseudo.getText().toString(), binding.etDateNaissance.getText().toString(), binding.etMail.getText().toString(), WSUtils.sha256(binding.etMdp.getText().toString()));
            dataBaseSQLite.close();

            //TODO redirection sur page d accueil personnalisee, associee a mon compte
        } catch (Exception e) {
            e.printStackTrace();
            showErrorMsg(e.getMessage());
        }

        //pour afficher la liste des 3 meilleures videos
        //List<Video> videoList = dataBaseSQLite.top3();
        //for(Video video : videoList) {
        //    runOnUiThread(new Runnable() {
        //        @Override
        //        public void run() {
        //            binding.idListVideoTest.append(video.toString());
        //        }
        //    });
        //}

    }

    /************     METHODE DEPORTEE POUR ALLEGER LE CODE    ************/

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
