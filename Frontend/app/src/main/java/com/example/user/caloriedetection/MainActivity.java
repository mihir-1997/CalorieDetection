package com.example.user.caloriedetection;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button button,button1;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int BITMAP_SAMPLE_SIZE = 8;
    public static final String GALLERY_DIRECTORY_NAME = "Calorie Detection";
    public static final String IMAGE_EXTENSION = "jpg";
    private static final int RESULT_LOAD_IMAGE = 200;

    private static String imageStoragePath;

    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(!hasPermissions(getApplicationContext(), PERMISSIONS)){
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, PERMISSION_ALL);
        }

        // Floating button for taking pictures
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking availability of the camera
                if (!CameraUtils.isDeviceSupportCamera(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! Your device doesn't support camera",
                            Toast.LENGTH_LONG).show();
                    // will close the app if the device doesn't have camera
                    finish();
                }

                //Check for camera and external storage permission
                if(!hasPermissions(getApplicationContext(), PERMISSIONS)){
                    ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS, PERMISSION_ALL);
                } else {
                    captureImage();
                }
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Button onclick
        button = findViewById(R.id.require);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CalorieRequirement.class);
                startActivity(intent);
            }
        });

        button1 = findViewById(R.id.source);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Sources.class);
                startActivity(intent);
            }
        });
    }

    // Checking for all permissions granted or not
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Capturing Camera Image will launch camera app requested image capture
     */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = CameraUtils.getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        try {
            ExifInterface ei = new ExifInterface(imageStoragePath);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            Bitmap bitmap = CameraUtils.optimizeBitmap(MainActivity.BITMAP_SAMPLE_SIZE,imageStoragePath);
            Bitmap rotateBitmap = null;

            switch (orientation){

                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotateBitmap = rotateImage(bitmap,90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotateBitmap = rotateImage(bitmap,180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotateBitmap = rotateImage(bitmap,270);
                    break;
                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotateBitmap = bitmap;
            }

            FileOutputStream fos = new FileOutputStream(imageStoragePath);
            rotateBitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
        }
        catch (IOException e){

        }
        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    /**
     * Display image from gallery
     */
    private void previewCapturedImage(String imagePath, String item, String calories, String amount_per, String carbohydrates, String cholesterol,
                                      String fat, String pottasium, String sodium, String protein) {
        try {
            Log.d("debug","inside previewcapturedimage");
            Intent intent = new Intent(MainActivity.this,request.class);
            intent.putExtra("data",new String[]{imagePath,item,calories,amount_per,carbohydrates,cholesterol,fat,pottasium,sodium,protein});
            startActivity(intent);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Bitmap rotateImage(Bitmap source, float angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0 ,0, source.getWidth(),source.getHeight(),matrix,true);
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

        if (id == R.id.action_import_picture) {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(i, RESULT_LOAD_IMAGE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 100:
                Log.d("debug","inside switch 100 : camera");
                if (resultCode == RESULT_OK) {
                    // Refreshing the gallery
                    CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);
                    checkForFood(imageStoragePath);
                } else if (resultCode == RESULT_CANCELED) {
                    // user cancelled Image capture
                    Toast.makeText(getApplicationContext(),
                            "User cancelled image capture", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    // failed to capture image
                    Toast.makeText(getApplicationContext(),
                            "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            case 200:
                Log.d("debug","inside switch 200 : import");
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    Log.d("import from gallery",picturePath);
                    cursor.close();
                    checkForFood(picturePath);
                }
                break;
            default:
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    public void checkForFood(String imagePath){

        Log.d("debug","inside checkforfood");
        String url ="http://104.197.87.134:5000/upload";

        //Convert image from imagePath to bitmap for further process
        Bitmap bitmap = CameraUtils.optimizeBitmap(MainActivity.BITMAP_SAMPLE_SIZE, imagePath);

        //Upload image to server for prediction
        uploadBitmap(bitmap,url,imagePath);

    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }


    private void uploadBitmap(final Bitmap bitmap, final  String URL, final String imagepath) {

        Log.d("debug","inside uploadimage");
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Wait while loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //our custom volley request
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, URL,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            progressDialog.dismiss();
                            JSONObject obj = new JSONObject(new String(response.data));
                            if(obj.getString("output").equals("no labels found")){
                                Toast.makeText(MainActivity.this,"Nothing detected",Toast.LENGTH_LONG).show();
                            }
                            else {
                                String item = "";
                                String amount_per = "";
                                String calories = "";
                                String cholesterol = "";
                                String pottasium = "";
                                String sodium = "";
                                String fat = "";
                                String protein = "";
                                String carbohydrates = "";
                                JSONArray output = obj.getJSONArray("output");
                                for(int i=0;i<output.length();i++){
                                    JSONObject object = output.getJSONObject(i);
                                    item = object.getString("item");
                                    amount_per = object.getString("amount_per");
                                    calories = object.getString("calories");
                                    cholesterol = object.getString("cholesterol");
                                    pottasium = object.getString("potassium");
                                    sodium = object.getString("sodium");
                                    fat = object.getString("fat");
                                    protein = object.getString("protein");
                                    carbohydrates = object.getString("carbohydrates");
                                }
                                previewCapturedImage(imagepath,item,calories,amount_per,carbohydrates,cholesterol,fat,pottasium,sodium,protein);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "No network or error in connection", Toast.LENGTH_SHORT).show();
                    }
                }) {

            /*
             * Here we are passing image by renaming it with a unique name
             * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("image", new DataPart(imagename + ".jpg", getFileDataFromDrawable(bitmap)));
                return params;
            }

        };

        //Prevent from making multiple requests
        volleyMultipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_calorie_requirement) {
            Intent intent = new Intent(MainActivity.this,CalorieRequirement.class);
            startActivity(intent);
        } else if (id == R.id.nav_sources) {
            Intent intent = new Intent(MainActivity.this,Sources.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
