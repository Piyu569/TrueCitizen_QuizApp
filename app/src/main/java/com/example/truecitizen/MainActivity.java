package com.example.truecitizen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.truecitizen.databinding.ActivityMainBinding;
import com.example.truecitizen.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int current = 0;
    private Question[] questionBank = new Question[]{
            // create and intantiate question objects

            new Question(R.string.question_amendments, false),
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.textView2.setText(questionBank[current].getAnswerResId());
        binding.trueButton.setOnClickListener(view -> checkAnswer(true));
        binding.button4.setOnClickListener(view -> checkAnswer(false));

        binding.nextButton.setOnClickListener(view -> {
            // the idea here is ver simple to after array reaching its  end it should not show
            // out of bond after reaching in last position it comes back to the first
            current = (current + 1) % questionBank.length;
            updateQuestion();
        });
        binding.prevButton.setOnClickListener(view -> {
            current = (current - 1) % questionBank.length;
            updateQuestion();
        });
    }

    private void checkAnswer(boolean userAnswer) {
        boolean AnswerIsCorrect = questionBank[current].isAnswerTrue();
        int msgid;
        if (AnswerIsCorrect == userAnswer) {
            msgid = R.string.correct_answer;
        } else {
            msgid = R.string.wrong_answer;
        }
        Snackbar.make(binding.imageView, msgid, Snackbar.LENGTH_SHORT).show();
    }

    private void updateQuestion() {
        binding.textView2.setText(questionBank[current].getAnswerResId());
    }
}