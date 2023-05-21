package com.example.kripta.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kripta.Coin;
import com.example.kripta.R;
import com.example.kripta.SplashScreen;
import com.example.kripta.models.RecyclerViewItems;
import com.google.gson.Gson;

import java.util.List;

class RecyclerViewProfilHolder extends RecyclerView.ViewHolder{

    TextView name, description, ticker, price_dollar, price_rubl;
    ImageView delete;
    ConstraintLayout constr;

    public RecyclerViewProfilHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        description = (TextView) itemView.findViewById(R.id.description);
        ticker = (TextView) itemView.findViewById(R.id.ticker);
        price_dollar = (TextView) itemView.findViewById(R.id.price_dollar);
        price_rubl = (TextView) itemView.findViewById(R.id.price_rubl);
        delete = (ImageView) itemView.findViewById(R.id.delete);
        constr = (ConstraintLayout) itemView.findViewById(R.id.constr);
    }
}

public class RecyclerViewProfilAdapter extends RecyclerView.Adapter<RecyclerViewProfilHolder>{

    List<RecyclerViewItems> items;
    Context context;
    Intent intent;

    public RecyclerViewProfilAdapter(List<RecyclerViewItems> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewProfilHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_profil_item, parent, false);
        return new RecyclerViewProfilHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewProfilHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        if(items.get(position).getDescription().length() > 15)
            holder.description.setText(items.get(position).getDescription().substring(0,15) + "...");
        else
            holder.description.setText(items.get(position).getDescription());
        holder.ticker.setText(items.get(position).getTicker());
        holder.price_dollar.setText(items.get(position).getPriceDollar()+"$");
        holder.price_rubl.setText(items.get(position).getPriceRubl()+"₽");

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.getAdapterPosition() != -1) {
                    items.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    notifyItemRangeChanged(holder.getAdapterPosition(), items.size());
                    saveData();
                }
            }
        });

        holder.constr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, Coin.class);
                intent.putExtra("index", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void saveData() {
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        SharedPreferences sharedPreferences = context.getSharedPreferences(SplashScreen.FILE_NAME, MODE_PRIVATE);

        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        String json = gson.toJson(SplashScreen.itemsProfil);

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString(SplashScreen.ITEMS_PROFIL, json);

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();

    }
}
