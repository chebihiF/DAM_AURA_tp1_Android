package org.m2i.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random(); // class random => generer nombres aleatoires ...
    TextView nbr1_txt, nbr2_txt;

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

        int n1 = random.nextInt(200); // générer un nombre aleatoire entre 0 - 200
        int n2 = random.nextInt(200); // générer un nombre aleatoire entre 0 - 200

        nbr1_txt.setText(String.valueOf(n1));
        nbr2_txt.setText(""+n2);
    }
}