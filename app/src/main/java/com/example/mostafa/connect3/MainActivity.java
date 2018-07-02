package com.example.mostafa.connect3;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    //yellow=0 red=1

    int active_player=0;
    boolean gameIsActive=true;

    //2 mean un played
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int [][]winningStates={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};




    public void dropIn(View view){
        ImageView imageView = (ImageView) view;
        int number= Integer.parseInt(imageView.getTag().toString());
        if(gameState[number]==2&& gameIsActive) {
            gameState[number]=active_player;

            imageView.setTranslationY(-1000f);
            if (active_player == 0) {
                imageView.setImageResource(R.drawable.yellow);

                active_player = 1;
            } else if (active_player == 1) {
                imageView.setImageResource(R.drawable.red);

                active_player = 0;
            }

            imageView.animate().translationYBy(1000f).rotation(360).setDuration(200);
            for (int[] winingState : winningStates){
                    if(gameState[winingState[0]]==gameState[winingState[1]]&&gameState[winingState[1]]==gameState[winingState[2]]
                            &&gameState[winingState[0]]!=2){
                       Toast.makeText(this,"game over",Toast.LENGTH_SHORT).show();
                        gameIsActive=false;
                        String winner="Red";
                        if(gameState[winingState[0]]==0){
                            winner="yellow";
                        }
                        TextView textView=(TextView)findViewById(R.id.winner_message);
                        textView.setText(winner+" has won!");
                        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linear_layout);
                        linearLayout.setVisibility(View.VISIBLE);
                        break;
                    }else {
                        boolean gameIsOver=true;
                        for (int state:gameState){
                            if(state==2) gameIsOver=false;
                        }
                        if(gameIsOver){
                            TextView textView=(TextView)findViewById(R.id.winner_message);
                            textView.setText("it's a draw");
                            LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linear_layout);
                            linearLayout.setVisibility(View.VISIBLE);
                        }
                    }
            }
        }

    }
    public void playAgain(View view){
        gameIsActive=true;
        active_player=0;
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linear_layout);
        linearLayout.setVisibility(View.INVISIBLE);
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        GridLayout gridLayout=(GridLayout)findViewById(R.id.grid_layout);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





    }


}
