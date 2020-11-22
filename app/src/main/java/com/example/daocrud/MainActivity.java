package com.example.daocrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.daocrud.AppDatabase;
import com.example.daocrud.Buku;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText ID, Judul, Nama, Rak;
    private AppDatabase database;
    private Button simpanData, lihatData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ID = findViewById(R.id.id_buku);
        Judul = findViewById(R.id.judulBuku);
        Nama = findViewById(R.id.namaPenulis);
        Rak = findViewById(R.id.rakBuku);
        simpanData = findViewById(R.id.save);
        simpanData.setOnClickListener(this);



        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbBuku").build();
    }

    @SuppressLint("StaticFieldLeak")
    private void insertData (final Buku buku){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground (Void... voids){
                return database.bukuDAO().insertBuku(buku);
            }

            @Override
            protected void onPostExecute(Long status){
                Toast.makeText(MainActivity.this, "Status Row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    @Override
    public void onClick(View v) {
                //Cek ID dan Judul tidak boleh kosong
        if (ID.getText().toString().isEmpty() || Judul.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "NIM atau Judul Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }
        else {
            Buku data = new Buku();
            data.setId(ID.getText().toString());
            data.setJudul(Judul.getText().toString());
            data.setNama(Nama.getText().toString());
            data.setRak(Rak.getText().toString());
            insertData(data);

            ID.setText("");
            Judul.setText("");
            Nama.setText("");
            Rak.setText("");
        }
        Intent intent = new Intent(MainActivity.this, ReadDataActivity.class);
        startActivity(intent);

    }
}