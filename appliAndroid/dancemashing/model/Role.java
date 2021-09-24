package com.example.dancemashing.model;

public class Role {

    /*************************     ATTRIBUTS     ****************************/

    private int mIdRole;
    private boolean mChoregraphe;
    private boolean mDanseur;
    private boolean mVisiteur;

    /***********************     CONSTRUCTEUR     ***************************/

    public Role() {
    }

    /**********************     GETTERS/SETTERS     *************************/

    public int getmIdRole() {
        return mIdRole;
    }

    public void setmIdRole(int mIdRole) {
        this.mIdRole = mIdRole;
    }

    public boolean ismChoregraphe() {
        return mChoregraphe;
    }

    public void setmChoregraphe(boolean mChoregraphe) {
        this.mChoregraphe = mChoregraphe;
    }

    public boolean ismDanseur() {
        return mDanseur;
    }

    public void setmDanseur(boolean mDanseur) {
        this.mDanseur = mDanseur;
    }

    public boolean ismVisiteur() {
        return mVisiteur;
    }

    public void setmVisiteur(boolean mVisiteur) {
        this.mVisiteur = mVisiteur;
    }

}
