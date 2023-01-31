package com.example.seminarja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.seminarja.adapters.RecyclerViewAdapter;
import com.example.seminarja.data.Question;
import com.example.seminarja.data.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HighScoreActivity extends AppCompatActivity {

    ArrayList<User> userArrayList = new ArrayList<>();
    ArrayList<Question> pitanja = new ArrayList<>();
    String broj = "";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score2);

        RecyclerViewAdapter rvAdapter = new RecyclerViewAdapter(userArrayList);

        broj = getIntent().getStringExtra("brojPitanja");
        dohvat(rvAdapter);
        recyclerView = findViewById(R.id.recyclerView);
        initRecyclerview(rvAdapter);
    }

    void dohvat(RecyclerViewAdapter rvAdapter) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("finishedGames");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    if(user.getPoints().equals(broj)) {
                        if(userArrayList.isEmpty()) {
                            userArrayList.add(user);
                        }
                        for(User user1 : userArrayList) {
                            if(!user1.getName().equalsIgnoreCase(user.getName())) {
                                userArrayList.add(user);
                            }
                        }
                    }
                }
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void initRecyclerview(RecyclerViewAdapter rvAdapter) {
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}