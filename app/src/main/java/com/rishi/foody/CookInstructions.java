package com.rishi.foody;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rishi.foody.ViewModel.FavouriteViewModel;
import com.rishi.foody.models.FoodModel;

public class CookInstructions extends AppCompatActivity {

    TextView name;
    TextView category;
    TextView area;
    TextView instructions;
    TextView ingredients;
    ImageView image;
    FloatingActionButton fav;
    FavouriteViewModel favmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_instructions);

        Intent intent = getIntent();
        FoodModel foodmodel = intent.getParcelableExtra("meal");

        image = findViewById(R.id.meal_image);
        name = findViewById(R.id.meal_name);
        area = findViewById(R.id.meal_area);
        category = findViewById(R.id.meal_cat);
        instructions = findViewById(R.id.instructions);
        ingredients = findViewById(R.id.ingredients);
        fav = findViewById(R.id.fav);

        favmodel = FavouriteViewModel.getInstance();
        setValues(foodmodel);

        fav.setOnClickListener(view -> {
            if (favmodel.isFavourite(foodmodel)) {
                Toast.makeText(this, "Meal removed from favourites", Toast.LENGTH_SHORT).show();
                favmodel.removeMeal(foodmodel);
                fav.getDrawable().setTint(getResources().getColor(R.color.black));
            } else {
                Toast.makeText(this, "Meal added to favourites", Toast.LENGTH_SHORT).show();
                favmodel.addMeal(foodmodel);
                fav.getDrawable().setTint(getResources().getColor(R.color.pink));
            }
        });
    }

    public void setValues(FoodModel model) {
        Glide.with(this).load(model.getImage_url()).into(image);
        name.setText(model.getName());
        category.setText(model.getCategory());
        area.setText(model.getArea());
        instructions.setText(model.getInstructions());

        String ingredient = "";
        int cnt = 1;
        for (String s : model.getIngredients()) {
            ingredient = ingredient + cnt + ". " + s + "\n";
            cnt++;
        }
        ingredients.setText(ingredient);
        if (favmodel.isFavourite(model)) {
            fav.getDrawable().setTint(getResources().getColor(R.color.pink));
        } else {
            fav.getDrawable().setTint(getResources().getColor(R.color.black));
        }
    }
}