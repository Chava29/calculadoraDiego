package com.example.actcalcu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        rootView = findViewById(R.id.root);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (view, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return windowInsets;
        });

        int[] buttons = {R.id.botregresa, R.id.botonsaludo, R.id.botoncalculadora};
        for (int id : buttons) {
            View button = findViewById(id);
            if (button != null) {
                button.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.botonsaludo) {
            showAnotherGreeting();
        } else if (id == R.id.botregresa) {
            finish();
        } else if (id == R.id.botoncalculadora) {
            startActivity(new Intent(this, MainActivity3.class));
        }
    }

    private void showAnotherGreeting() {
        Saluditos saludito = new Saluditos();
        Snackbar.make(rootView, saludito.otrosaludo(), Snackbar.LENGTH_SHORT).show();
    }
}
