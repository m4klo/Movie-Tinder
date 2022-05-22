package com.example.tinder_movie;

import static com.example.tinder_movie.GenreActivity.setURL;
import static com.example.tinder_movie.GenreActivity.setURLorder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {

    ProgressDialog progressDialog;

    private arrayAdapter arrayAdapter;

    private FirebaseAuth mAuth;

    private String CurrentUId;

    private DatabaseReference usersDb;

    ListView listView;
    List<movies> rowitems;

    public MainActivity() {
    }

    public void logoutUser(View view) {
        mAuth.signOut();
        Intent intent = new Intent (MainActivity.this, ChooseLoginRegistrationActivity.class);
        startActivity(intent);
        finish();
        return;
    }
    public void openLibrary(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
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
              URL url = new URL(GenreActivity.getURL());
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
          CurrentUId = mAuth.getCurrentUser().getUid();
          DatabaseReference currentUserright = FirebaseDatabase.getInstance("https://movie-tinder-f5289-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Users").child(CurrentUId).child("right");
          DatabaseReference currentUserleft = FirebaseDatabase.getInstance("https://movie-tinder-f5289-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Users").child(CurrentUId).child("left");
          progressDialog.dismiss();
          try {
              JSONObject jsonObject = new JSONObject(s);
              JSONArray jsonArray;
              jsonArray = jsonObject.getJSONArray("results");
              for (int i = 0; i < jsonArray.length(); i++) {
                  movies movie = new movies();
                  JSONObject results = jsonArray.getJSONObject(i);
                  String strtitle = results.getString("title").replaceAll("[#$.]", " ");
                  movie.setTitle(strtitle);
                  currentUserright.child(movie.getTitle()).addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                          if (!dataSnapshot.exists()) {
                              currentUserleft.child(movie.getTitle()).addListenerForSingleValueEvent(new ValueEventListener() {
                                  @Override
                                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                      if (!dataSnapshot.exists()) {
                                          try {
                                              movie.setNetflixId(results.getString("netflix_id"));
                                              movie.setImgURL(results.getString("img"));
                                          } catch (JSONException e) {
                                              e.printStackTrace();
                                          }
                                          rowitems.add(movie);
                                      }
                                  }

                                  @Override
                                  public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

                                  }
                              });
                          }
                          else{
                              currentUserright.child(movie.getTitle()).addListenerForSingleValueEvent(new ValueEventListener() {
                                  @Override
                                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                      if (!dataSnapshot.exists()) {
                                          try {
                                              movie.setNetflixId(results.getString("netflix_id"));
                                              movie.setImgURL(results.getString("img"));
                                          } catch (JSONException e) {
                                              e.printStackTrace();
                                          }
                                          rowitems.add(movie);
                                      }
                                  }

                                  @Override
                                  public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

                                  }
                              });
                          }
                      }

                      @Override
                      public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

                      }
                  });
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
        movies movie=new movies();
        movie.setTitle("Swipe left or right!");
        movie.setNetflixId("0");
        rowitems.add(movie);
        new syncData().execute();
        arrayAdapter = new arrayAdapter(this, R.layout.item, rowitems);
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                String netflixid = rowitems.get(0).getNetflixId();
                String title = rowitems.get(0).getTitle();
                String img = rowitems.get(0).getImgURL();
                DatabaseReference currentUserDb = FirebaseDatabase.getInstance("https://movie-tinder-f5289-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Users").child(CurrentUId).child("left").child(title);
                Map movieInfo = new HashMap<>();
                movieInfo.put("title", title);
                movieInfo.put("netflixid", netflixid);
                movieInfo.put("img", img);
                currentUserDb.updateChildren(movieInfo);
                rowitems.remove(0);
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                String netflixid = rowitems.get(0).getNetflixId();
                String title = rowitems.get(0).getTitle();
                DatabaseReference currentUserDb = FirebaseDatabase.getInstance("https://movie-tinder-f5289-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Users").child(CurrentUId).child("right").child(title);
                Map movieInfo = new HashMap<>();
                movieInfo.put("title", title);
                movieInfo.put("netflixid", netflixid);
                currentUserDb.updateChildren(movieInfo);
                rowitems.remove(0);
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                if(rowitems.isEmpty()) {
                    setURLorder();
                    setURL();
                    finish();
                    startActivity(getIntent());
                }
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