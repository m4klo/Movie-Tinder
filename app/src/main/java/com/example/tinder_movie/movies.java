package com.example.tinder_movie;

public class movies {

    private String title;

    private String NetflixId;

    private String img;

    private String description;


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public movies(){
    }

    public String getNetflixId() {
        return NetflixId;
    }

    public void setNetflixId(String netflixId) {
        this.NetflixId = netflixId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDescription(String dscrpt) {this.description=dscrpt;}

    public String getDescription() {return description;}
}
