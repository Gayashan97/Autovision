package com.game.autovision;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Random;

public class ImagesActivity extends AppCompatActivity {

    private ImageButton img1,img2,img3;
    private int[] images = new int[] {R.drawable.car1, R.drawable.car2, R.drawable.car3, R.drawable.car4,R.drawable.car5,R.drawable.car6,R.drawable.car7,R.drawable.car8,R.drawable.car9,R.drawable.car10,R.drawable.car11,R.drawable.car12,R.drawable.car13,R.drawable.car14,R.drawable.car15,R.drawable.car16,R.drawable.car17,R.drawable.car18,R.drawable.car19,R.drawable.car20};
    private TextView txtMake,txtBox,timer;
    private Button btn;
    private CountDownTimer countDownTimer;
    private String brand,brand1,brand2,brand3;
    private boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        txtMake=findViewById(R.id.txt_make);
        txtBox=findViewById(R.id.txt_box);
        btn=findViewById(R.id.btn_nxt);
        timer=findViewById(R.id.images_timer);
        isChecked = getIntent().getBooleanExtra("switch", false);

        setImages();
        checkAnswer();
        if (isChecked){
            displayTimer();
        }
        displayNext();

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

        System.out.println(car1 + " "+car2+" "+car3);

        int idx1=getResources().getIdentifier("car"+(car1),"string","com.game.autovision");
        int idx2=getResources().getIdentifier("car"+(car2),"string","com.game.autovision");
        int idx3=getResources().getIdentifier("car"+(car3),"string","com.game.autovision");

        System.out.println(idx1);

        brand1=getString(idx1);
        brand2=getString(idx2);
        brand3=getString(idx3);

        if (brand1.equals(brand2) || brand1.equals(brand3) || brand2.equals(brand3)){
            setImages();
        }

        System.out.println(brand1);
        System.out.println(brand2);
        System.out.println(brand3);

        img1.setImageResource(images[car1]);
        img2.setImageResource(images[car2]);
        img3.setImageResource(images[car3]);

        int[] cars={idx1,idx2,idx3};
        displayMake(cars);
    }

    public void displayMake(int[] cars){
        int idx = new Random().nextInt(cars.length);
        brand=getString(cars[idx]);
        txtMake.setText(brand);
    }

    public void checkAnswer(){

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChecked)
                    countDownTimer.cancel();
                if (brand.equals(brand1)){
                    txtBox.setText("CORRECT");
                    txtBox.setTextColor(Color.GREEN);
                }else{
                    txtBox.setText("WRONG");
                    txtBox.setTextColor(Color.RED);
                }
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChecked)
                    countDownTimer.cancel();
                if (brand.equals(brand2)){
                    txtBox.setText("CORRECT");
                    txtBox.setTextColor(Color.GREEN);
                }else{
                    txtBox.setText("WRONG");
                    txtBox.setTextColor(Color.RED);
                }
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChecked)
                    countDownTimer.cancel();
                if (brand.equals(brand3)){
                    txtBox.setText("CORRECT");
                    txtBox.setTextColor(Color.GREEN);
                }else{
                    txtBox.setText("WRONG");
                    txtBox.setTextColor(Color.RED);
                }
            }
        });
    }

    public void displayNext(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(ImagesActivity.this, ImagesActivity.class);
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
                txtBox.setText("WRONG");
                txtBox.setTextColor(Color.RED);
                displayNext();
            }
        }.start();
    }


}