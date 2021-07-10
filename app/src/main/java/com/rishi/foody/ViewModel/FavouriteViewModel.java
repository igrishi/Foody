package com.rishi.foody.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rishi.foody.models.FoodModel;

import java.util.ArrayList;
import java.util.List;

public class FavouriteViewModel {
    public static FavouriteViewModel instance;
    static MutableLiveData<List<FoodModel>> favourite;
    String TAG = "FavouriteViewModel";

    public static FavouriteViewModel getInstance() {
        if (instance == null) {
            instance = new FavouriteViewModel();
            init();
        }
        return instance;
    }

    public LiveData<List<FoodModel>> getFavourite() {
        return favourite;
    }

    public void addMeal(FoodModel model) {
        List<FoodModel> curr = favourite.getValue();
        curr.add(model);
        favourite.postValue(curr);
    }

    public void removeMeal(FoodModel model) {
        List<FoodModel> curr = favourite.getValue();
        FoodModel toremove = null;
        for (FoodModel food : curr) {
            if (food.getId().equals(model.getId())) {
                toremove = food;
                break;
            }
        }
        if (toremove != null) curr.remove(toremove);
        favourite.postValue(curr);
    }

    public boolean isFavourite(FoodModel model) {
        List<FoodModel> curr = favourite.getValue();
        for (FoodModel food : curr) {
            if (food.getId().equals(model.getId())) return true;
        }
        return false;
    }

    public static void init() {
        List<FoodModel> list = new ArrayList<>();
        favourite = new MutableLiveData<>();
        favourite.setValue(list);
    }
}
