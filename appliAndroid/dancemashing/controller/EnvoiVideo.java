package com.example.dancemashing.controller;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dancemashing.DataBaseSQLite;
import com.example.dancemashing.databinding.ActivityEnvoiVideoBinding;

public class EnvoiVideo extends AppCompatActivity {

    /*************************     ATTRIBUTS     ****************************/

    private ActivityEnvoiVideoBinding binding;
    private DataBaseSQLite dataBaseSQLite;

    /*****************     PAGE D ACCUEIL DE L ACTIVITE     *****************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEnvoiVideoBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }

    /******************     REDIRECTION CLIC SUR BOUTON    ******************/

    public void onBtValider(View view) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.tvError.setVisibility(View.INVISIBLE);
            }
        });

        try {
            dataBaseSQLite = new DataBaseSQLite(this);
            //on insere dans la bdd le contenu du champ saisi
            dataBaseSQLite.insertVideo(binding.etUrlVideo.getText().toString());
            dataBaseSQLite.close();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorMsg(e.getMessage());
        }
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