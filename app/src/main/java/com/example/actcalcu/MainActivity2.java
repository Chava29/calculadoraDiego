package com.example.actcalcu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button botonreturn, botonotrosaludo, botonparacal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        botonreturn = findViewById(R.id.botregresa);
        botonotrosaludo = findViewById(R.id.botonsaludo);
        botonparacal = findViewById(R.id.botoncalculadora);
        botonreturn.setOnClickListener(this);
        botonotrosaludo.setOnClickListener(this);
        botonparacal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String cadenita = ((Button)v).getText().toString();
        if (cadenita.equals("otro saludo")){
            Saluditos saludito = new Saluditos();

            Toast.makeText(this,
                    saludito.otrosaludo(), Toast.LENGTH_SHORT).show();
        }
        else
        if (cadenita.equals("regresar")){
            Intent intentito = new Intent(this, MainActivity.class);
            startActivity(intentito);
        }
        else
        if (cadenita.equals("calculadora")){
            Intent intentito = new Intent(this, MainActivity3.class);
            startActivity(intentito);
        }
    }
}