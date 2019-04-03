package com.example.user.caloriedetection;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Sources extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner spinner;
    LinearLayout linearLayout;
    private String selectedItem = " ";
    String[] nutrients = {"--Select Nutrient--", "Carbohydrates", "Healthy Fats", "Protein", "Vitamin A", "Vitamin B",
            "Vitamin D", "Vitamin E", "Vitamin K", "Folic Acid", "Calcium", "Iron", "Zinc", "Chromium"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Spinner
        spinner = findViewById(R.id.spinner1);

        //Set Spinner items
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item, nutrients);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        //Spinner listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = adapterView.getItemAtPosition(i).toString();
                showSources(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    String[] sources = new String[]{};

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void showSources(String nutrient) {

        linearLayout = findViewById(R.id.linear_layout_sources);
        linearLayout.removeAllViews();

        switch (nutrient) {
            case "Carbohydrates":
                sources = new String[]{"Whole Grains","Fruits","Vegetables"};
                break;
            case "Healthy Fats":
                sources = new String[]{"Avocados","Olives","Nuts","Fish","Oils(Olive Oil and Canora Oil)"};
                break;
            case "Protein":
                sources = new String[]{"Soy","Dairy and Nuts","Lean Beef and Pork","Chicken and Turkey","Tofu"};
                break;
            case "Vitamin A":
                sources = new String[]{"Carrots like orange food","Sweet Potato","Centaloupe Melon"};
                break;
            case "Vitamin B":
                sources = new String[]{"Whole unprocessed food especially whole grains","Potato","Lentils","Beans","Chili pepper","Banana"};
                break;
            case "Vitamin C":
                sources = new String[]{"Oranges","Guava","Red and Green peppers","Kiwi","Grapefruits","Brussels Sprouts","Cantaloupe","Strawberries"};
                break;
            case "Vitamin D":
                sources = new String[]{"Natural Sunlight","Eggs","Fish","Mushrooms"};
                break;
            case "Vitamin E":
                sources = new String[]{"Almond","Nuts","Sunflower Seeds","Tomatoes"};
                break;
            case "Vitamin K":
                sources = new String[]{"Kale","Spinach","Brussels Sprout","Broccoli"};
                break;
            case "Folic Acid":
                sources = new String[]{"Dark leafy greens","Asparagus","Broccoli","Beans","Peas","Lentils","Seeds","Nuts","Cauliflower","Beets","Corn","Citrus Fruits"};
                break;
            case "Calcium":
                sources = new String[]{"Dairy Products like yogurt","Cheese","Milk","Tofu","Black Molasses"};
                break;
            case "Iron":
                sources = new String[]{"Soybeans","Cereal","Pumpkin","Beans","Seeds","Lentils","Spinach"};
                break;
            case "Zinc":
                sources = new String[]{"Sea Food","Beans","Black Chocolate","Cashew","Spinach"};
                break;
            case "Chromium":
                sources = new String[]{"Whole Grains","Fresh Vegetables","Herbs"};
                break;
            default:
                break;
        }

        int length_sources = sources.length;
        linearLayout = findViewById(R.id.linear_layout_sources);
        for(int i=0;i<length_sources;i++){
            TextView textView = new TextView(this);
            textView.setText(Integer.toString(i+1)+". "+sources[i]);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
            textView.setPadding(20,5,5,5);
            textView.setBackground(ContextCompat.getDrawable(this,R.drawable.table_border));
            linearLayout.addView(textView);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_import_picture) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(Sources.this,MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_calorie_requirement) {
            Intent intent = new Intent(Sources.this,CalorieRequirement.class);
            startActivity(intent);
        } else if (id == R.id.nav_sources) {
            Intent intent = new Intent(Sources.this,Sources.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
