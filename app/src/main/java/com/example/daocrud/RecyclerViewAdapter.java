package com.example.daocrud;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    //Deklarasi Variabel

    private ArrayList<Buku> daftarBuku;
    private AppDatabase appDatabase;
    private Context context;

    public RecyclerViewAdapter(ArrayList<Buku> daftarBuku, Context context){
        this.daftarBuku = daftarBuku;
        this.context = context;
        appDatabase = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class, "dbBuku").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView id, judul, penulis, rak;
        private CardView item;

        ViewHolder(View itemView){
            super(itemView);
            id = itemView.findViewById(R.id.idBuku);
            judul = itemView.findViewById(R.id.judulBuku);
            item = itemView.findViewById(R.id.cvMain);
            penulis = itemView.findViewById(R.id.nama);
            rak = itemView.findViewById(R.id.rak);

        }
    }

    @NonNull
    @Override

    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder (@NonNull ViewHolder holder, int position){
        String getID = daftarBuku.get(position).getId();
        String getJudul = daftarBuku.get(position).getJudul();
        String getNama = daftarBuku.get(position).getNama();
        String getRak = daftarBuku.get(position).getRak();

        holder.id.setText(getID);
        holder.judul.setText(getJudul);
        holder.penulis.setText(getNama);
        holder.rak.setText(getRak);

        holder.item.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick (View v){
                CharSequence[] MenuPilihan = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext()).setTitle("Pilih Aksi").setItems(MenuPilihan, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                onEditData(position, context);
                                break;

                            case 1:
                                onDeleteData(position);
                                break;
                        }
                    }
                });
                dialog.create();
                dialog.show();
                return true;
            }
        });
    }

    private void onDeleteData (int position){
        appDatabase.bukuDAO().deleteBuku(daftarBuku.get(position));
        daftarBuku.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,daftarBuku.size());
        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
    }
    private void onEditData(int position, Context context){
        context.startActivity(new Intent(context, EditActivity.class).putExtra("data", daftarBuku.get(position)));
        ((Activity)context).finish();
    }

    @Override
    public int getItemCount(){
        return daftarBuku.size();
    }
}
