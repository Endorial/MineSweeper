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
    private TextView smiley, timer, flagsLeft, txtBestTime, txtGameStatus;
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
        txtGameStatus=findViewById(R.id.txtGameStatus);
        if(Difficulty_Level.equals("Easy"))
        {
            BOMB_COUNT=5;
            GRID_SIZE=5;
            GRID_SPAN=5;
        }

        if(Difficulty_Level.equals("Medium"))
        {
            BOMB_COUNT=15;
            GRID_SIZE=8;
            GRID_SPAN=8;
        }
        if(Difficulty_Level.equals("Hard"))
        {
            BOMB_COUNT=25;
            GRID_SIZE=10;
            GRID_SPAN=10;
        }

        grid = findViewById(R.id.activity_main_grid);
        grid.setLayoutManager(new GridLayoutManager(this, GRID_SPAN));

        // Set grid height dynamically based on grid size
        int cellSize = 40; // dp
        int gridHeightPx = (int) (GRID_SIZE * cellSize * getResources().getDisplayMetrics().density);
        grid.getLayoutParams().height = gridHeightPx;


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
                txtGameStatus.setText("⏰ Time's Up! Game Over!");
                txtGameStatus.setTextColor(getResources().getColor(R.color.red));

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
                txtGameStatus.setText("Ready to Play!");
                txtGameStatus.setTextColor(getResources().getColor(R.color.green));
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
        // Regular click should reveal cells
        handleCellClick(cell, false);
    }

    private void handleCellClick(Cell cell, boolean isFlagAction) {
        if (!timerStarted) {
            countDownTimer.start();
            timerStarted = true;
            txtGameStatus.setText("🎮 Game Started!");
            txtGameStatus.setTextColor(getResources().getColor(R.color.yellow));
        }

        if (isFlagAction) {
            mineSweeperGame.flag(cell);
        } else {
            mineSweeperGame.handleCellClick(cell);
        }

        flagsLeft.setText(String.format("%03d", mineSweeperGame.getNumberBombs() - mineSweeperGame.getFlagCount()));

        if (mineSweeperGame.isGameOver()) {
            countDownTimer.cancel();
            txtGameStatus.setText("💥 BOOM! Game Over!");
            txtGameStatus.setTextColor(getResources().getColor(R.color.red));
            Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
            mineSweeperGame.getMineGrid().revealAllBombs();
        }

        if (mineSweeperGame.isGameWon()) {
            countDownTimer.cancel();
            saveBestTime();
            setBestTime();
            txtGameStatus.setText("🎉 Congratulations! You Won!");
            txtGameStatus.setTextColor(getResources().getColor(R.color.green));
            Toast.makeText(getApplicationContext(), "Game Won", Toast.LENGTH_SHORT).show();
            mineSweeperGame.getMineGrid().revealAllBombs();
        }

        mineGridRecyclerAdapter.setCells(mineSweeperGame.getMineGrid().getCells());
    }

    private void saveBestTime() {
        if(secondsElapsed < getBestTime() || getBestTime() == -1) {
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
            sharedPreferences.edit().putInt("BEST_TIME", secondsElapsed).apply();
        }
    }
    private int getBestTime() {

        SharedPreferences sharedPreferences=getSharedPreferences(getPackageName(),MODE_PRIVATE);
       return sharedPreferences.getInt("BEST_TIME",-1);
    }

    @Override
    public void cellLongClick(Cell cell) {
        // Long click should flag/unflag cells
        handleCellClick(cell, true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}