package com.michaelfotiadis.steam.net;

import com.google.gson.Gson;
import com.michaelfotiadis.steam.net.api.Dota2Api;
import com.michaelfotiadis.steam.net.api.GamesApi;
import com.michaelfotiadis.steam.net.api.SteamApi;

import retrofit2.Retrofit;

public class RestClient {

    private final Retrofit2Factory mRetrofit2Factory;

    public RestClient(final String baseUrl,
                      final Gson gson,
                      final OkHttpFactory factory) {

        mRetrofit2Factory = new Retrofit2Factory(baseUrl, gson, factory);

    }

    public Dota2Api getDota2Api() {
        return createApi(Dota2Api.class);
    }

    public GamesApi getGamesApi() {
        return createApi(GamesApi.class);
    }

    public SteamApi getSteamApi() {
        return createApi(SteamApi.class);
    }

    private synchronized <T> T createApi(final Class<T> clazz) {
        final Retrofit retrofit = mRetrofit2Factory.create(clazz);
        return retrofit.create(clazz);
    }


}
