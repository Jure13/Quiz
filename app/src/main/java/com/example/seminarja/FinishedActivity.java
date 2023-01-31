package com.example.seminarja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.seminarja.data.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.UUID;

public class FinishedActivity extends AppCompatActivity {

    TextView pointsText, congrats;
    Button btnSave, btnHome;

    Integer points;

    DatabaseReference db;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

        points = Integer.parseInt(getIntent().getStringExtra("points"));
        initElements();

        gson = new Gson();
        db = FirebaseDatabase.getInstance().getReference().child("finishedGames");
    }

    void goHome(){
        startActivity(new Intent(this, MenuActivity.class));
    }

    void initElements(){
        pointsText = findViewById(R.id.finalScore);
        pointsText.setText("You have: " + points.toString());

        congrats = findViewById(R.id.congrat);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveScore();
                goHome();
            }
        });
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
    }

    void saveScore(){
        User user = new User();
        String uid = UUID.randomUUID().toString();
        EditText name = findViewById(R.id.editTextName);
        user.setName(name.getText().toString());
        user.setPoints(points.toString());

        db.child(uid).setValue(user);
    }
}