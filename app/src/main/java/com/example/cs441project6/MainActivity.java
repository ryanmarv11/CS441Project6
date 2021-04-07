package com.example.cs441project6;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.io.*;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000; //2 seconds


    //Variables
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.magicCardBack);
        message = findViewById(R.id.textView);

        image.setAnimation(topAnim);
        message.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

        ArrayList<String> nameList = new ArrayList<>();
        nameList = createNameList();
        ArrayList<MTGSet> setList = createSetList(nameList);



    }
    public ArrayList<String> createNameList()
    {
        ArrayList<String> setNameList = new ArrayList<>();
        File file = new File("/mnt/c/Users/ryanm/Documents/CS441/CS441Project6/app/src/main/java/com/example/cs441project6");
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while(line != null)
            {
                setNameList.add(line);
                line = br.readLine();
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("ERROR: FILE NOT FOUND.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("ERROR: IOException.");
            e.printStackTrace();
        }
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
                setList.add(new MTGSet(false, true, true, true, true, nameList.get(i)));
            }
            else if(i >= 14 & i < 34) //pioneer
            {
                setList.add(new MTGSet(false, true, true, true, true, nameList.get(i)));
            }
            else if(i >= 34 & i < 67) //modern
            {
                setList.add(new MTGSet(false, false, true, true, false, nameList.get(i)));
            }
            else if(i >= 67 & i < 104) //legacy
            {
                setList.add(new MTGSet(false, false, false, true, false, nameList.get(i)));
            }
            else
            {
                System.out.println("WEEWOO WEEWOO SOMEBODY DID A FUCKY WUCKY");
            }
        }
        return setList;
    }
}