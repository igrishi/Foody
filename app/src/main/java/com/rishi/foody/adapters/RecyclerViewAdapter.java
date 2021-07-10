package com.rishi.foody.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rishi.foody.DisplayMeal;
import com.rishi.foody.R;
import com.rishi.foody.models.FoodModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    List<FoodModel> food;
    Context context;
    DisplayMeal displaymeal;

    public RecyclerViewAdapter(Context context, List<FoodModel> food, DisplayMeal displaymeal) {
        this.food = food;
        this.context = context;
        this.displaymeal = displaymeal;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodrow, parent, false);
        return new ViewHolder(v, displaymeal);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = food.get(position).getName();
        String area = food.get(position).getArea();
        String image_url = food.get(position).getImage_url();

        holder.name.setText(name);
        holder.area.setText(area);
        Glide.with(context).load(image_url).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return food.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView area;
        ImageView image;
        DisplayMeal displaymeal;

        public ViewHolder(@NonNull View itemView, DisplayMeal displaymeal) {
            super(itemView);
            this.name = itemView.findViewById(R.id.meal_name);
            this.area = itemView.findViewById(R.id.area);
            this.image = itemView.findViewById(R.id.thumbnail);
            this.displaymeal = displaymeal;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            displaymeal.display(food.get(pos));
        }
    }
}
