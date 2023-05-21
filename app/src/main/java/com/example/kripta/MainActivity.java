package com.example.kripta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kripta.API.KriptaApi;
import com.example.kripta.adapters.RecyclerViewAdapter;
import com.example.kripta.models.Coins;
import com.example.kripta.models.GetInfo;
import com.example.kripta.models.GetPrise;
import com.example.kripta.models.RecyclerViewItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    ImageView profil;
    EditText search;
    RecyclerViewAdapter adapter;
    List<RecyclerViewItems> searchItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        profil = (ImageView) findViewById(R.id.profil);
        search = (EditText) findViewById(R.id.search);

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Profil.class));
            }
        });

        adapter = new RecyclerViewAdapter(SplashScreen.items, this);
        recycler.setAdapter(adapter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchItems = new ArrayList<>();
                for(int i = 0;i < SplashScreen.items.size();i++){
                    if(SplashScreen.items.get(i).getName().toLowerCase().indexOf(s.toString().toLowerCase()) != -1 ||
                            SplashScreen.items.get(i).getTicker().toLowerCase().indexOf(s.toString().toLowerCase()) != -1) {
                            searchItems.add(SplashScreen.items.get(i));
                        }
                }
                if(s.equals("")) searchItems = SplashScreen.items;
                adapter = new RecyclerViewAdapter(searchItems, MainActivity.this);
                recycler.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}