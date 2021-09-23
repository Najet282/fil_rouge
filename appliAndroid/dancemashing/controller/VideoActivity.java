package com.example.dancemashing.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dancemashing.DataBaseSQLite;
import com.example.dancemashing.databinding.ActivityVideoBinding;
import com.example.dancemashing.model.Video;

import java.io.Serializable;

public class VideoActivity extends AppCompatActivity implements Serializable {

    /*************************     ATTRIBUTS     ****************************/

    private ActivityVideoBinding binding;
    private DataBaseSQLite dataBaseSQLite;

    private int scoreLeft = 0;
    private int scoreRight = 0;

    //stocke des videos recuperees aleatoirement grace a recupVideo de la classe DataBaseSQLite
    private Video videoLeft = dataBaseSQLite.recupVideo();
    private Video videoRight = dataBaseSQLite.recupVideo();
    //stocke le nom recupere des videos
    private String nomVideoLeft = videoLeft.getmNomVideo();
    private String nomVideoRight = videoRight.getmNomVideo();

    /*****************     PAGE D ACCUEIL DE L ACTIVITE     *****************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        //on informe le widget videoview quelle source il devra lire
        //Uniform Resource Identifier
        Uri uriVideoLeft = Uri.parse(nomVideoLeft);
        Uri uriVideoRight = Uri.parse(nomVideoRight);

        //injecte la video dans dans l activite
        binding.vvLeft.setVideoURI(uriVideoLeft);
        binding.vvRight.setVideoURI(uriVideoRight);

        MediaController mc = new MediaController(this);
        mc.setAnchorView(binding.vvLeft);
        mc.setMediaPlayer(binding.vvLeft);
        mc.setAnchorView(binding.vvRight);
        mc.setMediaPlayer(binding.vvRight);

        binding.vvLeft.setMediaController(mc);
        binding.vvLeft.setVideoURI(uriVideoLeft);
        binding.vvLeft.requestFocus();
        binding.vvLeft.start();
        binding.vvRight.setMediaController(mc);
        binding.vvRight.setVideoURI(uriVideoLeft);
        binding.vvRight.requestFocus();
        binding.vvRight.start();
    }

    //suspend la video des lors que notre activite est invisible
    @Override
    protected void onPause() {
        super.onPause();
        binding.vvLeft.suspend();
    }

    //TODO or NOT TODO qd l activite redevient visible apres avoir ete mis en arriere plan
    //onResume() en executant binding.vvLeft.seekTo(int ms); pour reprendre la lecture a un emplacement precis en ms

    /*****************     REDIRECTIONS CLIC SUR BOUTON    ******************/

    public void onIvLogoDMClic(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onIvLogoJDClic(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void onBtPouceLeft(View view) {
        scoreLeft++;
        compareScore();
    }

    public void onBtPouceRight(View view) {
        scoreRight++;
        compareScore();
    }

    /************     METHODE DEPORTEE POUR ALLEGER LE CODE    ************/

    public Video compareScore() {
        if (scoreLeft > scoreRight) {
            return videoLeft;
        }
        //TODO gerer l egalite
        return videoRight;
    }

}