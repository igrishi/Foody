package com.rishi.foody;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rishi.foody.ViewModel.FoodViewModel;
import com.rishi.foody.adapters.RecyclerViewAdapter;
import com.rishi.foody.models.FoodModel;

public class MainActivity extends AppCompatActivity implements DisplayMeal {

    SearchView search;
    RecyclerView recyclerView;
    ProgressBar progress;
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter adapter;
    FoodViewModel viewModel;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search_food);
        recyclerView = findViewById(R.id.recycler_view);
        progress = findViewById(R.id.progess_bar);
        fab = findViewById(R.id.favourites);
        viewModel = ViewModelProviders.of(this).get(FoodViewModel.class);

        viewModel.init();

        viewModel.getMeals().observe(this, foodModels -> adapter.notifyDataSetChanged());

        viewModel.getIsFetching().observe(this, aBoolean -> {
            if (aBoolean)
                progress.setVisibility(View.VISIBLE);
            else
                progress.setVisibility(View.GONE);
        });

        init_recycler_view();
        Query();

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, Favourite.class);
            startActivity(intent);
        });
    }

    void init_recycler_view() {
        adapter = new RecyclerViewAdapter(this, viewModel.getMeals().getValue(), this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    void Query() {
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.Query(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.length() == 0)
                    viewModel.Query(newText);
                return true;
            }
        });
    }

    @Override
    public void display(FoodModel model) {
        Intent intent = new Intent(this, CookInstructions.class);
        intent.putExtra("meal", model);
        startActivity(intent);
    }
}