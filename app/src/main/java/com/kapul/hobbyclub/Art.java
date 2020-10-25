package com.kapul.hobbyclub;

public class Art {
    private String artname;
    private String artimageUrl;

    public Art(){

    }

    public Art(String artname, String artimageUrl) {
        this.artname = artname;
        this.artimageUrl = artimageUrl;
    }

    public String getArtname() {
        return artname;
    }

    public void setArtname(String artname) {
        this.artname = artname;
    }

    public String getArtimageUrl() {
        return artimageUrl;
    }

    public void setArtimageUrl(String artimageUrl) {
        this.artimageUrl = artimageUrl;
    }
}