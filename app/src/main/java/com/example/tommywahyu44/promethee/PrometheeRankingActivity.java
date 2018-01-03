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

import com.google.firebase.database.DatabaseReference;

public class PrometheeRankingActivity extends AppCompatActivity {

    DatabaseReference mRootref;
    TableRow row;
    TableLayout tableLayout, tableLayoutA;
    TextView Kecamatan, Leaving, Entering, Net, Ranking, Keterangan;
    double[] arrLeaving = new double[14];
    double[] arrEntering = new double[14];
    double[] arrNet = new double[14];
    private ProgressDialog progressDialog;
    ImageView CheckNextHasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promethee_ranking);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();


        CheckNextHasil = findViewById(R.id.checkNextHasil);
        CheckNextHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(PrometheeRankingActivity.this, ListviewHasilActivity.class));
            }
        });

        for (int i = 0; i < 14; i++) {
            arrLeaving[i] = 0;
            arrEntering[i] = 0;
            for (int j = 0; j < 14; j++) {
                arrLeaving[i] = arrLeaving[i] + MultiKriteriaActivity.IndeksPreferensi[j][i];
                arrEntering[i] = arrEntering[i] + MultiKriteriaActivity.IndeksPreferensi[i][j];
            }
            arrLeaving[i] = (1 / 13.0d) * arrLeaving[i];
            arrEntering[i] = (1 / 13.0d) * arrEntering[i];
            arrNet[i] = arrLeaving[i] - arrEntering[i];
        }

        for (int i = 0; i < 14; i++) {

            int pos = i;
            for (int j = 0; j < 14; j++) {
                if (arrNet[pos] < arrNet[j]) {
                    pos = j;
                }
            }
            InsertRow(MultiKriteriaActivity.namaKecamatan[pos], String.format("%.3f", arrLeaving[pos]), String.format("%.3f", arrEntering[pos]), String.format("%.3f", arrNet[pos]), Integer.toString(i + 1));
            ListviewHasilActivity.values[i] = MultiKriteriaActivity.namaKecamatan[pos];
            arrNet[pos] = -100000;
        }
        progressDialog.dismiss();
    }


    public void InsertRow(String Kecamatan, String Leaving, String Entering, String Net, String Rank) {
        tableLayout = findViewById(R.id.tableLayoutIdRank);
        tableLayoutA = findViewById(R.id.tableLayoutIdARank);
        row = (TableRow) getLayoutInflater().inflate(R.layout.activity_promethee_ranking_row, null);

        ((TextView) row.findViewById(R.id.Kecamatan)).setText(Kecamatan);
        ((TextView) row.findViewById(R.id.LeavingFlow)).setText(Leaving);
        ((TextView) row.findViewById(R.id.EnteringFlow)).setText(Entering);
        ((TextView) row.findViewById(R.id.NetFlow)).setText(Net);
        ((TextView) row.findViewById(R.id.Ranking)).setText(Rank);
        if (Double.parseDouble(Net) >= 0)
            ((TextView) row.findViewById(R.id.Keterangan)).setText("Diterima");
        else ((TextView) row.findViewById(R.id.Keterangan)).setText("Ditolak");

        tableLayoutA.addView(row);
    }
}