package com.game.autovision;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;

public class HomeActivity extends AppCompatActivity {

    private Button button1,button2,button3,button4;
    private Switch timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer=findViewById(R.id.timer);

        button1 = findViewById(R.id.btn1);
        button1.setOnClickListener(v -> openFirstActivity());

        button2 = findViewById(R.id.btn2);
        button2.setOnClickListener(v -> openSecondActivity());

        button3 = findViewById(R.id.btn3);
        button3.setOnClickListener(v -> openThirdActivity());

        button4 = findViewById(R.id.btn4);
        button4.setOnClickListener(v -> openFourthActivity());
    }

    public void openFirstActivity() {
        Intent intent = new Intent(this, IdentifyActivity.class);
        intent.putExtra("switch", timer.isChecked());
        startActivity(intent);
    }

    public void openSecondActivity() {
        Intent intent = new Intent(this, HintActivity.class);
        intent.putExtra("switch", timer.isChecked());
        startActivity(intent);
    }

    public void openThirdActivity() {
        Intent intent = new Intent(this, ImagesActivity.class);
        intent.putExtra("switch", timer.isChecked());
        startActivity(intent);
    }

    public void openFourthActivity() {
        Intent intent = new Intent(this, AdvancedActivity.class);
        intent.putExtra("switch", timer.isChecked());
        startActivity(intent);
    }
}