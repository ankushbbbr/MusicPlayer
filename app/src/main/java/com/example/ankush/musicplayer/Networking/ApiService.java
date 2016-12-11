package com.example.ankush.musicplayer.Networking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ankushbabbar on 24-Sep-16.
 */
public interface ApiService {
    @GET(".")
    Call<YoutubeResponse> searchVideos(@Query("part") String part,@Query("maxResults") int maxResults,
                                       @Query("q") String q,@Query("type") String type, @Query("key") String apiKey);

}
