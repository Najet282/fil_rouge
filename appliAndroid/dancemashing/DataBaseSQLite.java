package com.example.dancemashing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import com.example.dancemashing.model.Utilisateur;
import com.example.dancemashing.model.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//classe qui regroupe tout le code relatif a l acces a la bdd
public class DataBaseSQLite extends SQLiteOpenHelper {
//SQLiteOpenHelper fournit un support specifique a SQLite

    /*************************     ATTRIBUTS     ****************************/

    private static final String NOM_BDD = "dancemashing.db";
    private static final int VERSION_BDD = 2;
    private List<Video> videoList = new ArrayList<>();
    private List<String> nomVideoList = new ArrayList<>();
    private Video video  = new Video();
    private Random random = new Random();

    /***********************     CONSTRUCTEUR     **************************/

    //le constructeur a besoin d un context, d’un nom de bdd et d un numéro de version
    public DataBaseSQLite(Context context) {
        super(context, NOM_BDD, null, VERSION_BDD);
    }

    /********************     CREATION DE LA BASE     *********************/

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //on definit les ordres SQL de construction de tables
        String strSqlUser = "CREATE TABLE utilisateur ( idUser INTEGER PRIMARY KEY AUTOINCREMENT, pseudo TEXT NOT NULL, dateNaissance DATE NOT NULL, email TEXT NOT NULL, mdp TEXT NOT NULL);";
        //on execute l ordre SQL
        sqLiteDatabase.execSQL(strSqlUser);

        String strSqlVideo = "CREATE TABLE video ( idVideo INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT NOT NULL, nbLike INTEGER);";
        sqLiteDatabase.execSQL(strSqlVideo);

        String strSqlFormat = "CREATE TABLE format ( idFormat INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT NOT NULL);";
        sqLiteDatabase.execSQL(strSqlFormat);

        String strSqlRole = "CREATE TABLE role ( idRole INTEGER PRIMARY KEY AUTOINCREMENT, choregraphe BOOLEAN NOT NULL, danseur BOOLEAN NOT NULL, visiteur BOOLEAN NOT NULL);";
        sqLiteDatabase.execSQL(strSqlRole);

        String strSqlDroit = "CREATE TABLE droit ( idDroit INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT NOT NULL);";
        sqLiteDatabase.execSQL(strSqlDroit);

        //on verifie que la methode ne soit invoquee qu une seule et unique fois
        Log.w("Tag", "onCreate invoked");
    }

    //methode qui definit le comportement a adopter si la version de la bdd change
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //mise à jour de la structure de la bdd
        sqLiteDatabase.execSQL("DROP TABLE " + "utilisateur;");
        sqLiteDatabase.execSQL("DROP TABLE " + "video;");
        sqLiteDatabase.execSQL("DROP TABLE " + "format;");
        sqLiteDatabase.execSQL("DROP TABLE " + "role;");
        sqLiteDatabase.execSQL("DROP TABLE " + "droit;");
        onCreate(sqLiteDatabase);

        //on verifie que la methode soit invoquee lors d une mise a jour de la bdd
        Log.w("Tag", "onUpgrade invoked");
    }

    /******************     UTILISATION DE LA BASE     *******************/

    public void insertUser(String pseudo, String dateNaissance, String email, String mdp) {
        //ouverture de la bdd en ecriture
        SQLiteDatabase bdd = this.getWritableDatabase();
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put("pseudo", pseudo);
        values.put("dateNaissance", dateNaissance);
        values.put("email", email);
        values.put("mdp", mdp);

        bdd.insert("utilisateur", null, values);

        bdd.close();

        //on verifie que la methode soit invoquee lors d une insertion d utilisateur dans la bdd
        Log.w("Tag", ("insertUser invoked !"));
    }

    public void insertVideo(String nom) {
        //ouverture de la bdd en ecriture
        SQLiteDatabase bdd = this.getWritableDatabase();
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put("nom", nom);
        values.put("nbLike", 0);
        bdd.insert("video", null, values);

        bdd.close();

        //on verifie que la methode soit invoquee lors d une insertion de video dans la bdd
        Log.w("Tag", ("insertVideo invoked !"));
    }

    public List<Video> getAllVideos() {
        //ouverture de la bdd en ecriture
        SQLiteDatabase bdd = this.getWritableDatabase();
        Cursor c = this.getWritableDatabase().query("video", new String[]{"idVideo", "nom", "nbLike"}, null, null, null, null, null);
        List<Video> result = cursorToVideos(c);
        bdd.close();
        return result;
    }
    //Cette méthode permet de convertir un cursor en list de videos
    private List<Video> cursorToVideos(Cursor c) {
        ArrayList<Video> videoListe = new ArrayList<>();
        if (c != null) {
        //On se place sur le premier élément
            if (c.moveToFirst()) {
                do {
                    Video studentBean= new Video(c.getInt(0), c.getString(1), c.getInt(2));
                    videoListe.add(studentBean);
                } while (c.moveToNext());
            }
        }
        //On ferme le cursor
        c.close();
        //On retourne la liste
        return videoListe;
    }

    public Video recupVideo() {

        //1ere technique avec un ordre sql
        String strSql = "SELECT * FROM video";
        //on recupère les donnees de la bdd a l aide d un Cursor
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);

        //2eme technique avec un objet qui va produire l ordre sql
        Cursor c = this.getWritableDatabase().query("video", new String[]{"idVideo", "nom", "nbLike"}, null, null, null, null, null);
        //parametres: nom de la table ou se connecter, tableau des noms des colonnes de ma table que je veux recuperer dans le bon ordre
        //null si pas de where, pas de groupeBy, pas de having, pas d orderBy, pas de limit

        //peu importe la technique choisie
        //on se place sur le 1er element
        c.moveToFirst();
        //le curseur a un taquet de debut et un de fin autour des donnees a recuperer
        //tant qu on  est pas sur le taquet de fin (cela veut dire que l on a au moins un donnee dans la bdd)
        while (!c.isAfterLast()) {
            //videos les infos que je lis du curseur en fonction de la colonne
            video.setmIdVideo(c.getInt(0));
            video.setmNomVideo(c.getString(1));
            video.setmNbLike(c.getInt(2));
            //for (int i = 0; i < cursor.getCount(); i++) {
            videoList.add(video);
            //je deplace le cursor sur l element suivant
            c.moveToNext();
            //}
        }
        int nbRandom = random.nextInt(videoList.size());//
        c.close();
        return videoList.get(nbRandom);
    }

    public String recupNomVideo() {

        //1ere technique avec un ordre sql
        String strSql = "SELECT nom FROM video";
        //pour recuperer des donnees d une bdd il faut creer un objet de type Cursor
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);

        //2eme technique avec un objet qui va produire l ordre sql
        Cursor c = this.getWritableDatabase().query("video", new String[]{"nom"}, null, null, null, null, null);
        //parametres: nom de la table ou se connecter, tableau des noms des colonnes de ma table que je veux recuperer dans le bon ordre
        //null si pas de where, pas de groupeBy, pas de having, pas d orderBy, pas de limit

        //peu importe la technique choisie
        //on se place sur le 1er element
        cursor.moveToFirst();
        //le curseur a un taquet de debut et un de fin autour des donnees a recuperer
        //tant qu on  est pas sur le taquet de fin (cela veut dire que l on a au moins un donnee dans la bdd)
        while (!cursor.isAfterLast()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                //j ajoute la video a ma liste de video les infos que je lis du curseur en fonction de la colonne
                nomVideoList.add(cursor.getString(0));
                //je deplace le cursor sur l element suivant
                cursor.moveToNext();
            }
        }

        int nbRandom = random.nextInt(nomVideoList.size());
        String nomVideoRandom = nomVideoList.get(nbRandom);

        cursor.close();
        return nomVideoRandom;
    }

    public int suppVideo(int id) {
        //ouverture de la bdd en ecriture
        SQLiteDatabase bdd = this.getWritableDatabase();
        int resultSupp = bdd.delete("video", "idVideo = " + id, null);
        bdd.close();
        return resultSupp;
    }

    public List<Video> top3() {
        String strSql = "SELECT * FROM video ORDER BY nbLike DESC LIMIT 3";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Video video = new Video(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
            videoList.add(video);
            cursor.moveToNext();
        }
        cursor.close();
        return videoList;
    }

}
