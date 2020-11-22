package com.example.daocrud;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "TbBuku")
public class Buku implements Serializable {

    //Membuat Kolom ID Buku
    @NonNull
    @PrimaryKey
    @ColumnInfo(name="id_buku")
    private
    String id;

    //Membuat Kolom Judul Buku
    @ColumnInfo(name="judul_buku")
    private
    String judul;

    //Membuat Kolom Nama Penulis
    @ColumnInfo(name="nama_penulis")
    private
    String nama;

    //Membuat Kolom Rak Buku
    @ColumnInfo(name="rak_buku")
    private
    String rak;

    //Mengambil data ID
    @NonNull
    public String getId(){
        return id;
    }

    //Memasukkan data ID
    public void setId(@NonNull String id){
        this.id = id;
    }

    //Mengambil data judul
    public String getJudul(){
        return judul;
    }

    //Memasukkan data judul
    public void setJudul(String judul){
        this.judul = judul;
    }

    //Mengambil data nama penulis
    public String getNama(){
        return nama;
    }

    //Memasukkan data nama penulis
    public void setNama(String nama){
        this.nama = nama;
    }

    //Mengambil data rak
    public String getRak(){
        return rak;
    }

    //Memasukkan data rak
    public void setRak(String rak){
        this.rak = rak;
    }
}
