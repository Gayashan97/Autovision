package com.game.autovision;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView,timer;
    private EditText editText;
    private Button button;
    private String brand;
    private int[] images = new int[] {R.drawable.car1, R.drawable.car2, R.drawable.car3, R.drawable.car4,R.drawable.car5,R.drawable.car6,R.drawable.car7,R.drawable.car8,R.drawable.car9,R.drawable.car10,R.drawable.car11,R.drawable.car12,R.drawable.car13,R.drawable.car14,R.drawable.car15,R.drawable.car16,R.drawable.car17,R.drawable.car18,R.drawable.car19,R.drawable.car20};
    private int count;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        imageView=findViewById(R.id.img_hint);
        textView=findViewById(R.id.txt_dashes);
        editText=findViewById(R.id.txt_input);
        button=findViewById(R.id.btn_check);
        timer=findViewById(R.id.hints_timer);
        boolean isChecked = getIntent().getBooleanExtra("switch", false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                containsLetter();
            }
        });
        setImage();
        setDashes();
        if (isChecked){
            displayTimer();
        }
    }

    public void setImage(){
        // Get a random between 0 and images.length-1
        int rand= (int) (Math.random() * images.length);
        int brandno=getResources().getIdentifier("car"+(rand+1),"string","com.game.autovision");
        brand=getString(brandno);
        Log.e("tag","make "+brand);
        imageView.setImageResource(images[rand]);
    }

    public void setDashes(){
        int count=brand.length();
        String str="";
        for (int i=0;i<count;i++){
            str=str.concat("_ ");
        }
        textView.setText(str);
    }

    public String getCharacter(){
        String word = editText.getText().toString().toUpperCase();
        return word;
    }

    public void containsLetter(){

        String word=textView.getText().toString();
        char letter=getCharacter().charAt(0);
        StringBuilder stringBuilder=new StringBuilder(word);


        if(brand.contains(getCharacter())){
            for (int i=0;i<brand.length();i++){
                {
                    if (brand.charAt(i)==letter){
                        stringBuilder.replace(i,i+1,getCharacter());
                    }
                }
            }
        }
        textView.setText(stringBuilder);
    }

    public void displayTimer(){
        countDownTimer=new CountDownTimer(21000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("Time Left : " + millisUntilFinished / 1000+"s" );
            }

            public void onFinish() {
                timer.setText("Time Out!");

            }
        }.start();
    }
}