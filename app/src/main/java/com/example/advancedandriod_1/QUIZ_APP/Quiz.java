package com.example.advancedandriod_1.QUIZ_APP;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.advancedandriod_1.R;

public class Quiz extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
    private int highscore;
   // SharedPreferences sharedPreferences;
    private TextView textViewHighscore;
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        textViewHighscore = findViewById(R.id.text_view_highscore);
        loadHighscore(); // you can put it in Onresume()

        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }
    private void startQuiz() {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivityForResult(intent,123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==123){
                if (resultCode == RESULT_OK) {
                    assert data != null;
                    int score=data.getIntExtra("highscores",0);
                    if (score > highscore) {
                        updateHighscore(score);
                    }
                }
            }
        }
    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }

// you can write this logic in onPause()
    private void updateHighscore(int score) {
        highscore = score;
        textViewHighscore.setText("Highscore: " + highscore);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}
