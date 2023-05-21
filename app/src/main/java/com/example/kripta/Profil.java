package com.example.kripta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.kripta.adapters.RecyclerViewAdapter;
import com.example.kripta.adapters.RecyclerViewProfilAdapter;

public class Profil extends AppCompatActivity {

    ImageView back;
    RecyclerView recycler;
    RecyclerViewProfilAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerViewProfilAdapter(SplashScreen.itemsProfil, this);
        recycler.setAdapter(adapter);
    }
}