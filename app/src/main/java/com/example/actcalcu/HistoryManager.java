package com.example.actcalcu;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class that stores calculator history entries using {@link SharedPreferences}.
 */
public final class HistoryManager {

    private static final String PREFS_NAME = "calculator_history";
    private static final String KEY_HISTORY = "entries";
    private static final int MAX_ENTRIES = 30;

    private HistoryManager() {
        // No instances.
    }

    public static void addEntry(Context context, String entry) {
        List<String> history = new ArrayList<>(getHistory(context));
        history.add(0, entry);
        if (history.size() > MAX_ENTRIES) {
            history = history.subList(0, MAX_ENTRIES);
        }
        persist(context, history);
    }

    public static List<String> getHistory(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String raw = preferences.getString(KEY_HISTORY, "[]");
        List<String> history = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(raw);
            for (int i = 0; i < array.length(); i++) {
                history.add(array.getString(i));
            }
        } catch (JSONException ignored) {
            // If parsing fails we simply return an empty history.
        }
        return history;
    }

    public static void clear(Context context) {
        persist(context, Collections.emptyList());
    }

    private static void persist(Context context, List<String> history) {
        JSONArray array = new JSONArray();
        for (String entry : history) {
            array.put(entry);
        }
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_HISTORY, array.toString())
                .apply();
    }
}
