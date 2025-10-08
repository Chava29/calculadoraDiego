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

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {

    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        rootView = findViewById(R.id.root);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (view, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return windowInsets;
        });

        int[] buttons = {
                R.id.botsuma,
                R.id.botresta,
                R.id.botmultiplicacion,
                R.id.botdivision,
                R.id.button_history,
                R.id.botreturn
        };

        for (int id : buttons) {
            View button = findViewById(id);
            if (button != null) {
                button.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.botsuma) {
            openActivity(Suma.class);
        } else if (id == R.id.botresta) {
            openActivity(Resta.class);
        } else if (id == R.id.botmultiplicacion) {
            openActivity(Multiplicacion.class);
        } else if (id == R.id.botdivision) {
            openActivity(Division.class);
        } else if (id == R.id.button_history) {
            openActivity(HistoryActivity.class);
        } else if (id == R.id.botreturn) {
            finish();
        }
    }

    private void openActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }
}
