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
    private DataBaseSQLite dataBaseSQLite = new DataBaseSQLite(this);

    private int scoreLeft = 0;
    private int scoreRight = 0;

    private Video videoLeft = new Video();
    private Video videoRight = new Video();

    private String nomVideoLeft;
    private String nomVideoRight;

    private Uri uriVideoLeft;
    private Uri uriVideoRight;

    /*****************     PAGE D ACCUEIL DE L ACTIVITE     *****************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());



        //stocke des videos recuperees aleatoirement grace a recupVideo de la classe DataBaseSQLite
        //videoLeft = dataBaseSQLite.recupVideo();
        //videoRight = dataBaseSQLite.recupVideo();
        //stocke le nom recupere des videos
        //nomVideoLeft = videoLeft.getmNomVideo();
        //nomVideoRight = videoRight.getmNomVideo();

        //on informe le widget videoview quelle source il devra lire
        //Uniform Resource Identifier
        //uriVideoLeft = Uri.parse(nomVideoLeft);
        //uriVideoRight = Uri.parse(nomVideoRight);

        //binding.vvLeft.setVideoURI(uriVideoLeft);
        //binding.vvLeft.setVideoPath("android.resource://raw/nicki_minaj_bang_bang_128488.mp4");
        //try {
        //    binding.vvLeft.start();
        //}catch (Exception e){
        //    e.printStackTrace();
        //    binding.tvError.setText(e.getMessage());
        //}


        //runOnUiThread(new Runnable() {
        //    @Override
        //    public void run() {
        //        //injecte la video dans dans l activite
        //        binding.vvLeft.setVideoURI(uriVideoLeft);
        //        binding.vvRight.setVideoURI(uriVideoRight);
        //    }
        //});
        //MediaController mc = new MediaController(this);
        //runOnUiThread(new Runnable() {
        //    @Override
        //    public void run() {
        //        try {
        //            mc.setAnchorView(binding.vvLeft);//ok2
        //            mc.setMediaPlayer(binding.vvLeft);
        //            mc.setAnchorView(binding.vvRight);
        //            mc.setMediaPlayer(binding.vvRight);
        //            binding.vvLeft.setMediaController(mc);//ok1
        //            binding.vvLeft.setVideoURI(uriVideoLeft);
        //            binding.vvLeft.requestFocus();
        //            binding.vvLeft.start();//ok3
        //            binding.vvRight.setMediaController(mc);
        //            binding.vvRight.setVideoURI(uriVideoLeft);
        //            binding.vvRight.requestFocus();
        //            binding.vvRight.start();
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //            binding.tvError.setText(e.getMessage());
        //        }
        //    }
        //});
    }

    //suspend la video des lors que notre activite est invisible
    @Override
    protected void onPause() {
        super.onPause();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.vvLeft.suspend();
            }
        });
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

    public void onBtPlay(View view) {
        binding.tvError.setText("");
        String nomVideoRecup = dataBaseSQLite.recupVideo().getmNomVideo();
        binding.tvError.setText(nomVideoRecup);
        //MediaController mc = new MediaController(this);
        //runOnUiThread(new Runnable() {
        //    @Override
        //    public void run() {
        //        try {
        //            mc.setAnchorView(binding.vvLeft);
        //            mc.setMediaPlayer(binding.vvLeft);
        //            mc.setAnchorView(binding.vvRight);
        //            mc.setMediaPlayer(binding.vvRight);

        //            binding.vvLeft.setMediaController(mc);
        //            binding.vvLeft.setVideoURI(uriVideoLeft);
        //            binding.vvLeft.requestFocus();
        //            binding.vvLeft.start();
        //            binding.vvRight.setMediaController(mc);
        //            binding.vvRight.setVideoURI(uriVideoLeft);
        //            binding.vvRight.requestFocus();
        //            binding.vvRight.start();
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //            binding.tvError.setText(e.getMessage());
        //        }
        //    }
        //});
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