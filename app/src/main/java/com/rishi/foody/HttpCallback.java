package com.rishi.foody;

import com.rishi.foody.models.FoodModel;

import java.util.List;

public interface HttpCallback {
    void HttpResponse(List<FoodModel> list);
}
