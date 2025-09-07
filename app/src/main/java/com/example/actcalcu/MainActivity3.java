package com.example.actcalcu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    Button bots, botr, botm, botdv;
    Button botgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        bots = findViewById(R.id.botsuma);
        botr = findViewById(R.id.botresta);
        botm = findViewById(R.id.botmultiplicacion);
        botdv = findViewById(R.id.botdivision);
        botgreso = findViewById(R.id.botreturn);

        bots.setOnClickListener(this);
        botr.setOnClickListener(this);
        botm.setOnClickListener(this);
        botdv.setOnClickListener(this);
        botgreso.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String cadenita = ((Button) view).getText().toString();
        if (cadenita.equals("suma")) {
            Intent intentito = new Intent(this, Suma.class);
            startActivity(intentito);

        } else if (cadenita.equals("resta")) {
            Intent intentito = new Intent(this, Resta.class);
            startActivity(intentito);

        } else if (cadenita.equals("multiplicacion")) {
            Intent intentito = new Intent(this, Multiplicacion.class);
            startActivity(intentito);

        } else if (cadenita.equals("division")) {
            Intent intentito = new Intent(this, Division.class);
            startActivity(intentito);
        } else if (cadenita.equals("regresar")){
            Intent intentito = new Intent(this, MainActivity2.class);
            startActivity(intentito);
        }
    }
}