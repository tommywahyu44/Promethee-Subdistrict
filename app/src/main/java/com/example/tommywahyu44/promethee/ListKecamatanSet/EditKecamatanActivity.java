package com.example.tommywahyu44.promethee.ListKecamatanSet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tommywahyu44.promethee.PreferensiActivity;
import com.example.tommywahyu44.promethee.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.tommywahyu44.promethee.ListKecamatanSet.SetKecamatanActivity.countKriteria;

public class EditKecamatanActivity extends AppCompatActivity {

    private EditText KK11, KK12, KK21, KK22, KK31, KK32, KK41, KK42,
            KK51, KK52, KK61, KK62, KK71, KK72, KK81, KK82,
            KK91, KK92, KKT1, KKT2;
    private TextView namaKecamatan;
    DatabaseReference mRootref;
    String id, nama1, nama2, nama3, nama4, nama5, nama6, nama7, nama8, nama9, nama10;
    ImageView CheckNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kecamatan);
        mRootref = FirebaseDatabase.getInstance().getReference();


        KK11 = findViewById(R.id.KK1);
        KK21 = findViewById(R.id.KK2);
        KK31 = findViewById(R.id.KK3);
        KK41 = findViewById(R.id.KK4);
        KK51 = findViewById(R.id.KK5);
        KK61 = findViewById(R.id.KK6);
        KK71 = findViewById(R.id.KK7);
        KK81 = findViewById(R.id.KK8);
        KK91 = findViewById(R.id.KK9);
        KKT1 = findViewById(R.id.KKT);
        namaKecamatan = findViewById(R.id.namaKecamatanEdit);

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            nama1 = intent.getStringExtra("nama1");
            switch (countKriteria) {
                case 10:
                    nama10 = intent.getStringExtra("nama10");

                case 9:
                    nama9 = intent.getStringExtra("nama9");

                case 8:
                    nama8 = intent.getStringExtra("nama8");

                case 7:
                    nama7 = intent.getStringExtra("nama7");
                case 6:
                    nama6 = intent.getStringExtra("nama6");

                case 5:
                    nama5 = intent.getStringExtra("nama5");

                case 4:
                    nama4 = intent.getStringExtra("nama4");

                case 3:
                    nama3 = intent.getStringExtra("nama3");

                case 2:
                    nama2 = intent.getStringExtra("nama2");

                case 1:
                    nama1 = intent.getStringExtra("nama1");
            }
        }


        switch (countKriteria) {
            case 10:
                KKT1.setVisibility(View.VISIBLE);
                KKT1.setHint(nama10);
            case 9:
                KK91.setVisibility(View.VISIBLE);
                KK91.setHint(nama9);
            case 8:
                KK81.setVisibility(View.VISIBLE);
                KK81.setHint(nama8);
            case 7:
                KK71.setVisibility(View.VISIBLE);
                KK71.setHint(nama7);
            case 6:
                KK61.setVisibility(View.VISIBLE);
                KK61.setHint(nama6);
            case 5:
                KK51.setVisibility(View.VISIBLE);
                KK51.setHint(nama5);
            case 4:
                KK41.setVisibility(View.VISIBLE);
                KK41.setHint(nama4);
            case 3:
                KK31.setVisibility(View.VISIBLE);
                KK31.setHint(nama3);
            case 2:
                KK21.setVisibility(View.VISIBLE);
                KK21.setHint(nama2);
            case 1:
                KK11.setVisibility(View.VISIBLE);
                KK11.setHint(nama1);
        }
        namaKecamatan.setText(id);

        CheckNext = findViewById(R.id.checkEdit);
        CheckNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference event = mRootref.child("Kecamatan");
                switch (countKriteria) {
                    case 10:
                        event.child(id).child(nama10).setValue(KKT1.getText().toString());
                    case 9:
                        event.child(id).child(nama9).setValue(KK91.getText().toString());
                    case 8:
                        event.child(id).child(nama8).setValue(KK81.getText().toString());
                    case 7:
                        event.child(id).child(nama7).setValue(KK71.getText().toString());
                    case 6:
                        event.child(id).child(nama6).setValue(KK61.getText().toString());
                    case 5:
                        event.child(id).child(nama5).setValue(KK51.getText().toString());
                    case 4:
                        event.child(id).child(nama4).setValue(KK41.getText().toString());
                    case 3:
                        event.child(id).child(nama3).setValue(KK31.getText().toString());
                    case 2:
                        event.child(id).child(nama2).setValue(KK21.getText().toString());
                    case 1:
                        event.child(id).child(nama1).setValue(KK11.getText().toString());
                }


                finish();
                startActivity(new Intent(EditKecamatanActivity.this, SetKecamatanActivity.class));
            }
        });
    }


}
