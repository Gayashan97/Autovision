package com.game.autovision;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IdentifyActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Spinner spinnerCars;
    private TextView txt1,txt2,timer;
    private int[] images = new int[] {R.drawable.car1, R.drawable.car2, R.drawable.car3, R.drawable.car4,R.drawable.car5,R.drawable.car6,R.drawable.car7,R.drawable.car8,R.drawable.car9,R.drawable.car10,R.drawable.car11,R.drawable.car12,R.drawable.car13,R.drawable.car14,R.drawable.car15,R.drawable.car16,R.drawable.car17,R.drawable.car18,R.drawable.car19,R.drawable.car20};
    private Button btn;
    private CountDownTimer countDownTimer;
    private String make;
    boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);

        mImageView = findViewById(R.id.img_view);
        txt1= findViewById(R.id.txt_answer);
        txt2= findViewById(R.id.txt_correct);
        btn=findViewById(R.id.btn_iden);
        timer=findViewById(R.id.txt_timer);
        isChecked = getIntent().getBooleanExtra("switch", false);

        int carId=setImage();

        int carNo=getResources().getIdentifier("car"+(carId+1),"string","com.game.autovision");
        make=getString(carNo);

        createSpinner();
        if (isChecked){
            displayTimer();
        }
    }

    public void createSpinner(){
        spinnerCars=findViewById(R.id.spinnerCars);
        String[] cars= getResources().getStringArray(R.array.cars_array);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,cars);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerCars.setAdapter(arrayAdapter);
    }

    public int setImage(){
        // Get a random between 0 and images.length-1
        int carId = (int)(Math.random() * images.length);
        // Set the image
        mImageView.setImageResource(images[carId]);
        return carId;
    }

    public void identifyCar(View view) {
        if (spinnerCars.getSelectedItem().toString().equals(make)){
            txt1.setText("CORRECT");
            txt1.setTextColor(Color.GREEN);
            txt1.setTextSize(TypedValue.COMPLEX_UNIT_PT,20);
            if(isChecked)
                countDownTimer.cancel();
            displayNext();
        }
        else{
            txt1.setText("WRONG");
            txt1.setTextColor(Color.RED);
            txt1.setTextSize(TypedValue.COMPLEX_UNIT_PT,20);
            txt2.setText("ANSWER : "+make);
            txt2.setTextColor(Color.YELLOW);
            txt2.setTextSize(TypedValue.COMPLEX_UNIT_PT,15);
            if(isChecked)
                countDownTimer.cancel();
            displayNext();
        }
    }

    public void displayNext(){
        btn.setText("NEXT");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(IdentifyActivity.this, IdentifyActivity.class);
                intent.putExtra("switch", isChecked);
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
                txt1.setText("WRONG");
                txt1.setTextColor(Color.RED);
                txt1.setTextSize(TypedValue.COMPLEX_UNIT_PT,20);
                txt2.setText("ANSWER : "+make);
                txt2.setTextColor(Color.YELLOW);
                txt2.setTextSize(TypedValue.COMPLEX_UNIT_PT,15);
                displayNext();
            }

         }.start();
    }
}

//todo: images array to be initialized using loop
