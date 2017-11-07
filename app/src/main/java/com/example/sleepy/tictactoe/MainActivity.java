package com.example.sleepy.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int flag=0;
    int[] trackerArray = new int[] {2,2,2,2,2,2,2,2,2};
    boolean winner =false;
    boolean gameOver=true;
    int[][] winningPositions = new int[][]{{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void fadeIn (View view) {
        ImageView var = (ImageView) view;
        System.out.println(var.getTag().toString());
        if (winner == false) {
            //if flag is 0, blue
            //if flag is 1, purple
            //if flag is 2, empty
            if (trackerArray[Integer.parseInt(var.getTag().toString())] == 2) {
                if (flag == 0) {
                    var.setImageResource(R.drawable.blue);
                    trackerArray[Integer.parseInt(var.getTag().toString())] = flag;
                    flag = 1;
                } else if (flag == 1) {
                    var.setImageResource(R.drawable.purp);
                    trackerArray[Integer.parseInt(var.getTag().toString())] = flag;
                    flag = 0;
                }
                var.animate().alpha(1f).rotationBy(360f).setDuration(2000);
            }
            checkWin();

        }


    }
    public void playAgain(View view){

        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
        winnerLayout.setTranslationY(-2000f);
        winnerLayout.animate().alpha(0f);

        flag=0;
        winner =false;

        //trackerArray = new int[] {2,2,2,2,2,2,2,2,2}; //waste of memory?
        for(int j=0; j<trackerArray.length; j++){
            trackerArray[j]=2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    public void checkWin(){
        for(int[] winningPosition: winningPositions){
            if(trackerArray[winningPosition[0]] == trackerArray[winningPosition[1]]
                    && trackerArray[winningPosition[1]] == trackerArray[winningPosition[2]]
                    && trackerArray[winningPosition[0]]!=2 ){

                LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
                winnerLayout.setTranslationY(150f);
                winnerLayout.animate().alpha(1f).setDuration(2000);
                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                if(trackerArray[winningPosition[0]] == 0)
                    winnerMessage.setText("BLUE IS THE WINNER.");
                else
                    winnerMessage.setText("PURPLE IS THE WINNER.");

                winner = true;

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
        winnerLayout.setTranslationY(-2000f);
    }
}
