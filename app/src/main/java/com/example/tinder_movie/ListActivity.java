package com.example.tinder_movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.StackView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends Activity {


    private FirebaseAuth mAuth;
    private String CurrentUId;

    StackView stackview;
    DatabaseReference mRef;

    public void back(View view) {
        Intent intent = new Intent(ListActivity.this, GenreActivity.class);
        startActivity(intent);
        finish();
        return;
    }
    private List<String> titles(DataSnapshot snapshot)
    {
        List<String> titles=new ArrayList<>();
        for(DataSnapshot ds : snapshot.getChildren()){
            movies movie = new movies();
            movie.setTitle(ds.getValue(movies.class).getTitle());
            titles.add(movie.getTitle());
        }
        return titles;
    }
    private List<String> imgs(DataSnapshot snapshot)
    {
        List<String> imgs=new ArrayList<>();
        for(DataSnapshot ds : snapshot.getChildren()){
            movies movie = new movies();
            movie.setImg(ds.getValue(movies.class).getImg());
            imgs.add(movie.getImg());
        }
        return imgs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stackview = (StackView) findViewById(R.id.stackview);
        mAuth = FirebaseAuth.getInstance();
        CurrentUId = mAuth.getCurrentUser().getUid();

        mRef = FirebaseDatabase.getInstance("https://movie-tinder-f5289-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Users").child(CurrentUId).child("approved");
        mRef.addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                titles(snapshot);
                imgs(snapshot);
                listActivityAdapter adapter = new listActivityAdapter(titles(snapshot), imgs(snapshot), ListActivity.this, R.layout.item_scaleview);
                stackview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        stackview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, MovieInfoActivity.class);
                intent.putExtra("title", stackview.getItemAtPosition(position).toString());
                startActivity(intent);
                finish();
            }
        });

    }
}
