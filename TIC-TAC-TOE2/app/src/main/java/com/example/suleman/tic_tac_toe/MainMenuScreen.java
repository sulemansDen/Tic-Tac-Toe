package com.example.suleman.tic_tac_toe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by suleman on 10/3/16.
 */
public class MainMenuScreen   extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_menu);

        ((Button)findViewById(R.id.one_b)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG", "One Player Button pressed");
                Intent i = new Intent(MainMenuScreen.this, Tic_tac_toe.class);
                i.putExtra("Game Type", true);
                startActivityForResult(i, 0);

            }
        });
        ((Button)findViewById(R.id.two_b)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG","Two Player Button pressed");
                Intent i =new Intent(MainMenuScreen.this,Tic_tac_toe.class);
                i.putExtra("Game Type",false);
                startActivityForResult(i,0);

            }
        });
        ((Button)findViewById(R.id.exit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DEBUG","One Player Button pressed");
                MainMenuScreen.this.finish();

            }
        });
    }
}
