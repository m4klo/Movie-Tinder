package com.example.tinder_movie;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.*;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;
    private static HttpURLConnection connection;

    public static void http() throws IOException {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();


        String url = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?offset=50&type=movie&limit=1";

        URL obj = new URL(url);
        connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        connection.setRequestProperty("X-RapidAPI-Host", "unogs-unogs-v1.p.rapidapi.com");
        connection.setRequestProperty("X-RapidAPI-Key", "ae7b155a09msh17af395a573014dp1dc75bjsn7cb2bcd9773b");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line=reader.readLine())!= null) {
            responseContent.append(line);
        }
        reader.close();

        System.out.println(responseContent.toString());
    }

  //  public static String parse(String responseBody) {
  //      JSONArray albums = new JSONArray(responseBody);
   //     for(int i=0; i<albums.length(); i++)
   //     {
  //          JSONObject album =albums.getJSONObject(i);
   //         String title= album.getString("title");
   //         System.out.println(title);
   //     }

  //  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            http();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
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