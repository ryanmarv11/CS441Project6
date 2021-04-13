package com.example.cs441project6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Instructions extends AppCompatActivity {

    private Button backButton;
    private TextView textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Instructions.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        textBox = findViewById(R.id.textBox);
        String instructions = "Welcome to the MTG Format Guesser. The game works as so:\nTo start, press the next button. Then, a random set from magic's history will be generated.\n";
        instructions += "Once the set has been generated, flip the switches that correspond to the formats that that set's cards are legal in.\n";
        instructions += "For example, if Theros is generated, you'd select Pioneer, Modern, and Legacy\n";
        instructions += "Then, once you have made your selections, click the submit button. The game will tell you whether or not you were correct.\n";
        instructions += "I hope you enjoy my game.:)\n";
        textBox.setText(instructions);

    }
}