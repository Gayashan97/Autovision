package com.game.autovision;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AdvancedActivity extends AppCompatActivity {

    private ImageView img1,img2,img3;
    private EditText edit1,edit2,edit3;
    private TextView lbl1,lbl2,lbl3,ans,txt_score,timer;
    private Button btn;
    private int make1,make2,make3;
    private int[] images = new int[] {R.drawable.car1, R.drawable.car2, R.drawable.car3, R.drawable.car4,R.drawable.car5,R.drawable.car6,R.drawable.car7,R.drawable.car8,R.drawable.car9,R.drawable.car10,R.drawable.car11,R.drawable.car12,R.drawable.car13,R.drawable.car14,R.drawable.car15,R.drawable.car16,R.drawable.car17,R.drawable.car18,R.drawable.car19,R.drawable.car20};
    private int count,score;
    private boolean check1,check2,check3;
    private CountDownTimer countDownTimer;
    private boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);
        img1=findViewById(R.id.imgview1);
        img2=findViewById(R.id.imgview2);
        img3=findViewById(R.id.imgview3);
        edit1=findViewById(R.id.txt_img1);
        edit2=findViewById(R.id.txt_img2);
        edit3=findViewById(R.id.txt_img3);
        lbl1=findViewById(R.id.lbl_img1);
        lbl2=findViewById(R.id.ibl_img2);
        lbl3=findViewById(R.id.lbl_img3);
        ans=findViewById(R.id.txt_ans);
        txt_score=findViewById(R.id.txt_score);
        setImages();
        timer=findViewById(R.id.advanced_timer);
        isChecked = getIntent().getBooleanExtra("switch", false);
        btn=findViewById(R.id.btn_sub);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChecked)
                    countDownTimer.cancel();
                txt_score.setVisibility(View.VISIBLE);
                count++;
                if (count==3){
                    ans.setText("WRONG");
                    ans.setTextColor(Color.RED);
                    displayAnswers();
                    displayNext();
                }
                displayTimer();
                checkAnswers();
                displayScore();
            }
        });
        if (isChecked){
            displayTimer();
        }
    }

    public void setImages(){
        // Get a random between 0 and images.length-1
        int car1 = (int)(Math.random() * images.length);
        int car2,car3;

        do{
            car2 = (int)(Math.random() * images.length);
        }while(car2==car1);

        do {
            car3=(int)(Math.random() * images.length);
        }while (car3==car2 || car3==car1);

        img1.setImageResource(this.images[car1]);
        img2.setImageResource(this.images[car2]);
        img3.setImageResource(this.images[car3]);

        make1=getResources().getIdentifier("car"+(car1+1),"string","com.game.autovision");
        make2=getResources().getIdentifier("car"+(car2+1),"string","com.game.autovision");
        make3=getResources().getIdentifier("car"+(car3+1),"string","com.game.autovision");

    }

    public void checkAnswers(){

        if (edit1.getText().toString().equalsIgnoreCase(getString(make1)) && !check1) {
            edit1.setEnabled(false);
            edit1.setBackgroundColor(Color.GREEN);
            score++;
            check1 = true;
        }
        else
            edit1.setBackgroundColor(Color.RED);

        if (edit2.getText().toString().equalsIgnoreCase(getString(make2)) && !check2) {
            edit2.setEnabled(false);
            edit2.setBackgroundColor(Color.GREEN);
            score++;
            check2 = true;
        }
        else
            edit2.setBackgroundColor(Color.RED);

        if (edit3.getText().toString().equalsIgnoreCase(getString(make3)) && !check3) {
            edit3.setEnabled(false);
            edit3.setBackgroundColor(Color.GREEN);
            score++;
            check3 = true;
        }
        else
            edit3.setBackgroundColor(Color.RED);

        if(score==3){
            ans.setText("CORRECT");
            ans.setTextColor(Color.GREEN);
        }
    }

    public void displayAnswers(){
        lbl1.setText(getString(make1));
        lbl1.setTextColor(Color.YELLOW);
        lbl2.setText(getString(make2));
        lbl2.setTextColor(Color.YELLOW);
        lbl3.setText(getString(make3));
        lbl3.setTextColor(Color.YELLOW);
    }

    public void displayScore(){
        txt_score.setText(Integer.toString(score));
    }

    public void displayNext(){
        btn.setText("NEXT");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(AdvancedActivity.this, AdvancedActivity.class);
                intent.putExtra("switch",isChecked);
                startActivity(intent);
            }
        });
    }

    public void displayTimer(){
        countDownTimer=new CountDownTimer(21000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("Time Left : " + millisUntilFinished / 1000+"s" );
            }

            public void onFinish() {
                timer.setText("Time Out!");
                ans.setText("WRONG");
                ans.setTextColor(Color.RED);
                displayAnswers();
                displayNext();
            }
        }.start();
    }
}