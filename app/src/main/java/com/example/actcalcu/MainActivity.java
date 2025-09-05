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

public class MainActivity
        extends AppCompatActivity implements View.OnClickListener {
    Button botsaludo, botoact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        botsaludo = findViewById(R.id.boton);
        botoact = findViewById(R.id.otroboton);

        botsaludo.setOnClickListener(this);
        botoact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String cadenita = ((Button)v).getText().toString();
        if (cadenita.equals("saludo")){

            Saluditos saludito = new Saluditos();

            Toast.makeText(this,
                    saludito.saludo(), Toast.LENGTH_SHORT).show();
        }
        else
            if (cadenita.equals("otra actividad")){
                //a traves de este intent me voy a ir a la sig actividad
                Intent intentito = new
                        Intent(this, MainActivity2.class);
                startActivity(intentito);
            }
    }
}