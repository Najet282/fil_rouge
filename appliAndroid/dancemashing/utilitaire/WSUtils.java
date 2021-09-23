package com.example.dancemashing.utilitaire;

import com.example.dancemashing.model.Resultat;
import com.google.gson.Gson;

public class WSUtils {

    /*************************     ATTRIBUT     ****************************/

    private final static Gson myGson = new Gson();

    /*************************     METHODE     ****************************/

    //parse le JSON recu de la requete OkHttpUtils.sendCetOkHttpRequest, et le transforme en objet
    public static Resultat loadBDD() throws Exception {
        String  myJson = OkHttpUtils.sendCetOkHttpRequest("http://localhost/phpmyadmin/index.php?route=/database/structure&server=1&db=dancemashing");
        //je transmets en param mon Json et la classe représentée par mon Json
        Resultat resultat = myGson.fromJson(myJson, Resultat.class);
        System.out.println(resultat);
        return resultat;
    }

}
