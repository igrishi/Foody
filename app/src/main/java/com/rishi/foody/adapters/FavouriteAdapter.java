package com.rishi.foody.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rishi.foody.DisplayFavouriteMeal;
import com.rishi.foody.Favourite;
import com.rishi.foody.R;
import com.rishi.foody.models.FoodModel;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    List<FoodModel> list;
    Favourite fav;
    DisplayFavouriteMeal display;

    public FavouriteAdapter(List<FoodModel> list, Favourite fav) {
        this.list = list;
        this.fav = fav;
        this.display = fav;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodrow, parent, false);
        return new ViewHolder(v, display);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(fav).load(list.get(position).getImage_url()).into(holder.image);
        holder.name.setText(list.get(position).getName());
        holder.area.setText(list.get(position).getArea());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView area;
        ImageView image;
        DisplayFavouriteMeal display;

        public ViewHolder(@NonNull View itemView, DisplayFavouriteMeal displayMeal) {
            super(itemView);
            this.name = itemView.findViewById(R.id.meal_name);
            this.area = itemView.findViewById(R.id.area);
            this.image = itemView.findViewById(R.id.thumbnail);
            this.display = displayMeal;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            display.display(list.get(pos));
        }
    }
}
