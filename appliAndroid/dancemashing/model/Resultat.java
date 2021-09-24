package com.example.dancemashing.model;

public class Resultat {

    /*************************     ATTRIBUTS     ****************************/

    private Utilisateur utilisateur;
    private Video video;
    private Format format;
    private Role role;
    private Droit droit;

    /***********************     CONSTRUCTEUR     ***************************/

    public Resultat() {
    }

    /**********************     GETTERS/SETTERS     *************************/

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Droit getDroit() {
        return droit;
    }

    public void setDroit(Droit droit) {
        this.droit = droit;
    }

}
