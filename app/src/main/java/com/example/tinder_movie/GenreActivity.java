package com.example.tinder_movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;



public class GenreActivity extends Activity {
    private Button Sci_fi, Horror, Fantasy;
    private static String url;
    private static int urlp1;
    private static int genre;


    static String getURL()
    {
        return url;
    }
    public static void setURL() {url="https://unogs-unogs-v1.p.rapidapi.com/search/titles?offset="+urlp1+"&order_by=date&genre_list="+genre+"&type=movie";};
    public static void setURLorder() { urlp1=urlp1+100;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        Sci_fi = (Button) findViewById(R.id.sci_fi);
        Horror = (Button) findViewById(R.id.horror);
        Fantasy = (Button) findViewById(R.id.fantasy);

        Sci_fi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre=108533;
                urlp1=0;
                url = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?offset="+urlp1+"&order_by=date&genre_list="+genre+"&type=movie";
                Intent intent = new Intent(GenreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        Horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre=8711;
                urlp1=0;
                url = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?offset="+urlp1+"&order_by=date&genre_list="+genre+"&type=movie";
                Intent intent = new Intent(GenreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        Fantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre=9744;
                urlp1=0;
                url = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?&offset="+urlp1+"&order_by=date&genre_list="+genre+"&type=movie";
                Intent intent = new Intent(GenreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });


    }
}