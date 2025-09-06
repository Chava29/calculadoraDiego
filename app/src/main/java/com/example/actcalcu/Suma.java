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

public class Suma extends AppCompatActivity implements View.OnClickListener {
    EditText textito1, textito2;
    Button botresultado, botregresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suma);

        textito1 = findViewById(R.id.texto1);
        textito2 = findViewById(R.id.texto2);

        botresultado = findViewById(R.id.botresultado);
        botregresa = findViewById(R.id.botregreso);

        botresultado.setOnClickListener(this);
        botregresa.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String cadenita =((Button)view).getText().toString();
        Clasesita objetito = new Clasesita();

        objetito.setDatito1(Integer.parseInt(textito1.getText().toString()));
        objetito.setDatito2(Integer.parseInt(textito2.getText().toString()));

        if (cadenita.equals("resultado"))
            Toast.makeText(this, "la suma es: " + objetito.sumita(), Toast.LENGTH_SHORT).show();
        else
            if(cadenita.equals("regresar")){
                Intent intentito = new Intent(this, MainActivity3.class);
                startActivity(intentito);
            }
    }
}