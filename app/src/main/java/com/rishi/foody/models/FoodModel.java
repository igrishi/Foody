package com.rishi.foody.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class FoodModel implements Parcelable {
    private String name;
    private String id;
    private String area;
    private String category;
    private String image_url;
    private String instructions;
    private List<String> ingredients;

    public FoodModel(String name, String id, String area, String category, String image_url, String instructions, List<String> ingredients) {
        this.name = name;
        this.id = id;
        this.area = area;
        this.category = category;
        this.image_url = image_url;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    protected FoodModel(Parcel in) {
        name = in.readString();
        id = in.readString();
        area = in.readString();
        category = in.readString();
        image_url = in.readString();
        instructions = in.readString();
        ingredients = in.createStringArrayList();
    }

    public static final Creator<FoodModel> CREATOR = new Creator<FoodModel>() {
        @Override
        public FoodModel createFromParcel(Parcel in) {
            return new FoodModel(in);
        }

        @Override
        public FoodModel[] newArray(int size) {
            return new FoodModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(id);
        parcel.writeString(area);
        parcel.writeString(category);
        parcel.writeString(image_url);
        parcel.writeString(instructions);
        parcel.writeStringList(ingredients);
    }
}
