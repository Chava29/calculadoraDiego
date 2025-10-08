package com.example.actcalcu;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Base activity that centralises common calculator behaviour such as validation, formatting
 * and sharing. Concrete subclasses only need to provide the operation logic and the
 * corresponding labels.
 */
public abstract class BaseOperationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout inputOneLayout;
    private TextInputLayout inputTwoLayout;
    private TextInputEditText inputOne;
    private TextInputEditText inputTwo;
    private MaterialButton calculateButton;
    private MaterialButton clearButton;
    private MaterialButton shareButton;
    private TextView resultLabel;
    private View rootView;
    private String lastFormattedResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(getLayoutResId());
        setupOperationUi();
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract String getOperationTitle();

    protected abstract String getOperationSymbol();

    protected abstract double resolveOperation(double first, double second);

    protected void onResultCalculated(double first, double second, double result) {
        // Hook for subclasses if they want to add custom behaviour.
    }

    private void setupOperationUi() {
        rootView = findViewById(R.id.root);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(getOperationTitle());
            toolbar.setNavigationOnClickListener(v -> finish());
        }

        ViewCompat.setOnApplyWindowInsetsListener(rootView, (view, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return windowInsets;
        });

        inputOneLayout = findViewById(R.id.input_one_layout);
        inputTwoLayout = findViewById(R.id.input_two_layout);
        inputOne = findViewById(R.id.input_one);
        inputTwo = findViewById(R.id.input_two);
        calculateButton = findViewById(R.id.calculate_button);
        clearButton = findViewById(R.id.clear_button);
        shareButton = findViewById(R.id.share_button);
        resultLabel = findViewById(R.id.result_label);

        inputTwo.setImeOptions(EditorInfo.IME_ACTION_DONE);

        calculateButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);
        shareButton.setEnabled(false);

        updateResultPlaceholder();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.calculate_button) {
            onCalculateClicked();
        } else if (id == R.id.clear_button) {
            onClearClicked();
        } else if (id == R.id.share_button) {
            onShareClicked();
        }
    }

    private void onCalculateClicked() {
        Double first = validateAndParse(inputOneLayout, inputOne);
        Double second = validateAndParse(inputTwoLayout, inputTwo);
        if (first == null || second == null) {
            return;
        }

        try {
            double result = resolveOperation(first, second);
            String formattedFirst = formatNumber(first);
            String formattedSecond = formatNumber(second);
            lastFormattedResult = formatNumber(result);

            String historyEntry = getString(
                    R.string.history_entry_template,
                    formattedFirst,
                    getOperationSymbol(),
                    formattedSecond,
                    lastFormattedResult
            );

            resultLabel.setText(getString(R.string.result_template, lastFormattedResult));
            shareButton.setEnabled(true);
            HistoryManager.addEntry(this, historyEntry);
            onResultCalculated(first, second, result);
        } catch (IllegalArgumentException exception) {
            inputTwoLayout.setError(exception.getMessage());
        }
    }

    private void onClearClicked() {
        inputOneLayout.setError(null);
        inputTwoLayout.setError(null);
        inputOne.setText(null);
        inputTwo.setText(null);
        lastFormattedResult = null;
        shareButton.setEnabled(false);
        updateResultPlaceholder();
        showMessage(getString(R.string.cleared_message));
    }

    private void onShareClicked() {
        if (TextUtils.isEmpty(lastFormattedResult)) {
            showMessage(getString(R.string.error_no_result));
            return;
        }

        String message = getString(
                R.string.share_result_template,
                getOperationTitle().toLowerCase(Locale.getDefault()),
                lastFormattedResult
        );

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_title)));
    }

    private Double validateAndParse(TextInputLayout layout, TextInputEditText input) {
        layout.setError(null);
        String text = input.getText() != null ? input.getText().toString().trim() : "";
        if (text.isEmpty()) {
            layout.setError(getString(R.string.error_empty_field));
            return null;
        }

        try {
            return Double.parseDouble(text.replace(',', '.'));
        } catch (NumberFormatException exception) {
            layout.setError(getString(R.string.error_invalid_number));
            return null;
        }
    }

    private void updateResultPlaceholder() {
        resultLabel.setText(getString(R.string.result_placeholder));
    }

    protected String formatNumber(double value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#.########", symbols);
        String formatted = decimalFormat.format(value);
        if (formatted.contains(".")) {
            // Replace decimal separator with locale if needed.
            char separator = DecimalFormatSymbols.getInstance().getDecimalSeparator();
            formatted = formatted.replace('.', separator);
        }
        return formatted;
    }

    protected void copyToClipboard(String label, String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard == null) {
            return;
        }
        ClipData clip = ClipData.newPlainText(label, text);
        clipboard.setPrimaryClip(clip);
    }

    protected void showMessage(String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
    }
}
