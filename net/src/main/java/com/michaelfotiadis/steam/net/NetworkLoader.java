package com.michaelfotiadis.steam.net;

import com.google.gson.Gson;
import com.michaelfotiadis.steam.net.api.Dota2Api;
import com.michaelfotiadis.steam.net.api.GamesApi;
import com.michaelfotiadis.steam.net.api.SteamApi;
import com.michaelfotiadis.steam.net.gson.SteamGson;

public class NetworkLoader {

    private static final String ENDPOINT = "https://api.steampowered.com/";

    private final RestClient steamRestClient;

    public NetworkLoader(final boolean isDebugEnabled) {

        final OkHttpFactory okHttpFactory = new OkHttpFactoryImpl(isDebugEnabled);
        final Gson gson = SteamGson.get();
        this.steamRestClient = new RestClient(ENDPOINT, gson, okHttpFactory);

    }

    public Dota2Api getDota2Api() {
        return this.steamRestClient.getDota2Api();
    }

    public GamesApi getGamesApi() {
        return this.steamRestClient.getGamesApi();
    }

    public SteamApi getSteamApi() {
        return this.steamRestClient.getSteamApi();
    }

}
