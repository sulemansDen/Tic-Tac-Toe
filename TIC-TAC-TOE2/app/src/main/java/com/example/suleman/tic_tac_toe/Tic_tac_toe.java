package com.example.suleman.tic_tac_toe;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.IccOpenLogicalChannelResponse;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tic_tac_toe extends Activity {

    private Tic_tac_toe_game myGame;

    private Button myboardbutton[];

    private TextView myInfo;
    private TextView myPlayerone;
    private TextView myPlayertwo;
    private TextView myTies;
    private TextView mPlayerOneText;
    private TextView mPlayerTwoText;

    private int humanCounter = 0;
    private int tiesCounter = 0;
    private int AndroidCounter = 0;

    private boolean myHumanFirst = true;
    private boolean msinglePlayer = false;
    private boolean mIsPlayerOneTurn = true;
    private boolean myGameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tic_tac_toe);

        boolean mGameType = getIntent().getExtras().getBoolean("Game Type");

        myboardbutton = new Button[myGame.getBoardSize()];
        myboardbutton[0] = (Button) findViewById(R.id.one);
        myboardbutton[1] = (Button) findViewById(R.id.two);
        myboardbutton[2] = (Button) findViewById(R.id.three);
        myboardbutton[3] = (Button) findViewById(R.id.four);
        myboardbutton[4] = (Button) findViewById(R.id.five);
        myboardbutton[5] = (Button) findViewById(R.id.six);
        myboardbutton[6] = (Button) findViewById(R.id.seven);
        myboardbutton[7] = (Button) findViewById(R.id.eight);
        myboardbutton[8] = (Button) findViewById(R.id.nine);

        myInfo = (TextView)findViewById(R.id.information);
        myPlayerone = (TextView)findViewById(R.id.human);
        myTies = (TextView)findViewById(R.id.ties);
        myPlayertwo = (TextView)findViewById(R.id.Android);
        mPlayerOneText = (TextView)findViewById(R.id.textView);
        mPlayerTwoText = (TextView)findViewById(R.id.textView3);
        myPlayerone.setText(Integer.toString(humanCounter));
        myTies.setText(Integer.toString(tiesCounter));
        myPlayertwo.setText(Integer.toString(AndroidCounter));

        myGame = new Tic_tac_toe_game();
        startNewGame(mGameType);
    }
    private void startNewGame(boolean IsSingle)
    {
        this.msinglePlayer = IsSingle;
        myGame.clearBoard();
        for (int i=0 ; i<myboardbutton.length ; i++)
        {
            //myboardbutton[i].setText("");
            myboardbutton[i].setEnabled(true);
            myboardbutton[i].setOnClickListener(new ButtonClickListener(i));
         myboardbutton[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.bg));
        }

        if(msinglePlayer)
        {

           // mPlayerOneText.setText("Human : ");
            //mPlayerTwoText.setText("Android :");
            if(myHumanFirst)
            {
                myInfo.setText(R.string.First_human);
                myHumanFirst = false;
            }
            else
            {
                myInfo.setText("Android's Turn");
                int move = myGame.computer_move();
                setMove(myGame.Player_two,move);
                myHumanFirst = true;
            }
        }
        else
        {
            mPlayerOneText.setText("Player One : ");
            mPlayerTwoText.setText("Player Two :");
            if(myHumanFirst)
            {
                myInfo.setText("Player One Turn");
                myHumanFirst = false;
            }
            else
            {
                myInfo.setText("Android's Turn");
                myHumanFirst = true;
            }
        }
        myGameOver=false;

    }

    private class ButtonClickListener implements View.OnClickListener
    {
        int location;

        public ButtonClickListener(int location)
        {
            this.location = location;
        }
        public void onClick(View view)
        {
            if(!myGameOver)
            {
                if(myboardbutton[location].isEnabled())
                {
                    if(msinglePlayer)
                    {
                        setMove(myGame.Player_one,location);
                        int winner = myGame.check_for_winner();
                        if(winner == 0)
                        {
                            myInfo.setText("Android's turn");
                            int move = myGame.computer_move();
                            setMove(myGame.Player_two,move);
                            winner = myGame.check_for_winner();
                        }
                        if(winner == 0) {
                            myInfo.setText(R.string.Turn_Human);
                        }
                        else if(winner ==1)
                        {
                            Toast.makeText(getApplication(), "It's A Tie", Toast.LENGTH_SHORT).show();
                            myInfo.setText("It's A Tie");
                            tiesCounter++;
                            myTies.setText(Integer.toString(tiesCounter));
                            myGameOver = true;

                        }
                        else if(winner ==2)
                        {
                            Toast.makeText(getApplication(), R.string.result_human_wins, Toast.LENGTH_SHORT).show();
                            myInfo.setText(R.string.result_human_wins);
                            humanCounter++;
                            myPlayerone.setText(Integer.toString(humanCounter));
                            myGameOver= true;
                        }
                        else
                        {
                            Toast.makeText(getApplication(), R.string.result_computer_won, Toast.LENGTH_SHORT).show();
                            myInfo.setText(R.string.result_computer_won);
                            AndroidCounter++;
                            myPlayertwo.setText(Integer.toString(AndroidCounter));
                            myGameOver= true;
                        }
                    }
                    else
                    {
                        if(mIsPlayerOneTurn)
                        {
                            setMove(myGame.Player_one,location);
                        }
                        else
                        {
                            setMove(myGame.Player_two,location);
                        }

                        int winner = myGame.check_for_winner();
                        if(winner == 0)
                        {
                            if(mIsPlayerOneTurn)
                            {
                                myInfo.setText("Player Two Turn");
                                mIsPlayerOneTurn = false;
                            }
                            else
                            {
                                myInfo.setText("Player One Turn");
                                mIsPlayerOneTurn = true;
                            }
                        }
                        else if(winner ==1)
                        {
                            Toast.makeText(getApplication(),"It's A Tie", Toast.LENGTH_LONG).show();
                            myInfo.setText("It's A Tie");
                            tiesCounter++;
                            myTies.setText(Integer.toString(tiesCounter));
                            myGameOver = true;

                        }
                        else if(winner ==2)
                        {
                            Toast.makeText(getApplication(), "Player One Won", Toast.LENGTH_SHORT).show();
                            myInfo.setText("Player One Wins");
                            humanCounter++;
                            myPlayerone.setText(Integer.toString(humanCounter));
                            myGameOver= true;
                        }
                        else
                        {
                            Toast.makeText(getApplication(),"Player Two Won", Toast.LENGTH_SHORT).show();
                            myInfo.setText("Player Two Wins");
                            AndroidCounter++;
                            myPlayertwo.setText(Integer.toString(AndroidCounter));
                            myGameOver= true;
                        }
                    }

                }
            }
        }
    }

    private void setMove(char player , int location )
    {
        myGame.set_move(player, location);
        myboardbutton[location].setEnabled(false);
        //myboardbutton[location].setText(String.valueOf(player));
        if(player == myGame.Player_one)
        {
          //  myboardbutton[location].setTextColor(Color.GREEN);
                myboardbutton[location].setBackgroundDrawable(getResources().getDrawable(R.drawable.x));

        }
        else
        {
            //myboardbutton[location].setTextColor(Color.RED);
            myboardbutton[location].setBackgroundDrawable(getResources().getDrawable(R.drawable.z));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.new_game:
                startNewGame(msinglePlayer);
                break;
            case R.id.exit_game:
                Tic_tac_toe.this.finish();
                break;


        }
        myGameOver=false;
        return super.onOptionsItemSelected(item);
    }
}
