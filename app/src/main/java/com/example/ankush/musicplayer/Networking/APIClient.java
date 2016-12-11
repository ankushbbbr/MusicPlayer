package com.example.ankush.musicplayer.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ankushbabbar on 24-Sep-16.
 */
public class APIClient {
    static ApiService service;

    public static ApiService getService() {
        if(service==null){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(URLConstants.BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();
            service =retrofit.create(ApiService.class);
        }
        return service;
    }
}
