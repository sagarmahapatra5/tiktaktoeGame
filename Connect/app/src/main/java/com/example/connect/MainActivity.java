package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    boolean gameIsActive = true;
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View v) {
        Log.i("Sagar", "Inside Drop In");
        ImageView counter = (ImageView) v;
        counter.setTranslationY(-1000f);
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                counter.animate().translationYBy(1000f).rotation(1800).setDuration(1000);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                counter.animate().translationYBy(1000f).rotation(1800).setDuration(1000);
                activePlayer = 0;
            }
            for (int[] position : winningPositions) {
                if (gameState[position[0]] == gameState[position[1]]
                        && gameState[position[1]] == gameState[position[2]]
                        && gameState[position[0]] != 2) {
                    String winner = "Red";
                    gameIsActive = false;
                    if (gameState[position[0]] == 0) {
                        winner = "Yellow";
                    }
                    TextView winnerMsg = (TextView) findViewById(R.id.messageText);
                    winnerMsg.setText(winner + " has won !");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainDisplay);
                    layout.setVisibility(v.VISIBLE);
                } else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 2) gameIsOver = false;
                    }
                    if (gameIsOver) {
                        TextView winnerMsg = (TextView) findViewById(R.id.messageText);
                        winnerMsg.setText("It's a draw !");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainDisplay);
                        layout.setVisibility(v.VISIBLE);
                    }
                }
            }
        }
    }
    public void playAgain(View v){
        gameIsActive = true;
        LinearLayout layout= (LinearLayout)findViewById(R.id.playAgainDisplay);
        layout.setVisibility(v.INVISIBLE);
         activePlayer =0;
         for (int i=0;i<gameState.length;i++){
             gameState[i]=2;
         }
        GridLayout gridLayout =(GridLayout)findViewById(R.id.gridLayout);
         for(int i=0;i < gridLayout.getChildCount();i++ ){
             ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
         }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
