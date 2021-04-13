package com.example.cs441project6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;



public class TotalScores extends AppCompatActivity {

    private TextView textBox;
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_scores);

        textBox = (TextView) findViewById(R.id.textBox);

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TotalScores.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        int[] scoreArray;
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

        String scoreString = makeScoreString(scoreArray);
        textBox.setText(scoreString);
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
}