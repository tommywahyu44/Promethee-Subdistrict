package com.example.tommywahyu44.promethee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.tommywahyu44.promethee.ListKecamatanSet.SetKecamatanActivity.countKriteria;

public class MultiKriteriaActivity extends AppCompatActivity {

    DatabaseReference mRootref;
    TableRow row;
    TableLayout tableLayout, tableLayoutA;
    TextView Kecamatan1, Kecamatan2, IndeksPref;

    static double[][] IndeksPreferensi = new double[14][14];
    static String[] namaKecamatan = new String[14];
    ImageView CheckNextRank;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_kriteria);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        CheckNextRank = findViewById(R.id.checkNextRank);
        CheckNextRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MultiKriteriaActivity.this, PrometheeRankingActivity.class));
            }
        });


        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                IndeksPreferensi[i][j] = 0.0;
                for (int k = 0; k < countKriteria; k++) {
                    IndeksPreferensi[i][j] = IndeksPreferensi[i][j] + (PreferensiActivity.arrPrefHd[i][j][k] * (PreferensiActivity.bobotKriteria[k] / 100.0d));
                }
                 IndeksPreferensi[i][j] = (1.0d / countKriteria) * IndeksPreferensi[i][j];
            }
        }



        mRootref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference event = mRootref;

        event.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0, j = 0;
                for (final DataSnapshot postSnaphot : dataSnapshot.child("Kecamatan").getChildren()) {
                    namaKecamatan[i] = postSnaphot.getKey().toString();
                    for (final DataSnapshot postShot : dataSnapshot.child("Kecamatan").getChildren()) {

                        InsertRow(postSnaphot.getKey().toString(), postShot.getKey().toString(), String.format("%.3f", IndeksPreferensi[i][j]));
                        j++;
                    }
                    j = 0;
                    i++;
                }
                progressDialog.dismiss();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });


    }

    public void InsertRow(String P1, String P2, String Indeks) {
        tableLayout = findViewById(R.id.tableLayoutIdIndeks);
        tableLayoutA = findViewById(R.id.tableLayoutIdAIndeks);
        row = (TableRow) getLayoutInflater().inflate(R.layout.activity_multi_kriteria_row, null);

        ((TextView) row.findViewById(R.id.KecamatanIndex1)).setText(P1);
        ((TextView) row.findViewById(R.id.KecamatanIndex2)).setText(P2);
        ((TextView) row.findViewById(R.id.IndeksPref)).setText(Indeks);

        tableLayoutA.addView(row);

    }
}
