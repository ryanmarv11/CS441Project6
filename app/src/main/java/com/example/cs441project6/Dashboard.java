package com.example.cs441project6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private Button startButton, leaderboardButton, instructionsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        startButton = findViewById(R.id.startButton);
        leaderboardButton = findViewById(R.id.leaderboardButton);
        instructionsButton = findViewById(R.id.instructionsButton);
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Game.class);
                startActivity(intent);
                finish();
            }
        });

    }


}