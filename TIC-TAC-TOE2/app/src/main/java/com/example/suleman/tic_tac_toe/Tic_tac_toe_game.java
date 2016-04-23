package com.example.suleman.tic_tac_toe;

import java.util.Random;

/**
 * Created by suleman on 19/11/15.
 */
public class Tic_tac_toe_game {

    private char mBoard[];
    private static final int BOARD_SIZE=9;

    public static final char Player_one = 'X';
    public static final char Player_two = 'O';
    public static final char EMPTY_SPACE = ' ';

    private Random mRand;

    public static int getBoardSize()
    {
        return BOARD_SIZE;
    }

    public Tic_tac_toe_game()
    {
        mBoard = new char[BOARD_SIZE];
        for (int i=0;i<BOARD_SIZE;i++)
        {
            mBoard[i] = EMPTY_SPACE;
            mRand = new Random();
        }
    }

    public void clearBoard()
    {
        for (int i=0;i<BOARD_SIZE;i++) {
            mBoard[i] = EMPTY_SPACE;
        }
    }

    public void set_move(char player , int location )
    {
        mBoard[location] = player;
    }

    public int computer_move() {
        int move;

        for (int i = 0; i < getBoardSize(); i++) {
            if (mBoard[i] != Player_one && mBoard[i] != Player_two) {
                char temp = mBoard[i];
                mBoard[i] = Player_two;
                if (check_for_winner() == 3) {
                    set_move(Player_two, i);
                    return i;
                } else {
                    mBoard[i] = temp;
                }
            }
        }

        for (int i = 0; i < getBoardSize(); i++) {
            if (mBoard[i] != Player_one && mBoard[i] != Player_two) {
                char temp = mBoard[i];
                mBoard[i] = Player_one;
                if (check_for_winner() == 2) {
                    set_move(Player_one, i);
                    return i;
                } else {
                    mBoard[i] = temp;
                }
            }
        }

        do {
            move = mRand.nextInt(getBoardSize());
        }
        while (mBoard[move] == Player_one || mBoard[move] == Player_two);

        set_move(Player_two, move);
        return move;

    }
    public int check_for_winner()
    {
        for (int i=0 ; i<=6 ; i+=3)
        {
            if(mBoard[i] == Player_one &&
                    mBoard[i+1] == Player_one &&
                    mBoard[i+2] == Player_one)
                return 2;

            if(mBoard[i] == Player_two &&
                    mBoard[i+1] == Player_two &&
                    mBoard[i+2] == Player_two)
                return 3;
        }

        for(int i=0;i<=2;i++)
        {
            if(mBoard[i] == Player_one &&
                    mBoard[i+3] == Player_one &&
                    mBoard[i+6] == Player_one)
                return 2;

            if(mBoard[i] == Player_two &&
                    mBoard[i+3] == Player_two &&
                    mBoard[i+6] == Player_two)
                return 3;

            if (mBoard[0] == Player_one &&
                    mBoard[4] == Player_one &&
                    mBoard[8] == Player_one ||
                    mBoard[2] == Player_one &&
                    mBoard[4] == Player_one &&
                    mBoard[6] == Player_one)
                return 2;

            if (mBoard[0] == Player_two &&
                    mBoard[4] == Player_two &&
                    mBoard[8] == Player_two ||
                    mBoard[2] == Player_two &&
                            mBoard[4] == Player_two &&
                            mBoard[6] == Player_two)
                return 3;

        }
        for(int i=0 ; i<getBoardSize() ; i++)
        {
            if (mBoard[i] !=Player_one && mBoard[i]!=Player_two)
                return 0;
        }
        return 1;
    }
}













