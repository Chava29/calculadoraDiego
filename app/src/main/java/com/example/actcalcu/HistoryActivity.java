package com.example.actcalcu;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);

        rootView = findViewById(R.id.root);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (view, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return windowInsets;
        });

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        ListView listView = findViewById(R.id.history_list);
        MaterialTextView emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        List<String> history = HistoryManager.getHistory(this);
        adapter = new ArrayAdapter<>(this, R.layout.item_history, R.id.history_text, history);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String entry = adapter.getItem(position);
                if (entry == null) {
                    return;
                }
                copyEntryToClipboard(entry);
                Snackbar.make(rootView, R.string.history_copied, Snackbar.LENGTH_SHORT).show();
            }
        });

        MaterialButton clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(v -> clearHistory());
    }

    private void clearHistory() {
        HistoryManager.clear(this);
        adapter.clear();
        adapter.notifyDataSetChanged();
        Snackbar.make(rootView, R.string.history_cleared, Snackbar.LENGTH_SHORT).show();
    }

    private void copyEntryToClipboard(String entry) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard == null) {
            return;
        }
        clipboard.setPrimaryClip(ClipData.newPlainText(getString(R.string.history_clip_label), entry));
    }
}
