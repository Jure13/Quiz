package com.example.seminarja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.seminarja.data.Question;
import com.example.seminarja.data.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class MenuActivity extends AppCompatActivity {

    private Button btnPlay, btnHighScore;

    ArrayList<Question> questionsList = new ArrayList<>();
    String jsonArrayList;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        btnPlay = findViewById(R.id.playButton);
        btnHighScore = findViewById(R.id.highScoreButton);


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), QuizActivity.class);
                intent.putExtra("question", gson.toJson(questionsList));
                intent.putExtra("points", "0");
                startActivity(intent);
            }
        });

        btnHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!Objects.equals(jsonArrayList, "")){
                    Intent intentScores = new Intent(view.getContext(), HighScoreActivity.class);
                    String vel = Integer.toString( questionsList.size());
                    intentScores.putExtra("brojPitanja", vel);
                    startActivity(intentScores);
                } else{
                    Toast.makeText(view.getContext(), "User scores are loading", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getQuestions();
    }


    void getQuestions() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("question");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Type type = new TypeToken<ArrayList<Question>>() {}.getType();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Question question = dataSnapshot.getValue(Question.class);
                    questionsList.add(question);
                }

                Collections.shuffle(questionsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}