package com.example.cs441project6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;



public class Game extends AppCompatActivity {

    private TextView currentNumber, currentSet, statusMessage;
    private Switch standardSwitch, pioneerSwitch, modernSwitch, legacySwitch, historicSwitch;
    private Button submitButton, nextButton, backButton;
    private int roundNumber, randomPlaceholder, score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        roundNumber = 0;
        score = 0;
        ArrayList<String> nameList = createNameList();
        ArrayList<MTGSet> setList = createSetList(nameList);
        //assign the textviews
        currentNumber = (TextView) findViewById(R.id.currentNumber);
        currentSet = (TextView) findViewById(R.id.currentSet);
        statusMessage = (TextView) findViewById(R.id.statusMessage);


        //assign the switches
        standardSwitch = (Switch) findViewById(R.id.standardSwitch);
        pioneerSwitch = (Switch) findViewById(R.id.pioneerSwitch);
        modernSwitch = (Switch) findViewById(R.id.modernSwitch);
        legacySwitch = (Switch) findViewById(R.id.legacySwitch);
        historicSwitch = (Switch) findViewById(R.id.historicSwitch);


        //create the switch listeners
        standardSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true)
                {
                    Toast.makeText(getBaseContext(), "Standard has been selected", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getBaseContext(), "Standard has been deselected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pioneerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true)
                {
                    Toast.makeText(getBaseContext(), "Pioneer has been selected", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getBaseContext(), "Pioneer has been deselected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        modernSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true)
                {
                    Toast.makeText(getBaseContext(), "Modern has been selected", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getBaseContext(), "Modern has been deselected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        legacySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true)
                {
                    Toast.makeText(getBaseContext(), "Legacy has been selected", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getBaseContext(), "Legacy has been deselected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        historicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true)
                {
                    Toast.makeText(getBaseContext(), "Historic has been selected", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getBaseContext(), "Historic has been deselected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //assign the buttons
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusMessage.setText("");
                MTGSet iter;
                Random rand = new Random();
                int randomNumber;
                roundNumber++;
                if(roundNumber < 11)
                {
                    //generates the set
                    do {
                        randomNumber = rand.nextInt(104) + 1;
                        iter = setList.get(randomNumber);
                    }while(iter.isBeenChosen());
                    randomPlaceholder = randomNumber;
                    currentNumber.setText(getRoundNumberString(roundNumber));
                    currentSet.setText(iter.choose());
                    nextButton.setVisibility(View.GONE);
                    submitButton.setVisibility(View.VISIBLE);
                }
                else
                {
                    //cleanup step
                    nextButton.setVisibility(View.GONE);
                    submitButton.setVisibility(View.GONE);

                    standardSwitch.setVisibility(View.GONE);
                    pioneerSwitch.setVisibility(View.GONE);
                    modernSwitch.setVisibility(View.GONE);
                    legacySwitch.setVisibility(View.GONE);
                    historicSwitch.setVisibility(View.GONE);

                    //cleanup step part 2
                    statusMessage.setText("This round you got " + score + " correct. Click the leaderboard button to see your scores.");
                    backButton.setVisibility(View.VISIBLE);
                }
            }
        });

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call the check function
                MTGSet iter = setList.get(randomPlaceholder);
                boolean isCorrect = isCorrect(iter);
                if(isCorrect)
                {
                    score++;
                    statusMessage.setText("Correct! Press Next for the next set :)");
                }
                else
                {
                    String errorMessage = "Unfortunately your answer was not \ncorrect, the correct answer is:\n";
                    if(iter.isStandardLegal())
                    {
                        String s = "Standard: Yes\n";
                        errorMessage = errorMessage + s;
                    }
                    else
                    {
                        String s = "Standard: No\n";
                        errorMessage = errorMessage + s;
                    }

                    if(iter.isPioneerLegal())
                    {
                        String s = "Pioneer: Yes\n";
                        errorMessage = errorMessage + s;
                    }
                    else
                    {
                        String s = "Pioneer: No\n";
                        errorMessage = errorMessage + s;
                    }
                    if(iter.isModernLegal())
                    {
                        String s = "Modern: Yes\n";
                        errorMessage = errorMessage + s;
                    }
                    else
                    {
                        String s = "Modern: No\n";
                        errorMessage = errorMessage + s;
                    }
                    if(iter.isLegacyLegal())
                    {
                        String s = "Legacy: Yes\n";
                        errorMessage = errorMessage + s;
                    }
                    else
                    {
                        String s = "Legacy: No\n";
                        errorMessage = errorMessage + s;
                    }
                    if(iter.isHistoricLegal())
                    {
                        String s = "Historic: Yes\n";
                        errorMessage = errorMessage + s;
                    }
                    else
                    {
                        String s = "Historic: No\n";
                        errorMessage = errorMessage + s;
                    }
                    statusMessage.setText(errorMessage);
                }
                nextButton.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.GONE);
            }
        });

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("lastScore", score);
                editor.apply();

                Intent intent = new Intent(Game.this, Leaderboard.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public boolean isCorrect(MTGSet iter)
    {
        if(iter.isStandardLegal() != standardSwitch.isChecked())
        {
            return false;
        }
        if(iter.isPioneerLegal() != pioneerSwitch.isChecked())
        {
            return false;
        }
        if(iter.isModernLegal() != modernSwitch.isChecked())
        {
            return false;
        }
        if(iter.isLegacyLegal() != legacySwitch.isChecked())
        {
            return false;
        }
        if(iter.isHistoricLegal() != historicSwitch.isChecked())
        {
            return false;
        }
        return true;
    }

    public ArrayList<String> createNameList()
    {
        ArrayList<String> setNameList = new ArrayList<>();
        setNameList.add("Kaldheim");
        setNameList.add("Zendikar Rising");
        setNameList.add("Core Set 2021");
        setNameList.add("Ikoria");
        setNameList.add("Theros Beyond Death");
        setNameList.add("Throne of Eldraine");
        setNameList.add("Core Set 2020");
        setNameList.add("War of the Spark");
        setNameList.add("Ravnica Allegiance");
        setNameList.add("Guilds of Ravnica");
        setNameList.add("Core Set 2019");
        setNameList.add("Dominaria");
        setNameList.add("Rivals of Ixalan");
        setNameList.add("Ixalan");
        setNameList.add("Hour of Devastation");
        setNameList.add("Amonkhet");
        setNameList.add("Aether Revolt");
        setNameList.add("Kaladesh");
        setNameList.add("Eldritch Moon");
        setNameList.add("Shadows Over Innistrad");
        setNameList.add("Oath of the Gatewatch");
        setNameList.add("Battle For Zendikar");
        setNameList.add("Magic Origins");
        setNameList.add("Dragons of Tarkir");
        setNameList.add("Fate Reforged");
        setNameList.add("Khans of Tarkir");
        setNameList.add("Core Set 2015");
        setNameList.add("Journey Into Nyx");
        setNameList.add("Born of the Gods");
        setNameList.add("Theros");
        setNameList.add("Core Set 2014");
        setNameList.add("Dragon's Maze");
        setNameList.add("Gatecrash");
        setNameList.add("Return to Ravnica");
        setNameList.add("Core Set 2013");
        setNameList.add("Avacyn Restored");
        setNameList.add("Dark Ascension");
        setNameList.add("Core Set 2012");
        setNameList.add("New Phyrexia");
        setNameList.add("Mirrodin Beseiged");
        setNameList.add("Scars of Mirrodin");
        setNameList.add("Core Set 2011");
        setNameList.add("Rise of the Eldrazi");
        setNameList.add("Worldwake");
        setNameList.add("Zendikar");
        setNameList.add("Core Set 2010");
        setNameList.add("Alara Reborn");
        setNameList.add("Conflux");
        setNameList.add("Shards of Alara");
        setNameList.add("Eventide");
        setNameList.add("Shadowmoor");
        setNameList.add("MorningTide");
        setNameList.add("Lorwyn");
        setNameList.add("10th Edition");
        setNameList.add("Future Sight");
        setNameList.add("Planar Chaos");
        setNameList.add("Time Spiral");
        setNameList.add("Coldsnap");
        setNameList.add("Guildpact");
        setNameList.add("Ravnica: City of Guilds");
        setNameList.add("9th Edition");
        setNameList.add("Saviors of Kamigawa");
        setNameList.add("Betrayers of Kamigawa");
        setNameList.add("Champions of Kamigawa");
        setNameList.add("Fifth Dawn");
        setNameList.add("Darksteel");
        setNameList.add("Mirrodin");
        setNameList.add("8th Edition");
        setNameList.add("Scourge");
        setNameList.add("Legions");
        setNameList.add("Onslaught");
        setNameList.add("Judgment");
        setNameList.add("Torment");
        setNameList.add("Odyssey");
        setNameList.add("Apocalypse");
        setNameList.add("7th Edition");
        setNameList.add("Planeshift");
        setNameList.add("Invasion");
        setNameList.add("Prophecy");
        setNameList.add("Nemesis");
        setNameList.add("Mercadian Masques");
        setNameList.add("6th Edition");
        setNameList.add("Urza's Destiny");
        setNameList.add("Urza's Legacy");
        setNameList.add("Urza's Saga");
        setNameList.add("Exodus");
        setNameList.add("Stronghold");
        setNameList.add("Tempest");
        setNameList.add("5th Edition");
        setNameList.add("Visions");
        setNameList.add("Weatherlight");
        setNameList.add("Mirage");
        setNameList.add("Alliances");
        setNameList.add("Homelands");
        setNameList.add("Ice Age");
        setNameList.add("4th Edition");
        setNameList.add("Fallen Empires");
        setNameList.add("The Dark");
        setNameList.add("Legends");
        setNameList.add("Revised Edition");
        setNameList.add("Antiquities");
        setNameList.add("Arabian Nights");
        setNameList.add("Unlimited");
        setNameList.add("Beta");
        setNameList.add("Alpha");
        return setNameList;
    }

    public ArrayList<MTGSet> createSetList(ArrayList<String> nameList)
    {
        ArrayList<MTGSet> setList = new ArrayList<>();
        //standard is 0-5, pioneer is 6-33, modern is 34-66, legacy is 67-104
        for(int i = 0; i < nameList.size(); i++)
        {
            if(i < 6) //standard legal
            {
                setList.add(new MTGSet(true, true, true, true, true, nameList.get(i)));
            }
            else if(i >= 6 & i < 14) //historic but not pioneer
            {
                setList.add(new MTGSet(false, false, true, true, true, nameList.get(i)));
            }
            else if(i >= 14 & i < 34) //pioneer
            {
                setList.add(new MTGSet(false, true, true, true, false, nameList.get(i)));
            }
            else if(i >= 34 & i < 67) //modern
            {
                setList.add(new MTGSet(false, false, true, true, false, nameList.get(i)));
            }
            else if(i >= 67 & i < 105) //legacy
            {
                setList.add(new MTGSet(false, false, false, true, false, nameList.get(i)));
            }
            else
            {
                System.out.println("ERROR: Undefined Set: " + nameList.get(i));
            }
        }
        return setList;
    }

    public String getRoundNumberString(int roundNumber)
    {
        String s = "Round " + roundNumber + " of 10.";
        return s;
    }
}