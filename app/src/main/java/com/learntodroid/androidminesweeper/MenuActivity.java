package com.learntodroid.androidminesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClickStart(View view) {

        showDifficultLevelDialog();
    }

    public  void  showDifficultLevelDialog()
    {
        final Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_difficulty_level);
        dialog.show();

        final CardView cardEasy=dialog.findViewById(R.id.cardEasy);
        final CardView cardMedium=dialog.findViewById(R.id.cardMedium);
        final CardView cardHard=dialog.findViewById(R.id.cardHard);

        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v){

                dialog.dismiss();
                String Difficulty_Level="Easy";
                if(v==cardEasy)
                    Difficulty_Level="Easy";
                if(v==cardMedium)
                    Difficulty_Level="Medium";
                if(v==cardHard)
                    Difficulty_Level="Hard";

                startActivity(new Intent(MenuActivity.this,MainActivity.class).putExtra("Level",Difficulty_Level));

            }
        };


        cardEasy.setOnClickListener(onClickListener);
        cardMedium.setOnClickListener(onClickListener);
        cardHard.setOnClickListener(onClickListener);
    }
    public void onClickExit(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    public void onClickHowToPlay(View view) {
        startActivity(new Intent(MenuActivity.this,HowToActivity.class));

    }
}