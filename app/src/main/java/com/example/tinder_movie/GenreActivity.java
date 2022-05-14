package com.example.tinder_movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class GenreActivity extends Activity {
    private Button Sci_fi, Horror, Fantasy;
    private static String url;


    static String getURL()
    {
        return url;
    }

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
                url = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?limit=100&order_by=date&genre_list=108533&type=movie";
                Intent intent = new Intent(GenreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        Horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?limit=100&order_by=date&genre_list=8711&type=movie";
                Intent intent = new Intent(GenreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        Fantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?limit=100&order_by=date&genre_list=9744&type=movie";
                Intent intent = new Intent(GenreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });


    }
}