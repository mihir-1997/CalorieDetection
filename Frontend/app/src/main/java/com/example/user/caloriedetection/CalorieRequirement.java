package com.example.user.caloriedetection;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CalorieRequirement extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayAdapter aa;
    Spinner spinner;
    TextView textView;
    Button button;
    RadioGroup radioGender,radioCriteria;
    RadioButton genderButton,criteriaButton;
    private String selectedItem = " ";
    int gender=0,criteria=0;
    String[] age = {"--Select Age--", "1-10", "11-20", "21-30", "31-40", "41-50", "51-60", "61-70", "71-80"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_requirement);
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
        spinner = findViewById(R.id.spinner2);
        radioGender = findViewById(R.id.genderRadio);
        radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                gender = radioGroup.getCheckedRadioButtonId();
                genderButton = findViewById(gender);
            }
        });
        radioCriteria = findViewById(R.id.criteriaRadio);
        radioCriteria.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                criteria = radioGroup.getCheckedRadioButtonId();
                criteriaButton = findViewById(radioGroup.getCheckedRadioButtonId());
            }
        });

        //Set Spinner items
        aa = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item, age);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        //Spinner listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = adapterView.getItemAtPosition(i).toString();
//                showCalorie(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        textView  =findViewById(R.id.required_calorie_textview);
        button = findViewById(R.id.calculate_calorie);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gender == 0 || criteria == 0 || selectedItem.equals(""))
                    Toast.makeText(CalorieRequirement.this, "Select all fields", Toast.LENGTH_LONG).show();
                else {
                    textView.setVisibility(View.VISIBLE);
                    if ((genderButton.getText().toString()).equals("Male")) {
                        if ((criteriaButton.getText().toString()).equals("Active")) {
                            switch (selectedItem) {
                                case "1-10":
                                    textView.setText("1500");
                                    break;
                                case "11-20":
                                    textView.setText("2500");
                                    break;
                                case "21-30":
                                    textView.setText("3000");
                                    break;
                                case "31-40":
                                    textView.setText("2900");
                                    break;
                                case "41-50":
                                    textView.setText("2800");
                                    break;
                                case "51-60":
                                    textView.setText("2700");
                                    break;
                                case "61-70":
                                    textView.setText("2600");
                                    break;
                                case "71-80":
                                    textView.setText("2450");
                                    break;
                                default:
                                    textView.setText(" ");
                                    break;

                            }
                        } else if ((criteriaButton.getText().toString()).equals("Moderately Active")) {
                            switch (selectedItem) {
                                case "1-10":
                                    textView.setText("1350");
                                    break;
                                case "11-20":
                                    textView.setText("2300");
                                    break;
                                case "21-30":
                                    textView.setText("2700");
                                    break;
                                case "31-40":
                                    textView.setText("2600");
                                    break;
                                case "41-50":
                                    textView.setText("2500");
                                    break;
                                case "51-60":
                                    textView.setText("2400");
                                    break;
                                case "61-70":
                                    textView.setText("2300");
                                    break;
                                case "71-80":
                                    textView.setText("2200");
                                    break;
                                default:
                                    textView.setText(" ");
                                    break;

                            }
                        } else if ((criteriaButton.getText().toString()).equals("Sedentary")) {
                            switch (selectedItem) {
                                case "1-10":
                                    textView.setText("1200");
                                    break;
                                case "11-20":
                                    textView.setText("2300");
                                    break;
                                case "21-30":
                                    textView.setText("2400");
                                    break;
                                case "31-40":
                                    textView.setText("2400");
                                    break;
                                case "41-50":
                                    textView.setText("2200");
                                    break;
                                case "51-60":
                                    textView.setText("2200");
                                    break;
                                case "61-70":
                                    textView.setText("2000");
                                    break;
                                case "71-80":
                                    textView.setText("2000");
                                    break;
                                default:
                                    textView.setText(" ");
                                    break;
                            }
                        }
                    } else if ((genderButton.getText().toString()).equals("Female")) {
                        if ((criteriaButton.getText().toString()).equals("Active")) {
                            switch (selectedItem) {
                                case "1-10":
                                    textView.setText("1800");
                                    break;
                                case "11-20":
                                    textView.setText("2300");
                                    break;
                                case "21-30":
                                    textView.setText("2400");
                                    break;
                                case "31-40":
                                    textView.setText("2200");
                                    break;
                                case "41-50":
                                    textView.setText("2200");
                                    break;
                                case "51-60":
                                    textView.setText("2200");
                                    break;
                                case "61-70":
                                    textView.setText("2000");
                                    break;
                                case "71-80":
                                    textView.setText("2000");
                                    break;
                                default:
                                    textView.setText(" ");
                                    break;

                            }
                        } else if ((criteriaButton.getText().toString()).equals("Moderately Active")) {
                            switch (selectedItem) {
                                case "1-10":
                                    textView.setText("1400");
                                    break;
                                case "11-20":
                                    textView.setText("2000");
                                    break;
                                case "21-30":
                                    textView.setText("2200");
                                    break;
                                case "31-40":
                                    textView.setText("2000");
                                    break;
                                case "41-50":
                                    textView.setText("2000");
                                    break;
                                case "51-60":
                                    textView.setText("1800");
                                    break;
                                case "61-70":
                                    textView.setText("1800");
                                    break;
                                case "71-80":
                                    textView.setText("1800");
                                    break;
                                default:
                                    textView.setText(" ");
                                    break;

                            }
                        } else if ((criteriaButton.getText().toString()).equals("Sedentary")) {
                            switch (selectedItem) {
                                case "1-10":
                                    textView.setText("1300");
                                    break;
                                case "11-20":
                                    textView.setText("1500");
                                    break;
                                case "21-30":
                                    textView.setText("2000");
                                    break;
                                case "31-40":
                                    textView.setText("1800");
                                    break;
                                case "41-50":
                                    textView.setText("1800");
                                    break;
                                case "51-60":
                                    textView.setText("1600");
                                    break;
                                case "61-70":
                                    textView.setText("1600");
                                    break;
                                case "71-80":
                                    textView.setText("1600");
                                    break;
                                default:
                                    textView.setText(" ");
                                    break;
                            }
                        }
                    }
                }
            }
        });

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
            Intent intent = new Intent(CalorieRequirement.this,MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_calorie_requirement) {
            Intent intent = new Intent(CalorieRequirement.this,CalorieRequirement.class);
            startActivity(intent);
        } else if (id == R.id.nav_sources) {
            Intent intent = new Intent(CalorieRequirement.this,Sources.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
