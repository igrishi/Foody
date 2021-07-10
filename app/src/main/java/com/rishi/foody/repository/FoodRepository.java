package com.rishi.foody.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.rishi.foody.HttpCallback;
import com.rishi.foody.models.FoodModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/*
Singleton Pattern
 */
public class FoodRepository {
    private static FoodRepository instance;
    private final String TAG = "FoodRepository";

    public static FoodRepository getInstance() {
        if (instance == null) {
            instance = new FoodRepository();
        }
        return instance;
    }

    public void getRandom_meal(HttpCallback callback) {
        MutableLiveData<List<FoodModel>> data = new MutableLiveData<>();
        APIcall("https://www.themealdb.com/api/json/v1/1/random.php", callback);
    }

    public void getSearchResult(String str, HttpCallback callback) {
        MutableLiveData<List<FoodModel>> data = new MutableLiveData<>();
        APIcall("https://www.themealdb.com/api/json/v1/1/search.php?s=" + str, callback);
    }

    public void APIcall(String url, HttpCallback callback) {
        List<FoodModel> foods = new ArrayList<>();
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (!response.isNull("meals")) {
                        JSONArray arr = response.getJSONArray("meals");
                        if (arr != null) {
                            for (int i = 0; i < arr.length(); ++i) {
                                JSONObject j = arr.getJSONObject(i);
                                String name = j.getString("strMeal");
                                String id = j.getString("idMeal");
                                String area = j.getString("strArea");
                                String category = j.getString("strCategory");
                                String image_url = j.getString("strMealThumb");
                                String instructions = j.getString("strInstructions");
                                List<String> ingredients = new ArrayList<>();
                                for (int k = 1; k <= 20; ++k) {
                                    String temp = j.getString("strIngredient" + k);
                                    if (temp != null && temp.length() != 0)
                                        ingredients.add(temp);
                                    else break;
                                }
                                FoodModel f = new FoodModel(name, id, area, category, image_url, instructions, ingredients);
                                foods.add(f);
                            }
                        }
                    }
                    callback.HttpResponse(foods);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d(TAG, "onFailure: " + statusCode);
            }
        });
    }
}
