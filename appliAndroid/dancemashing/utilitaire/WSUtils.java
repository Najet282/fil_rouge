package com.example.dancemashing.utilitaire;

import com.example.dancemashing.model.Resultat;
import com.google.gson.Gson;

import java.security.MessageDigest;

public class WSUtils {

    /*************************     ATTRIBUT     ****************************/

    private final static Gson myGson = new Gson();

    /*************************     METHODEs     ****************************/

    //parse le JSON recu de la requete OkHttpUtils.sendCetOkHttpRequest, et le transforme en objet
    public static Resultat loadBDD() throws Exception {
        String myJson = OkHttpUtils.sendCetOkHttpRequest("http://localhost/phpmyadmin/index.php?route=/database/structure&server=1&db=dancemashing");
        //je transmets en param mon Json et la classe représentée par mon Json
        Resultat resultat = myGson.fromJson(myJson, Resultat.class);
        System.out.println(resultat);
        return resultat;
    }

    //on utilisera cette methode pour verifier la correspondance entre les donnees saisies par l utilisateur et celles enregistrees dans la bdd
    //Secure Hash Algorithm 256 bits genere une cle de hachage (=son empreinte numerique, =hashcode, =condensat)
    //en theorie elle est unique : 2 contenus ne peuvent produire la meme cle
    public static String sha256(String str) {
        try {
            //on cree le hash de SHA256 en le recuperant de la bibliotheque MessageDigest
            MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA256");
            //on ajoute notre chaine transmise en param que l on convertit en octet
            messageDigest.update(str.getBytes());
            //on cree un tableau d octets
            byte digest[] = messageDigest.digest();
            //les variables de type byte sont comprises dans l intervalle -127 a 128
            //on veut des cles de hashage positives, non signees
            //conversion a l aide de la classe StingBuffer
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                //on convertit les nb negatifs en nb positifs de 0 a 255
                hexString.append(Integer.toHexString(digest[i] & 0xFF));
            }
            //on retourne la chaine de resultat
            return hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
