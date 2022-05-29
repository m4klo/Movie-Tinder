package com.example.tinder_movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieInfoActivity extends Activity {

    private String CurrentUId;
    private FirebaseAuth mAuth;
    DatabaseReference mRef;
    private String title;

    private ListView mListView;

    public void backtolist(View view) {
        Intent intent = new Intent(MovieInfoActivity.this, ListActivity.class);
        startActivity(intent);
        finish();
        return;
    }
    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            movies movie = new movies();
            movie.setTitle(ds.getValue(movies.class).getTitle());

            if(movie.getTitle().equals(title)) {
                movie.setNetflixId(ds.getValue(movies.class).getNetflixId());
                movie.setDescription(ds.getValue(movies.class).getDescription());
                ArrayList<String> array  = new ArrayList<>();
                array.add(movie.getTitle());
                array.add(movie.getDescription());
                ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
                mListView.setAdapter(adapter);
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_infor_activity);
        mAuth = FirebaseAuth.getInstance();
        CurrentUId = mAuth.getCurrentUser().getUid();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        title= (String) b.get("title");

        mListView = (ListView) findViewById(R.id.listview);

        String dscp;
        String img;

        mRef = FirebaseDatabase.getInstance("https://movie-tinder-f5289-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Users").child(CurrentUId).child("approved");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}
