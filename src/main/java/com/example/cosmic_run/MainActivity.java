package com.example.cosmic_run;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView playerImageView;
    private TextView scoreTextView;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerImageView = findViewById(R.id.playerImageView);
        scoreTextView = findViewById(R.id.scoreTextView);

        // Implement touch listener for jumping
        View mainLayout = findViewById(android.R.id.content);
        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    jump();
                    return true;
                }
                return false;
            }
        });
        updateScore();
    }

    private void jump() {
        // Implement jump logic here
        // For now, just move the player up slightly
        playerImageView.animate().translationY(-100).setDuration(500).withEndAction(new Runnable() {
            @Override
            public void run() {
                playerImageView.animate().translationY(0).setDuration(500).start();
            }
        }).start();
    }

    private void updateScore() {
        score++;
        scoreTextView.setText("Score: " + score);
        // Schedule the next score update
        playerImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateScore();
            }
        }, 100); // Update score every 100 milliseconds
    }
}