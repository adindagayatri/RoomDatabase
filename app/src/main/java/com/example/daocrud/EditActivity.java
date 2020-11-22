package com.example.daocrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class EditActivity extends AppCompatActivity {

    private TextInputEditText Judul, Nama, Rak;
    private AppDatabase database;
    private Button simpanData;
    private Buku buku;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Judul = findViewById(R.id.judulBuku);
        Nama = findViewById(R.id.namaPenulis);
        Rak = findViewById(R.id.rakBuku);
        simpanData = findViewById(R.id.save);
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbBuku").build();

        getDataBuku();

        simpanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buku.setJudul(Judul.getText().toString());
                buku.setNama(Nama.getText().toString());
                buku.setRak(Rak.getText().toString());
                updateData(buku);
            }
        });
    }

    private void getDataBuku(){
        buku = (Buku)getIntent().getSerializableExtra("data");
        Judul.setText(buku.getJudul());
        Nama.setText(buku.getNama());
        Rak.setText(buku.getRak());
    }

    private void updateData (final Buku buku){
        new AsyncTask<Void, Void, Integer>(){
            @Override
            protected Integer doInBackground(Void... voids){
                return database.bukuDAO().updateBuku(buku);
            }
            @Override
            protected void onPostExecute(Integer status){
                Toast.makeText(EditActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditActivity.this, ReadDataActivity.class));
                finish();
            }
        }.execute();
    }

    @Override

    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(EditActivity.this, ReadDataActivity.class));
        finish();
    }
}