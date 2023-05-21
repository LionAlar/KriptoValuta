package com.example.kripta.API;

import com.example.kripta.models.Coins;
import com.example.kripta.models.GetInfo;
import com.example.kripta.models.GetPrise;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryName;

public class KriptaApi {


    public interface API{
        @GET("tickers/{name}")
        Call<GetPrise> getPrice(@Path("name") String name);
        @GET("coins/{name}")
        Call<GetInfo> getInfo(@Path("name") String name);
        @GET("coins")
        Call<List<Coins>> getCoins();
    }

    private static String baseUrl = "https://api.coinpaprika.com/v1/";
    private static Retrofit retrofit;
    private static API api;

    public static API getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build();
            api = retrofit.create(API.class);
        }
        return api;
    }
}
