package ru.netology.lists;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView list = findViewById(R.id.list);

        String[] values = prepareContent();

        BaseAdapter listContentAdapter = createAdapter(values);

        list.setAdapter(listContentAdapter);
    }

    @NonNull
    private BaseAdapter createAdapter(String[] values) {
        return new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
    }

    @NonNull
    private String[] prepareContent() {
        return getString(R.string.large_text).split("\n\n");
    }
}
