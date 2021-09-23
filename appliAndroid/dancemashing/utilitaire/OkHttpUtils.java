package com.example.dancemashing.utilitaire;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {

    public static String sendCetOkHttpRequest(String url) throws Exception {
        //Toujours effectuer un contrôle d’url en l’affichant en console
        System.out.println("Url : " + url);
        OkHttpClient client = new OkHttpClient();
        //Création de la requête
        Request request = new Request.Builder().url(url).build();
        //Exécution de la requête
        Response response = client.newCall(request).execute();
        //Analyse du code retour
        if (response.code() < 200 || response.code() > 299) {
            throw new Exception("Réponse du serveur incorrect : " + response.code());
        } else {
            //Résultat de la requête
            return response.body().string();
        }
    }


    public static String sendPostOkHttpRequest(String url, String paramJson)throws Exception {
        System.out.println("Url : " + url);
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //Corps de la requête
        RequestBody body = RequestBody.create(paramJson, JSON);
        //Création de la requête
        Request request = new Request.Builder().url(url).post(body).build();
        //Exécution de la requête
        Response response = client.newCall(request).execute();
        //Analyse du code retour
        if (response.code() < 200 || response.code() > 299) {
            throw new Exception("Réponse du serveur incorrect : " + response.code());
        } else {
            //Résultat de la requête.
            return response.body().string();
        }
    }
}
