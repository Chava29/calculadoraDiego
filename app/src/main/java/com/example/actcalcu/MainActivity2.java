package com.example.actcalcu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button botregreso, bototrosaludo, botcalcu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        botregreso = findViewById(R.id.botregresa);
        bototrosaludo = findViewById(R.id.botonsaludo);
        botcalcu = findViewById(R.id.botoncalculadora);

        botregreso.setOnClickListener(this);
        bototrosaludo.setOnClickListener(this);
        botcalcu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String cadenita = ((Button)v).getText().toString();
        if (cadenita.equals("otro saludo")){
            //creamos el objeto Saluditos para que de la calse Saluditos nos la "regrese"
            Saluditos saludito = new Saluditos();

            Toast.makeText(this,
                    saludito.otrosaludo(), Toast.LENGTH_SHORT).show();
        }
        else
        if (cadenita.equals("regresar")){
            //a traves de este intent me voy a ir a la sig actividad
            Intent intentito = new Intent(this, MainActivity.class);
            startActivity(intentito);
        }
        else
        if (cadenita.equals("pasar a calculadora")){
            Intent intentito = new Intent(this, MainActivity3.class);
            startActivity(intentito);
        }
    }
}