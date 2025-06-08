package com.example.belajarsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBConfig config;
    SQLiteDatabase db;
    Cursor cursor;

    RecyclerView rcvData;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layout;

    ArrayList idList, titleList;

    ImageView linkAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        config = new DBConfig(this);

        linkAdd = findViewById(R.id.link_add);

        linkAdd.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
        });
    }

    @Override
    protected void onResume() {
        showData();
        super.onResume();
    }

    // Ubah aksesnya dari private menjadi public
    public void showData() {
        idList = new ArrayList<>();
        titleList = new ArrayList<>();

        layout = new LinearLayoutManager(this);
        adapter = new DataAdapter(this, idList, titleList);
        rcvData = findViewById(R.id.rcv_data);

        rcvData.setLayoutManager(layout);
        rcvData.setHasFixedSize(true);
        rcvData.setAdapter(adapter);

        db = config.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_tugas", null);
        cursor.moveToFirst();

        for (int count = 0; count < cursor.getCount(); count++) {
            cursor.moveToPosition(count);
            idList.add(cursor.getString(0));
            titleList.add(cursor.getString(1));
        }
    }
}
