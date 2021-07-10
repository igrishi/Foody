package com.rishi.foody;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rishi.foody.ViewModel.FavouriteViewModel;
import com.rishi.foody.adapters.FavouriteAdapter;
import com.rishi.foody.models.FoodModel;

public class Favourite extends AppCompatActivity implements DisplayFavouriteMeal {

    RecyclerView recyclerView;
    FavouriteAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    FavouriteViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        recyclerView = findViewById(R.id.recycler_view2);
        model = FavouriteViewModel.getInstance();
        model.getFavourite().observe(this, foodModels -> adapter.notifyDataSetChanged());
        init_recycler_view();


    }

    private void init_recycler_view() {
        adapter = new FavouriteAdapter(model.getFavourite().getValue(), this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void display(FoodModel model) {
        Intent intent = new Intent(this, CookInstructions.class);
        intent.putExtra("meal", model);
        startActivity(intent);
    }
}