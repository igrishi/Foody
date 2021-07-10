package com.rishi.foody.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rishi.foody.models.FoodModel;
import com.rishi.foody.repository.FoodRepository;

import java.util.ArrayList;
import java.util.List;

public class FoodViewModel extends ViewModel {
    private MutableLiveData<List<FoodModel>> meals;
    private FoodRepository repo;
    private MutableLiveData<Boolean> isFetching = new MutableLiveData<>();
    private String TAG = "FoodViewModel";

    public void init() {
        if (meals == null) {
            List<FoodModel> temp = new ArrayList<>();
            meals = new MutableLiveData<>();
            meals.setValue(temp);

            isFetching.setValue(true);

            repo = FoodRepository.getInstance();
            repo.getRandom_meal(list -> {
                List<FoodModel> curr = meals.getValue();
                curr.clear();
                curr.addAll(list);
                meals.postValue(curr);

                isFetching.setValue(false);
            });
        }
    }

    public void Query(String str) {
        if (meals != null) {
            List<FoodModel> curr = meals.getValue();
            curr.clear();

            isFetching.setValue(true);
            if (str == null || str.length() == 0) {
                repo.getRandom_meal(list -> {
                    curr.addAll(list);
                    meals.postValue(curr);
                    isFetching.setValue(false);
                });
            } else {
                repo.getSearchResult(str, list -> {
                    curr.addAll(list);
                    meals.postValue(curr);
                    isFetching.setValue(false);
                });
            }
        }
    }

    public LiveData<List<FoodModel>> getMeals() {
        return meals;
    }

    public LiveData<Boolean> getIsFetching() {
        return isFetching;
    }
}
