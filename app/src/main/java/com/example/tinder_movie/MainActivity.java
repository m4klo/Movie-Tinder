package com.example.tinder_movie;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    String API_URL  = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?offset=50&type=movie&limit=100";
    ProgressDialog progressDialog;
    private arrayAdapter arrayAdapter;

    private FirebaseAuth mAuth;

    private String CurrentUId;

    private DatabaseReference usersDb;

    ListView listView;
    List<movies> rowitems;

    public MainActivity() {
    }

    public void loguoutUser(View view) {
        mAuth.signOut();
        Intent intent = new Intent (MainActivity.this, ChooseLoginRegistrationActivity.class);
        startActivity(intent);
        finish();
        return;
    }

    public class syncData extends AsyncTask<String, String, String> {

        private String data;


      @Override
      protected void onPreExecute() {
          super.onPreExecute();
          progressDialog = new ProgressDialog(MainActivity.this);
          progressDialog.setMessage("Please Wait");
          progressDialog.setCancelable(false);
          progressDialog.show();
      }

      @Override
      protected String doInBackground(String... strings) {
          StringBuilder builder = new StringBuilder();

          try {
              URL url = new URL(API_URL);
              HttpURLConnection connection = (HttpURLConnection) url.openConnection();
              connection.setRequestMethod("GET");
              connection.setRequestProperty("X-RapidAPI-Host", "unogs-unogs-v1.p.rapidapi.com");
              connection.setRequestProperty("X-RapidAPI-Key", "ae7b155a09msh17af395a573014dp1dc75bjsn7cb2bcd9773b");
              connection.setConnectTimeout(5000);
              connection.setReadTimeout(5000);

              BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

              while(true)
              {
                  String readLine = reader.readLine();

                  String data = readLine;

                  if(data == null)
                  {
                      break;
                  }
                  builder.append(data);
              }
          }
          catch (Exception e) {
              e.printStackTrace();
          }
          return builder.toString();
      }

      @Override
      protected void onPostExecute(String s) {
          super.onPostExecute(s);
          progressDialog.dismiss();
          try {
              JSONObject jsonObject = new JSONObject(s);
              JSONArray jsonArray;
              jsonArray = jsonObject.getJSONArray("results");
              for (int i = 0; i < jsonArray.length(); i++) {
                  movies movie = new movies();
                  JSONObject results = jsonArray.getJSONObject(i);
                  movie.setTitle(results.getString("title"));
                  movie.setNetflixId(results.getInt("netflix_id"));
                  rowitems.add(movie);
              }
          } catch(JSONException e){
              e.printStackTrace();
          }
      }
  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersDb = FirebaseDatabase.getInstance().getReference().child("Users");

        mAuth = FirebaseAuth.getInstance();
        CurrentUId = mAuth.getCurrentUser().getUid();

        rowitems = new ArrayList<movies>();
        new syncData().execute();
        movies movie=new movies();
        movie.setTitle("Swipe left or right");
        rowitems.add(movie);
        arrayAdapter = new arrayAdapter(this, R.layout.item, rowitems);
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");
                rowitems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {

                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float v) {

            }

        });

        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        }



}