package com.example.tinder_movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MovieInfoActivity extends Activity {

    public void backtolist(View view) {
        Intent intent = new Intent(MovieInfoActivity.this, ListActivity.class);
        startActivity(intent);
        finish();
        return;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_infor_activity);
    }

}
