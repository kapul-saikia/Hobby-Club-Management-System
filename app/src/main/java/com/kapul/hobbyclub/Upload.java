package com.kapul.hobbyclub;

public class Upload {
    private String caption;
    private String exif;
    private String date;
    private String imageUrl;

    public Upload() {

    }

    public Upload(String caption, String exif, String date, String imageUrl) {
        if (caption.trim().equals("")) {
            caption = "No Caption";
        }
        this.caption = caption;
        this.exif = exif;
        this.date = date;
        this.imageUrl = imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getExif() {
        return exif;
    }

    public void setExif(String exif) {
        this.exif = exif;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
