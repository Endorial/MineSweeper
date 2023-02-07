package com.learntodroid.androidminesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    public static final long TIMER_LENGTH = 999000L;    // 999 seconds in milliseconds
    public static  int BOMB_COUNT = 7;
    public static  int GRID_SIZE = 7;
    public static  int GRID_SPAN = 10;

    private MineGridRecyclerAdapter mineGridRecyclerAdapter;
    private RecyclerView grid;
    private TextView smiley, timer, flagsLeft,txtBestTime;
    private MineSweeperGame mineSweeperGame;
    private CountDownTimer countDownTimer;
    private int secondsElapsed;
    private boolean timerStarted;
    String Difficulty_Level="Easy";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Difficulty_Level=getIntent().getStringExtra("Level");
        txtBestTime=findViewById(R.id.txtBestTime);
        if(Difficulty_Level.equals("Easy"))
        {
            BOMB_COUNT=2;
            GRID_SIZE=5;
            GRID_SPAN=5;
        }

        if(Difficulty_Level.equals("Medium"))
        {
            BOMB_COUNT=8;
            GRID_SIZE=8;
            GRID_SPAN=8;
        }
        if(Difficulty_Level.equals("Hard"))
        {
            BOMB_COUNT=10;
            GRID_SIZE=10;
            GRID_SPAN=10;
        }

        grid = findViewById(R.id.activity_main_grid);
        grid.setLayoutManager(new GridLayoutManager(this, GRID_SPAN));


        setBestTime();
        timer = findViewById(R.id.activity_main_timer);
        timerStarted = false;
        countDownTimer = new CountDownTimer(TIMER_LENGTH, 1000) {
            public void onTick(long millisUntilFinished) {
                secondsElapsed += 1;
                timer.setText(String.format("%03d", secondsElapsed));
            }

            public void onFinish() {
                mineSweeperGame.outOfTime();

                Toast.makeText(getApplicationContext(), "Game Over: Timer Expired", Toast.LENGTH_SHORT).show();
                mineSweeperGame.getMineGrid().revealAllBombs();
                mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
            }
        };

        flagsLeft = findViewById(R.id.activity_main_flagsleft);

        mineSweeperGame = new MineSweeperGame(GRID_SIZE, BOMB_COUNT);
        flagsLeft.setText(String.format("%03d", mineSweeperGame.getNumberBombs() - mineSweeperGame.getFlagCount()));
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(mineSweeperGame.getMineGrid().getCells(), this);
        grid.setAdapter(mineGridRecyclerAdapter);

        smiley = findViewById(R.id.activity_main_smiley);
        smiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mineSweeperGame = new MineSweeperGame(GRID_SIZE, BOMB_COUNT);
                mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
                timerStarted = false;
                countDownTimer.cancel();
                secondsElapsed = 0;
                timer.setText(R.string.default_count);
                flagsLeft.setText(String.format("%03d", mineSweeperGame.getNumberBombs() - mineSweeperGame.getFlagCount()));
            }
        });

    }

    private void setBestTime() {
        if(getBestTime()==-1)
        {
            txtBestTime.setText("Best Time : 000");
        }
        else
        {
            txtBestTime.setText(String.format("Best Time : %03d", getBestTime()));
        }
    }

    @Override
    public void cellClick(Cell cell) {

        if(mineSweeperGame.isFlagMode())
        {
            mineSweeperGame.toggleMode();
        }
        handleCellClick(cell);
    }



    private void handleCellClick(Cell cell) {

        mineSweeperGame.handleCellClick(cell);

        flagsLeft.setText(String.format("%03d", mineSweeperGame.getNumberBombs() - mineSweeperGame.getFlagCount()));

        if (!timerStarted) {
            countDownTimer.start();
            timerStarted = true;
        }

        if (mineSweeperGame.isGameOver()) {
            countDownTimer.cancel();
            Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
            mineSweeperGame.getMineGrid().revealAllBombs();
        }

        if (mineSweeperGame.isGameWon()) {
            countDownTimer.cancel();
            saveBestTime();
            setBestTime();
            Toast.makeText(getApplicationContext(), "Game Won", Toast.LENGTH_SHORT).show();
            mineSweeperGame.getMineGrid().revealAllBombs();
        }

        mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
    }

    private void saveBestTime() {


        if(secondsElapsed>getBestTime() && getBestTime()!=-1)
        {
            return;
        }
        SharedPreferences sharedPreferences=getSharedPreferences(getPackageName(),MODE_PRIVATE);
        sharedPreferences.edit().putInt("BEST_TIME",secondsElapsed).apply();

    }
    private int getBestTime() {

        SharedPreferences sharedPreferences=getSharedPreferences(getPackageName(),MODE_PRIVATE);
       return sharedPreferences.getInt("BEST_TIME",-1);
    }

    @Override
    public void cellLongClick(Cell cell) {


        if(!mineSweeperGame.isFlagMode())
        {
            mineSweeperGame.toggleMode();
        }

        handleCellClick(cell);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}