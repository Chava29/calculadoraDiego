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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View rootView;
    private Saluditos saluditos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        saluditos = new Saluditos();
        rootView = findViewById(R.id.root);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (view, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return windowInsets;
        });

        int[] clickableIds = {R.id.button_greet, R.id.button_navigate, R.id.button_tip};
        for (int id : clickableIds) {
            View button = findViewById(id);
            if (button != null) {
                button.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_greet) {
            showGreeting();
        } else if (id == R.id.button_navigate) {
            openSecondActivity();
        } else if (id == R.id.button_tip) {
            showTipDialog();
        }
    }

    private void showGreeting() {
        String message = getString(R.string.greeting_toast, saluditos.saludo());
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
    }

    private void openSecondActivity() {
        startActivity(new Intent(this, MainActivity2.class));
    }

    private void showTipDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.tip_button)
                .setMessage(R.string.tip_message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
