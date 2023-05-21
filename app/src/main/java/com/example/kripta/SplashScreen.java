package com.example.kripta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kripta.API.KriptaApi;
import com.example.kripta.adapters.RecyclerViewAdapter;
import com.example.kripta.models.Coins;
import com.example.kripta.models.GetInfo;
import com.example.kripta.models.GetPrise;
import com.example.kripta.models.RecyclerViewItems;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    public static String FILE_NAME = "FILE", ITEMS_PROFIL = "ITEMS_PROFIL";

    public static List<RecyclerViewItems> items = new ArrayList<>();
    public static List<RecyclerViewItems> itemsProfil = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        loadData();

        KriptaApi.getInstance().getCoins().enqueue(new Callback<List<Coins>>() {
            @Override
            public void onResponse(Call<List<Coins>> call, Response<List<Coins>> response) {
                List<Coins> coins = response.body();
                if(coins != null) {
                    for (int i = 0; i < 20; i++) { // coins.size()
                        items.add(new RecyclerViewItems(coins.get(i).getName(), "", coins.get(i).getSymbol(), -1, -1));
                        int finalI = i;

                        KriptaApi.getInstance().getInfo(coins.get(i).getId()).enqueue(new Callback<GetInfo>() {
                            @Override
                            public void onResponse(Call<GetInfo> call, Response<GetInfo> response) {
                                if(response.body() != null) {
                                    items.get(finalI).setDescription(response.body().getDescription());
                                }else{
                                    Toast.makeText(SplashScreen.this, "Проблемы с интернетом", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<GetInfo> call, Throwable t) {
                                Toast.makeText(SplashScreen.this, "Ошибка загрузки описания", Toast.LENGTH_SHORT).show();
                            }
                        });

                        KriptaApi.getInstance().getPrice(coins.get(i).getId()).enqueue(new Callback<GetPrise>() {
                            @Override
                            public void onResponse(Call<GetPrise> call, Response<GetPrise> response) {
                                if(response.body() != null) {
                                    items.get(finalI).setPriceDollar(response.body().getQuotes().getUSD().getPrice());
                                    int dollarKRublu = 80;
                                    items.get(finalI).setPriceRubl(response.body().getQuotes().getUSD().getPrice() * dollarKRublu);
                                }else{
                                    Toast.makeText(SplashScreen.this, "Проблемы с интернетом", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<GetPrise> call, Throwable t) {
                                Toast.makeText(SplashScreen.this, "Ошибка загрузки стоимости", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    Toast.makeText(SplashScreen.this, "Фух, закончил", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(SplashScreen.this, "Проблемы с интернетом!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Coins>> call, Throwable t) {
                Toast.makeText(SplashScreen.this, "Ошибка загрузки", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadData() {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString(ITEMS_PROFIL, null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<RecyclerViewItems>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        itemsProfil = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (itemsProfil == null) {
            // if the array list is empty
            // creating a new array list.
            itemsProfil = new ArrayList<>();
        }
    }


}