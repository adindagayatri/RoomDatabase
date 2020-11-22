package com.example.daocrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class ReadDataActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase database;
    private ArrayList<Buku> daftarBuku;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);
        getSupportActionBar().setTitle("Daftar Buku");

        recyclerView = (RecyclerView) findViewById(R.id.dataItem);
        button = (Button) findViewById(R.id.tambah);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadDataActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        daftarBuku = new ArrayList<>();
        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class, "dbBuku").allowMainThreadQueries().build();

        daftarBuku.addAll(Arrays.asList(database.bukuDAO().readDataBuku()));
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(daftarBuku,ReadDataActivity.this);
        recyclerView.setAdapter(adapter);

    }
}