package com.example.tinder_movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class MovieInfoActivity extends Activity {

    private String CurrentUId;
    private FirebaseAuth mAuth;
    DatabaseReference mRef;


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
        mAuth = FirebaseAuth.getInstance();
        CurrentUId = mAuth.getCurrentUser().getUid();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        String title= (String) b.get("title");

        String URL;
        mRef = FirebaseDatabase.getInstance("https://movie-tinder-f5289-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Users").child(CurrentUId).child("right").child(title).child("title");
        Log.d("wtf", mRef.toString());
        TextView titles = (TextView) findViewById(R.id.titleinfo);
        ImageView image = (ImageView) findViewById(R.id.imageinfo);
        TextView description = (TextView) findViewById(R.id.descriptioninfo);
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Map<String, String> map = (Map) snapshot.getValue();
                String dscp = map.get("description").toString();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        titles.setText(title);
    }

}
