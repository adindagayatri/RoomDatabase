package com.example.daocrud;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.daocrud.BukuDAO;
import com.example.daocrud.Buku;

@Database(entities = {Buku.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    //Mengakses Database menggunakan method abstract

    public abstract BukuDAO bukuDAO();

}
