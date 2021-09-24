package com.example.dancemashing.model;


import java.util.Date;

public class Utilisateur {

    /*************************     ATTRIBUTS     ****************************/

    private int mIdUser;
    private String mPseudo;
    private Date mDateNaissance;
    private String mEmail;
    private String mMdp;

    /***********************     CONSTRUCTEUR     ***************************/

    public Utilisateur() {
    }

    /**********************     GETTERS/SETTERS     *************************/

    public int getmIdUser() {
        return mIdUser;
    }

    public void setmIdUser(int mIdUser) {
        this.mIdUser = mIdUser;
    }

    public String getmPseudo() {
        return mPseudo;
    }

    public void setmPseudo(String mPseudo) {
        this.mPseudo = mPseudo;
    }

    public Date getmDateDeNaissance() {
        return mDateNaissance;
    }

    public void setmDateDeNaissance(Date mDateDeNaissance) {
        this.mDateNaissance = mDateDeNaissance;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmMdp() {
        return mMdp;
    }

    public void setmMdp(String mMdp) {
        this.mMdp = mMdp;
    }

}
