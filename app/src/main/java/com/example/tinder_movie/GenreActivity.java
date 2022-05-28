package com.example.tinder_movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class GenreActivity extends Activity {
    private Button Sci_fi, Horror, Fantasy, Action, Adventures, Comedias, Drama, Anime, Documentaries;
    private static String url;
    private static int urlp1;
    private static int genre;
    private FirebaseAuth mAuth;

    public void logoutUser(View view) {
        mAuth.signOut();
        Intent intent = new Intent (GenreActivity.this, ChooseLoginRegistrationActivity.class);
        startActivity(intent);
        finish();
        return;
    }
    public void openLibrary(View view) {
        Intent intent = new Intent(GenreActivity.this, ListActivity.class);
        startActivity(intent);
        finish();
        return;
    }


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
        Action = (Button) findViewById(R.id.action);
        Adventures = (Button) findViewById(R.id.adventures);
        Comedias = (Button) findViewById(R.id.comedias);
        Drama = (Button) findViewById(R.id.drama);
        Anime = (Button) findViewById(R.id.anime);
        Documentaries = (Button) findViewById(R.id.documentaries);
        mAuth = FirebaseAuth.getInstance();

        Sci_fi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre=3276033;
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
        Action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre=801362;
                urlp1=0;
                url = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?&offset="+urlp1+"&order_by=date&genre_list="+genre+"&type=movie";
                Intent intent = new Intent(GenreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        Adventures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre=7442;
                urlp1=0;
                url = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?&offset="+urlp1+"&order_by=date&genre_list="+genre+"&type=movie";
                Intent intent = new Intent(GenreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        Comedias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre=6548;
                urlp1=0;
                url = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?&offset="+urlp1+"&order_by=date&genre_list="+genre+"&type=movie";
                Intent intent = new Intent(GenreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        Drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre=5763;
                urlp1=0;
                url = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?&offset="+urlp1+"&order_by=date&genre_list="+genre+"&type=movie";
                Intent intent = new Intent(GenreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        Anime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre=7424;
                urlp1=0;
                url = "https://unogs-unogs-v1.p.rapidapi.com/search/titles?&offset="+urlp1+"&order_by=date&genre_list="+genre+"&type=movie";
                Intent intent = new Intent(GenreActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });
        Documentaries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre=2243108;
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