package com.example.tinder_movie;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends Activity {

    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;

    String API_URL  = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?offset=50&type=movie&limit=1";

    //  public static String parse(String responseBody) {
  //      JSONArray albums = new JSONArray(responseBody);
   //     for(int i=0; i<albums.length(); i++)
   //     {
  //          JSONObject album =albums.getJSONObject(i);
   //         String title= album.getString("title");
   //         System.out.println(title);
   //     }

  //  }
  public class syncData extends AsyncTask<String, String, String> {

      private Object URL;

      @Override
      protected void onPreExecute() {
          super.onPreExecute();
      }

      @Override
      protected String doInBackground(String... strings) {
          StringBuilder builder = new StringBuilder();

          try {
              URL=new URL(API_URL);
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

  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new syncData().execute();

        al = new ArrayList<>();
        al.add("Apocalypse Now");
        al.add("Saving Private Ryan");
        al.add("Pirates of the Carribean");
        al.add("Twiligth");
        al.add("Harry Potter");
        al.add("Matrix");
        al.add("Forest Gump");
        al.add("");

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al );

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");
                al.remove(0);
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
                al.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
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