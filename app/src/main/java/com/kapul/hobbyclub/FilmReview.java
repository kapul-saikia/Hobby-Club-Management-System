package com.kapul.hobbyclub;

public class FilmReview {
    private String filmname;
    private String filmreview;
    private String filmimageUrl;

    public FilmReview(){

    }

    public FilmReview(String filmname, String filmreview, String filmimageUrl) {
        this.filmname = filmname;
        this.filmreview = filmreview;
        this.filmimageUrl = filmimageUrl;
    }

    public String getFilmname() {
        return filmname;
    }

    public void setFilmname(String filmname) {
        this.filmname = filmname;
    }

    public String getFilmreview() {
        return filmreview;
    }

    public void setFilmreview(String filmreview) {
        this.filmreview = filmreview;
    }

    public String getFilmimageUrl() {
        return filmimageUrl;
    }

    public void setFilmimageUrl(String filmimageUrl) {
        this.filmimageUrl = filmimageUrl;
    }
}

