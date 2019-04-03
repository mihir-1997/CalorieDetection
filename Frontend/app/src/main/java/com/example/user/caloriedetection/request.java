package com.example.user.caloriedetection;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class request extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imageView;
    TextView itemName;
    TextView calories;
    TextView amount_per;
    TextView carbohydrates;
    TextView fat;
    TextView pottaium;
    TextView sodium;
    TextView cholesterol;
    TextView protein;
    Typeface custom_font;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        custom_font = ResourcesCompat.getFont(getApplicationContext(),R.font.grandhotel_regular);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Get imagePath from previous intent(or activity) i.e. MainActivity
        Intent intent = getIntent();
        String[] data = intent.getStringArrayExtra("data");

        //Convert image from imagePath to bitmap for further process
        Bitmap bitmap = CameraUtils.optimizeBitmap(MainActivity.BITMAP_SAMPLE_SIZE, data[0]);
        imageView = findViewById(R.id.request_imageView);
        imageView.setImageBitmap(bitmap);

        itemName = findViewById(R.id.item);
        itemName.setTypeface(custom_font);
        String item = data[1].substring(0,1).toUpperCase() + data[1].substring(1);
        itemName.setText(item);

        amount_per = findViewById(R.id.amount_per);
        amount_per.setText(data[3]);
        calories = findViewById(R.id.calories);
        calories.setText(data[2]);
        carbohydrates = findViewById(R.id.carbohydrates);
        carbohydrates.setText(data[4]);
        cholesterol = findViewById(R.id.cholesterol);
        cholesterol.setText(data[5]);
        fat = findViewById(R.id.fat);
        fat.setText(data[6]);
        pottaium= findViewById(R.id.pottasium);
        pottaium.setText(data[7]);
        sodium = findViewById(R.id.sodium);
        sodium.setText(data[8]);
        protein = findViewById(R.id.protein);
        protein.setText(data[9]);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(request.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_calorie_requirement) {
            Intent intent = new Intent(request.this, CalorieRequirement.class);
            startActivity(intent);
        } else if (id == R.id.nav_sources) {
            Intent intent = new Intent(request.this, Sources.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}