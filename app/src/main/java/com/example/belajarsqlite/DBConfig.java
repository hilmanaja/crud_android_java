package com.example.belajarsqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import android.content.Context;

public class DBConfig extends SQLiteOpenHelper {
    // membuat konstanta
    private static final String DB_NAME = "db_catatan.db"; // pastikan tulis nama DBnya
    private static final int DB_VERSION = 1;

    public DBConfig(@Nullable Context context){
        // pembuatan database
        super(context, DB_NAME, null, DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // membuat struktur table
        String queryCreateTable = "CREATE TABLE tb_tugas ( id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR(255) NOT NULL, description TEXT NOT NULL )";
        db.execSQL(queryCreateTable);

        // sample data
        String querySampleData = "INSERT INTO tb_tugas (title, description) VALUES ('UTS', 'Membuat program sederhana'), ('Tugas', 'Prakik SQLite'), ('Kuis', 'Teori SQL')";
        db.execSQL(querySampleData);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}