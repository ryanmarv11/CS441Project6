package com.example.cs441project6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Leaderboard extends AppCompatActivity {

    private Button backButton;
    private TextView textBox;
    int num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, num10;
    int lastScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        File file;
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

        file = new File(getApplicationContext().getFilesDir(), "ScoreLeaderboard.txt");
        if(file.exists())
        {
            textBox.setText("file found");
        }
        else
        {
            textBox.setText("file not found");
            /*String FILENAME = "ScoreLeaderboard";
            String string = "0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n";
            FileOutputStream fos;
            try {
                fos = openFileOutput(FILENAME, this.MODE_WORLD_WRITEABLE);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            */


        }

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();

        Intent intent = new Intent(Leaderboard.this, Game.class);
        startActivity(intent);
        finish();
    }
}