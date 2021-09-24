package com.example.dancemashing.model;

public class Video {

    /*************************     ATTRIBUTS     ****************************/

    private int mIdVideo;
    private String mNomVideo;
    private int mNbLike;


    /***********************     CONSTRUCTEUR     ***************************/

    public Video() {
    }

    public Video(int mIdVideo, String mNomVideo, int mNbLike) {
        this.mIdVideo = mIdVideo;
        this.mNomVideo = mNomVideo;
        this.mNbLike = mNbLike;
    }

    /**********************     GETTERS/SETTERS     *************************/

    public int getmIdVideo() {
        return mIdVideo;
    }

    public void setmIdVideo(int mIdVideo) {
        this.mIdVideo = mIdVideo;
    }

    public String getmNomVideo() {
        return mNomVideo;
    }

    public void setmNomVideo(String mNomVideo) {
        this.mNomVideo = mNomVideo;
    }

    public int getmNbLike() {
        return mNbLike;
    }

    public void setmNbLike(int mNbLike) {
        this.mNbLike = mNbLike;
    }

}
