package com.example.kripta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kripta.models.RecyclerViewItems;

public class Coin extends AppCompatActivity {

    TextView name, description, ticker, price_dollar, price_rubl;
    ImageView profil;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.activity_coin);

        profil = (ImageView) findViewById(R.id.profil);
        name = (TextView) findViewById(R.id.name);
        description = (TextView) findViewById(R.id.description);
        ticker = (TextView) findViewById(R.id.ticker);
        price_dollar = (TextView) findViewById(R.id.price_dollar);
        price_rubl = (TextView) findViewById(R.id.price_rubl);

        RecyclerViewItems item = SplashScreen.items.get(intent.getExtras().getInt("index"));

        name.setText(item.getName());
        description.setText(item.getDescription());
        ticker.setText(item.getTicker());
        price_dollar.setText(item.getPriceDollar()+"$");
        price_rubl.setText(item.getPriceRubl()+"₽");

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Coin.this, Profil.class));
                finish();
            }
        });
    }
}