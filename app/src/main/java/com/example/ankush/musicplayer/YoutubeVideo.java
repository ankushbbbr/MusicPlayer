package com.example.ankush.musicplayer;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ankushbabbar on 13-Oct-16.
 */
public class YoutubeVideo {
    public String title;
    public String description;
    @SerializedName("url")
    public String imgURL;
    public String videoId;
    public YoutubeVideo(String title,String description,String imgURL,String videoId){
        this.title=title;
        this.description=description;
        this.imgURL=imgURL;
        this.videoId=videoId;
    }
}
