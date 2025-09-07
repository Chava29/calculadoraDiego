package com.example.actcalcu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity
        extends AppCompatActivity implements View.OnClickListener {
    Button botonsaludar, botonsigact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        botonsaludar = findViewById(R.id.boton);
        botonsigact = findViewById(R.id.otroboton);

        botonsaludar.setOnClickListener(this);
        botonsigact.setOnClickListener(this);
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
                Intent intentito = new
                        Intent(this, MainActivity2.class);
                startActivity(intentito);
            }
    }
}