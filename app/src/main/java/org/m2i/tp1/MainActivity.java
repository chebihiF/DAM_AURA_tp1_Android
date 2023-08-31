package org.m2i.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Random random = new Random(); // class random => generer nombres aleatoires ...
    TextView nbr1_txt, nbr2_txt, score_txt, timer_txt;
    Button resp1_btn, resp2_btn, resp3_btn, start_btn, stop_btn;

    CountDownTimer timer ;

    boolean game_started = false ;

    int score = 0, timer_unit = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart() {
        super.onStart();
        nbr1_txt = findViewById(R.id.nbr1);
        nbr2_txt = findViewById(R.id.nbr2);
        resp1_btn =findViewById(R.id.resp1);
        resp2_btn =findViewById(R.id.resp2);
        resp3_btn =findViewById(R.id.resp3);
        score_txt = findViewById(R.id.score);
        start_btn = findViewById(R.id.start);
        timer_txt = findViewById(R.id.timer_txt);
        stop_btn = findViewById(R.id.stop);


        resp1_btn.setOnClickListener(this);
        resp2_btn.setOnClickListener(this);
        resp3_btn.setOnClickListener(this);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_game();
                game_started = true;
                start_btn.setEnabled(false);
            }
        });

        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                nbr1_txt.setText("0");
                nbr2_txt.setText("0");
                resp1_btn.setText("0");
                resp2_btn.setText("0");
                resp3_btn.setText("0");
                score = 0 ;
                start_btn.setEnabled(true);
                timer_txt.setText("5");
            }
        });
    }

    public void start_game(){

        setTimer();
        timer_txt.setText(""+timer_unit);

        int n1 = random.nextInt(200); // générer un nombre aleatoire entre 0 - 200
        int n2 = random.nextInt(200); // générer un nombre aleatoire entre 0 - 200
        int resp = n1 + n2;
        int r1 = random.nextInt(400); // générer un nombre aleatoire entre 0 - 200
        int r2 = random.nextInt(400); // générer un nombre aleatoire entre 0 - 200
        int i = random.nextInt(3);

        nbr1_txt.setText(String.valueOf(n1));
        nbr2_txt.setText(""+n2);

        if(i == 0){
            resp1_btn.setText(resp+"");
            resp2_btn.setText(r1+"");
            resp3_btn.setText(r2+"");
        }else if (i == 1)
        {
            resp2_btn.setText(resp+"");
            resp1_btn.setText(r1+"");
            resp3_btn.setText(r2+"");
        }else {
            resp3_btn.setText(resp+"");
            resp2_btn.setText(r1+"");
            resp1_btn.setText(r2+"");
        }
    }

    @Override
    public void onClick(View v) {
        if(game_started) {

            timer.cancel();
            timer_unit = 5 ;

            Button btn = (Button) v;
            int resp = Integer.parseInt(btn.getText().toString());
            int nbr1 = Integer.parseInt(nbr1_txt.getText().toString());
            int nbr2 = Integer.parseInt(nbr2_txt.getText().toString());
            if (resp == nbr1 + nbr2)
                score++;
            else
                score--;
            score_txt.setText("Score : " + score);
            if(score > 0)
                score_txt.setTextColor(getResources().getColor(R.color.my_green));
            else if (score < 0)
                score_txt.setTextColor(getResources().getColor(R.color.my_red));


            start_game();
        }
    }

    public void setTimer(){
        timer = new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timer_unit -- ;
                timer_txt.setText(""+timer_unit);

            }

            @Override
            public void onFinish() {
                timer_unit = 5 ;
                score -- ;
                score_txt.setText("Score : " + score);
                if(score > 0)
                    score_txt.setTextColor(getResources().getColor(R.color.my_green));
                else if (score < 0)
                    score_txt.setTextColor(getResources().getColor(R.color.my_red));
                start_game();
            }
        };

        timer.start();
    }
}