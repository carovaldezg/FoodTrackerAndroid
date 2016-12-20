package com.example.caro.foodtrackerandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.caro.foodtrackerandroid.R.drawable.addbutton;

public class MealListViewMainActivity extends AppCompatActivity {
    ArrayList<Meal> mealItems;
    ListView mealListView;
    ArrayAdapter<Meal> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list_view_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mealItems = new ArrayList<>();
        addMealItems();

        mealListView  = (ListView) findViewById(R.id.mealsListView);
        adapter = new propertyArrayAdapter(this, 0, mealItems);
        mealListView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startAddActivity();
            }
        });
    }

    public void startAddActivity()
    {
        Intent i = new Intent(getApplicationContext(), AddMealMainActivity.class);
        startActivity(i);
    }

    public void addMealItems()
    {
        RatingBar r1 = new RatingBar(this);
        r1.setRating(2);
        Meal meal1 = new Meal ("capresse salad", r1, "meal1");
        mealItems.add(meal1);

        RatingBar r2 = new RatingBar(this);
        r2.setRating(4);
        Meal meal2 = new Meal ("chicken & potatoes", r2, "meal2");
        mealItems.add(meal2);

        RatingBar r3 = new RatingBar(this);
        r3.setRating(3);
        Meal meal3 = new Meal ("tasty pasta", r3, "meal3");
        mealItems.add(meal3);

      /*  RatingBar r4 = new RatingBar(this);
        r4.setRating(5);
        Meal meal4 = new Meal ("capresse salad", r4, "meal1");
        mealItems.add(meal4);

        RatingBar r5 = new RatingBar(this);
        r5.setRating(2);
        Meal meal5 = new Meal ("chicken & potatoes", r5, "meal2");
        mealItems.add(meal5);

        RatingBar r6 = new RatingBar(this);
        r6.setRating(3);
        Meal meal6 = new Meal ("tasty pasta", r6, "meal3");
        mealItems.add(meal6);
        RatingBar r7 = new RatingBar(this);
        r7.setRating(2);
        Meal meal7 = new Meal ("capresse salad", r7, "meal1");
        mealItems.add(meal7);

        RatingBar r8 = new RatingBar(this);
        r8.setRating(4);
        Meal meal8 = new Meal ("chicken & potatoes", r8, "meal2");
        mealItems.add(meal2);

        RatingBar r9 = new RatingBar(this);
        r9.setRating(5);
        Meal meal9 = new Meal ("tasty pasta", r9, "meal3");
        mealItems.add(meal3);
*/

    }




    class propertyArrayAdapter extends ArrayAdapter<Meal>{

        private Context context;
        private List<Meal> mealList;

        //constructor, call on creation
        public propertyArrayAdapter(Context context, int resource, ArrayList<Meal> objects) {
            super(context, resource, objects);

            this.context = context;
            this.mealList = objects;
        }

        //called when rendering the list
        public View getView(int position, View convertView, ViewGroup parent) {

            //get the property we are displaying
            Meal meal = mealList.get(position);

            //get the inflater and inflate the XML layout for each item
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.meal_item_list_view, null);


            TextView mealname = (TextView) view.findViewById(R.id.mealNameTextView);
            RatingBar ratingbar = (RatingBar) view.findViewById(R.id.ratingBarListItem);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);

            String mealnamestring = meal.getMealName();
            mealname.setText(mealnamestring);

            RatingBar ratingbarstars = meal.getMealRating();

            ratingbar.setRating(ratingbarstars.getRating());



            //get the image associated with this property
            int imageID = context.getResources().getIdentifier(meal.getMealImage(), "drawable", context.getPackageName());
            imageView.setImageResource(imageID);

            return view;
        }
    }
}
