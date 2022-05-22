package com.example.tinder_movie;

public class movies {

    private String title;

    private String NetflixId;

    private String imgURL;

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

    public void setImgURL(String image)  {this.imgURL=image;}

    public String getImgURL() { return imgURL;}

    public void setDescription(String dscrpt) {this.description=dscrpt;}

    public String getDescription() {return description;}
}
