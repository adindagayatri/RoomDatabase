package com.example.daocrud;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BukuDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBuku(Buku buku);

    @Query("SELECT * FROM TbBuku")
    Buku[] readDataBuku();

    @Update
    int updateBuku(Buku buku);

    @Delete
    void deleteBuku (Buku buku);
}
