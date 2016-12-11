package com.example.ankush.musicplayer.Networking;

import java.util.ArrayList;

/**
 * Created by ankushbabbar on 13-Oct-16.
 */
public class YoutubeResponse {
    ArrayList<Item> items;

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public static class Item{
        id id;
        snippet snippet;

        public Item.snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Item.snippet snippet) {
            this.snippet = snippet;
        }

        public Item.id getId() {
            return id;
        }

        public void setId(Item.id id) {
            this.id = id;
        }

        public static class id{
            String videoId;

            public String getVideoId() {
                return videoId;
            }
            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }
        }

        public static class snippet{
            String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            String description;
            thumbnail thumbnails;

            public Item.snippet.thumbnail getThumbnails() {
                return thumbnails;
            }

            public void setThumbnails(Item.snippet.thumbnail thumbnails) {
                this.thumbnails = thumbnails;
            }

            public static class thumbnail{
                medium medium;

                public Item.snippet.thumbnail.medium getMedium() {
                    return medium;
                }

                public void setMedium(Item.snippet.thumbnail.medium medium) {
                    this.medium = medium;
                }

                public static class medium{
                    String url;//video thumbnails image url

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }
                }
            }
        }
    }

}
