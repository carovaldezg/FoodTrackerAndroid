package com.example.caro.foodtrackerandroid;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;
import android.widget.RatingBar;

/**
 * Created by caro on 8/12/16.
 */

public class Meal {

    String mealphoto;
    String mealname;
    RatingBar rating;

    public Meal(String mealname, RatingBar rating, String mealphoto){
        this.mealphoto = mealphoto;
        this.mealname = mealname;
        this.rating = rating;

    }

    public String getMealImage(){

         return mealphoto;
    }

    public RatingBar getMealRating(){
        return rating;
    }

    public String getMealName(){
        return mealname;
    }


}
