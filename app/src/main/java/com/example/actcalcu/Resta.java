package com.example.actcalcu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Resta extends AppCompatActivity implements View.OnClickListener {
    EditText texto1, texto2;
    Button botonresultado, botregresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resta);

        texto1 = findViewById(R.id.texto1);
        texto2 = findViewById(R.id.texto2);

        botonresultado = findViewById(R.id.botresultado);
        botregresa = findViewById(R.id.botregreso);

        botonresultado.setOnClickListener(this);
        botregresa.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        String cadenita = ((Button)view).getText().toString();

        Clasesita objetito = new Clasesita();

        objetito.setNumero1(Integer.parseInt(texto1.getText().toString()));
        objetito.setNumero2(Integer.parseInt(texto2.getText().toString()));

        if (cadenita.equals("resultado")){
            Toast.makeText(this, "El resultado es: " + objetito.restita(),
                    Toast.LENGTH_SHORT).show();
        }
        else
            if(cadenita.equals("regresar")){
                Intent intentito = new Intent(this, MainActivity3.class);
                startActivity(intentito);
            }
    }
}