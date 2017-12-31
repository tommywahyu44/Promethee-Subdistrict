package com.example.tommywahyu44.promethee;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tommywahyu44.promethee.ListKecamatanSet.SetKecamatanActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mRootref;
    TableRow row;
    TableLayout tableLayout;
    Button Hapus, Tambah, Selesai,TambahShow,HapusShow;
    EditText editHapus,editTambah1,editTambah2,editTambah3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tableLayout = findViewById(R.id.tableLayoutMenu);
        row = (TableRow) getLayoutInflater().inflate(R.layout.activity_menu_row, null);
        Hapus = findViewById(R.id.buttonHapus);
        Selesai = findViewById(R.id.buttonSelesai);
        Tambah = findViewById(R.id.buttonTambah);
        editHapus = findViewById(R.id.editHapus);
        TambahShow = findViewById(R.id.buttonTambahShow);
        HapusShow = findViewById(R.id.buttonHapusShow);
        editTambah1 = findViewById(R.id.editTambah1);
        editTambah2 = findViewById(R.id.editTambah2);
        editTambah3 = findViewById(R.id.editTambah3);

        HapusShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTambah1.setVisibility(view.GONE);
                editTambah2.setVisibility(view.GONE);
                editTambah3.setVisibility(view.GONE);
                HapusShow.setVisibility(View.GONE);
                Tambah.setVisibility(View.GONE);
                TambahShow.setVisibility(View.VISIBLE);
                tableLayout.setVisibility(view.VISIBLE);
                editHapus.setVisibility(view.VISIBLE);
                Hapus.setVisibility(View.VISIBLE);
            }
        });

        Selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent (MainActivity.this, SetKecamatanActivity.class));

            }
        });

        Tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TambahRow(editTambah1.getText().toString(),editTambah2.getText().toString(),editTambah3.getText().toString());
                Tambah.setVisibility(View.GONE);
                tableLayout.setVisibility(View.VISIBLE);
                TambahShow.setVisibility(View.VISIBLE);



            }
        });

        TambahShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableLayout.setVisibility(view.GONE);
                editHapus.setVisibility(view.GONE);
                TambahShow.setVisibility(View.GONE);
                HapusShow.setVisibility(View.VISIBLE);
                Tambah.setVisibility(View.VISIBLE);
                Hapus.setVisibility(View.GONE);
                editTambah1.setVisibility(view.VISIBLE);
                editTambah2.setVisibility(view.VISIBLE);
                editTambah3.setVisibility(view.VISIBLE);
            }
        });
        Hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(editHapus.getText().toString());
                HapusRow(a);
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mRootref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference event = mRootref.child("Kriteria");
        event.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String a = postSnapshot.getKey().toString();
                    String b = postSnapshot.child("Bobot" + a).getValue().toString();
                    String c = postSnapshot.child("Preferensi" + a).getValue().toString();
                    InsertRow(a, b, c);


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });


    }

    public void InsertRow(String kriteria, String bobot, String preferensi) {
        tableLayout = findViewById(R.id.tableLayoutMenu);
        row = (TableRow) getLayoutInflater().inflate(R.layout.activity_menu_row, null);

        ((TextView) row.findViewById(R.id.kriteria)).setText(kriteria);
        ((TextView) row.findViewById(R.id.bobot)).setText(bobot);
        ((TextView) row.findViewById(R.id.preferensi)).setText(preferensi);


        tableLayout.addView(row);

    }

    public void TambahRow (final String kriteria, String bobot, String preferensi) {
        tableLayout = findViewById(R.id.tableLayoutMenu);
        row = (TableRow) getLayoutInflater().inflate(R.layout.activity_menu_row, null);

        ((TextView) row.findViewById(R.id.kriteria)).setText(kriteria);
        ((TextView) row.findViewById(R.id.bobot)).setText(bobot);
        ((TextView) row.findViewById(R.id.preferensi)).setText(preferensi);


        tableLayout.addView(row);
        mRootref.child("Kriteria").child(kriteria).child("Bobot"+kriteria).setValue(bobot);
        mRootref.child("Kriteria").child(kriteria).child("Preferensi"+kriteria).setValue(preferensi);
        DatabaseReference event = mRootref.child("Kecamatan");
        event.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String a = postSnapshot.getKey().toString();
                    mRootref.child("Kecamatan").child(a).child(kriteria).setValue(0);



                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

    }

    public void HapusRow (int Row) {
        tableLayout = findViewById(R.id.tableLayoutMenu);
        row = (TableRow) getLayoutInflater().inflate(R.layout.activity_menu_row, null);
        TableRow row2 = (TableRow) tableLayout.getChildAt(Row);
        TextView text= (TextView) row2.getChildAt(0);
        final String text2 = text.getText().toString();
        mRootref.child("Kriteria").child(text2).getRef().removeValue();
        tableLayout.removeViewsInLayout(Row,1);
        DatabaseReference event = mRootref.child("Kecamatan");
        event.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                   postSnapshot.child(text2).getRef().removeValue();


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

    }
}
