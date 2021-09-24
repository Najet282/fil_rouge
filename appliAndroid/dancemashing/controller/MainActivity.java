package com.example.dancemashing.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dancemashing.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    /*************************     ATTRIBUTS     ****************************/

    private ActivityMainBinding binding;

    /*****************     PAGE D ACCUEIL DE L ACTIVITE     *****************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }

    /*****************     REDIRECTIONS CLIC SUR BOUTON    ******************/

    public void onBtConnexionClic(View view) {
        Intent intent = new Intent(this, ConnexionActivity.class);
        startActivity(intent);
    }

    public void onIvLogoDMClic(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onIvLogoJDClic(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onTvTitleVideosClic(View view) {
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }

    public void onIvVideo1Clic(View view) {
    }

    public void onIvVideo2Clic(View view) {
    }

    public void onIvVideo3Clic(View view) {
    }

    public void onBtMashClic(View view) {
    }

    public void onTvTitleChoreClic(View view) {
    }

    public void onIvChoreClic(View view) {
    }

    public void onBtPosterVideoClic(View view) {
        Intent intent = new Intent(this, EnvoiVideo.class);
        startActivity(intent);
    }

    public void onTvTitleTopClic(View view) {
        Intent intent = new Intent(this, TopActivity.class);
        startActivity(intent);
    }

    public void onIvTop1Clic(View view) {
    }

    public void onIvTop2Clic(View view) {
    }

    public void onIvTop3Clic(View view) {
    }

    public void onTvTitleLastClic(View view) {
    }

    public void onIvLast1Clic(View view) {
    }

    public void onIvLast2Clic(View view) {
    }
}