package com.example.actcalcu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    Button botonsuma, botonresta, botonmulti, botondivi;
    Button botregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        botonsuma = findViewById(R.id.botsuma);
        botonresta = findViewById(R.id.botresta);
        botonmulti = findViewById(R.id.botmultiplicacion);
        botondivi = findViewById(R.id.botdivision);
        botregresar = findViewById(R.id.botreturn);

        botonsuma.setOnClickListener(this);
        botonresta.setOnClickListener(this);
        botonmulti.setOnClickListener(this);
        botondivi.setOnClickListener(this);
        botregresar.setOnClickListener(this);
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