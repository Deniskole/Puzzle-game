package com.barleybreak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayAgain extends AppCompatActivity {

    private int placeSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_again);
    }

    public void newGame(View view) {
        switch (view.getId()) {
            case R.id.button3: {
                placeSize = 3;
                break;
            }
            case R.id.button4: {
                placeSize = 4;
                break;
            }
            case R.id.button5: {
                placeSize = 5;
                break;
            }
            case R.id.button6: {
                placeSize = 6;
                break;
            }
            default: {
                placeSize = 7;
                break;
            }
        }
        newGame();
    }


    public void newGame() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("placeSize", placeSize);
        startActivity(intent);
    }

}
