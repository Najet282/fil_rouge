package com.example.dancemashing.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dancemashing.databinding.ActivityTopBinding;

import java.io.Serializable;

public class TopActivity extends AppCompatActivity implements Serializable {

    /*************************     ATTRIBUTS     ****************************/

    private ActivityTopBinding binding;

    /*****************     PAGE D ACCUEIL DE L ACTIVITE     *****************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTopBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        //TODO passage de param que l'on récupère de VideoActivity
        //String param = getIntent().getSerializableExtra("imageTop");
        //binding.ivTop.setImageResource();
    }

    /*****************     REDIRECTIONS CLIC SUR BOUTON    ******************/

    public void onIvLogoDMClic(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onIvLogoJDClic(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}