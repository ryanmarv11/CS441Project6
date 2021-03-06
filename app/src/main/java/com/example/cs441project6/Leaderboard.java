package com.example.cs441project6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Leaderboard extends AppCompatActivity {

    private Button backButton;
    private TextView textBox;
    int scoreArray[];
    int lastScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        textBox = (TextView) findViewById(R.id.textBox);

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Leaderboard.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });


        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastScore = preferences.getInt("lastScore", 0);

        String fileName = "ScoreLeaderboard";
        scoreArray = new int[11];
        try {
            FileInputStream input = getApplicationContext().openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(input, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            int iterScore;
            for(int i = 0; i < 11; i++) {
                line = reader.readLine().trim();
                System.out.println("THE LINE IS: " + line);
                iterScore = Integer.parseInt(line);
                System.out.println("\n\n\nTHE NUMBER IS: " + iterScore + "\n\n");
                if (line != null) {
                        System.out.println("THE LINE IS: " + line);
                        scoreArray[i] = Integer.parseInt(line);
                }

            }
            input.close();
            }
        catch(Exception e) {
            e.printStackTrace();
        }
        scoreArray[lastScore]++;
        String scoreString = makeScoreString(scoreArray);
        textBox.setText(scoreString);
        try {
            FileOutputStream output = getApplicationContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            String example = "";
            for(int i = 0; i < 11; i++)
            {
                example = String.valueOf(scoreArray[i]) + '\n';
                System.out.println("The string is: " + example + " \nThe getBytes() is: " + example.getBytes());
                output.write(example.getBytes(Charset.forName("UTF-8")));
            }
            output.close();
        } catch (Exception e) {
            System.out.println("ERROR FILE NOT FOUND.");
        }
        lastScore = 0;




    }

    public String makeScoreString(int array[])
    {
        String s = "";
        for(int i = 0; i < 11; i++)
        {
            s = s + Integer.valueOf(i) + ": " + Integer.valueOf(array[i]) + "\n";
        }

        return s;
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();

        Intent intent = new Intent(Leaderboard.this, Game.class);
        startActivity(intent);
        finish();
    }
}