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

import com.example.tommywahyu44.promethee.ListKecamatanSet.SetKecamatanActivity;
import com.example.tommywahyu44.promethee.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import static com.example.tommywahyu44.promethee.ListKecamatanSet.SetKecamatanActivity.countKriteria;

public class PreferensiActivity extends AppCompatActivity {
    int i = 0;
    TableRow row;
    TableLayout tableLayout, tableLayoutA;
    DatabaseReference mRootref;
    TextView KT01, KT02, KT11, KT12, KT21, KT22, KT31, KT32, KT41, KT42, KT51, KT52, KT61, KT62, KT71, KT72, KT81, KT82, KT91, KT92, KTT1, KTT2,
            KD01, KD02, KD11, KD12, KD21, KD22, KD31, KD32, KD41, KD42, KD51, KD52, KD61, KD62, KD71, KD72, KD81, KD82, KD91, KD92, KDT1, KDT2;
    int[][] arrNilai = new int[14][10];
    int[][][] arrPrefD = new int[14][14][10];
    public static double[][][] arrPrefHd = new double[14][14][10];
    public static int[] bobotKriteria = new int[10];
    int[] preferensiKriteria = new int[10];
    double[] batasPref = new double[7];
    private ProgressDialog progressDialog;
    ImageView CheckNextIndeks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferensi);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        CheckNextIndeks = findViewById(R.id.checkNextIndeks);
        CheckNextIndeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(PreferensiActivity.this, MultiKriteriaActivity.class));
            }
        });

        KT01 = findViewById(R.id.KT01);
        KT02 = findViewById(R.id.KT02);
        KT11 = findViewById(R.id.KT11);
        KT12 = findViewById(R.id.KT12);
        KT21 = findViewById(R.id.KT21);
        KT22 = findViewById(R.id.KT22);
        KT31 = findViewById(R.id.KT31);
        KT32 = findViewById(R.id.KT32);
        KT41 = findViewById(R.id.KT41);
        KT42 = findViewById(R.id.KT42);
        KT51 = findViewById(R.id.KT51);
        KT52 = findViewById(R.id.KT52);
        KT61 = findViewById(R.id.KT61);
        KT62 = findViewById(R.id.KT62);
        KT71 = findViewById(R.id.KT71);
        KT72 = findViewById(R.id.KT72);
        KT81 = findViewById(R.id.KT81);
        KT82 = findViewById(R.id.KT82);
        KT91 = findViewById(R.id.KT91);
        KT92 = findViewById(R.id.KT92);
        KTT1 = findViewById(R.id.KTT1);
        KTT2 = findViewById(R.id.KTT2);

        KD01 = findViewById(R.id.KD01);
        KD02 = findViewById(R.id.KD02);
        KD11 = findViewById(R.id.KD11);
        KD12 = findViewById(R.id.KD12);
        KD21 = findViewById(R.id.KD21);
        KD22 = findViewById(R.id.KD22);
        KD31 = findViewById(R.id.KD31);
        KD32 = findViewById(R.id.KD32);
        KD41 = findViewById(R.id.KD41);
        KD42 = findViewById(R.id.KD42);
        KD51 = findViewById(R.id.KD51);
        KD52 = findViewById(R.id.KD52);
        KD61 = findViewById(R.id.KD61);
        KD62 = findViewById(R.id.KD62);
        KD71 = findViewById(R.id.KD71);
        KD72 = findViewById(R.id.KD72);
        KD81 = findViewById(R.id.KD81);
        KD82 = findViewById(R.id.KD82);
        KD91 = findViewById(R.id.KD91);
        KD92 = findViewById(R.id.KD92);
        KDT1 = findViewById(R.id.KDT1);
        KDT2 = findViewById(R.id.KDT2);


    }

    public void InsertRow(char P1, char P2, String A1, String A2, String B1, String B2, String C1, String C2, String D1, String D2,
                          String E1, String E2, String F1, String F2, String G1, String G2, String H1, String H2, String I1, String I2, String J1, String J2) {
        tableLayout = findViewById(R.id.tableLayoutId);
        tableLayoutA = findViewById(R.id.tableLayoutIdA);
        row = (TableRow) getLayoutInflater().inflate(R.layout.activity_preferensi_row, null);
        String P11 = Character.toString(P1);
        String P12 = Character.toString(P2);

        ((TextView) row.findViewById(R.id.Pref1)).setText(P11);
        ((TextView) row.findViewById(R.id.Pref2)).setText(P12);

        switch (countKriteria) {
            case 10:
                row.findViewById(R.id.j1).setVisibility(View.VISIBLE);
                row.findViewById(R.id.j2).setVisibility(View.VISIBLE);
                ((TextView) row.findViewById(R.id.j1)).setText(J1);
                ((TextView) row.findViewById(R.id.j2)).setText(J2);
            case 9:
                row.findViewById(R.id.i1).setVisibility(View.VISIBLE);
                row.findViewById(R.id.i2).setVisibility(View.VISIBLE);
                ((TextView) row.findViewById(R.id.i1)).setText(I1);
                ((TextView) row.findViewById(R.id.i2)).setText(I2);
            case 8:
                row.findViewById(R.id.h1).setVisibility(View.VISIBLE);
                row.findViewById(R.id.h2).setVisibility(View.VISIBLE);
                ((TextView) row.findViewById(R.id.h1)).setText(H1);
                ((TextView) row.findViewById(R.id.h2)).setText(H2);
            case 7:
                row.findViewById(R.id.g1).setVisibility(View.VISIBLE);
                row.findViewById(R.id.g2).setVisibility(View.VISIBLE);
                ((TextView) row.findViewById(R.id.g1)).setText(G1);
                ((TextView) row.findViewById(R.id.g2)).setText(G2);
            case 6:
                row.findViewById(R.id.f1).setVisibility(View.VISIBLE);
                row.findViewById(R.id.f2).setVisibility(View.VISIBLE);
                ((TextView) row.findViewById(R.id.f1)).setText(F1);
                ((TextView) row.findViewById(R.id.f2)).setText(F2);
            case 5:
                row.findViewById(R.id.e1).setVisibility(View.VISIBLE);
                row.findViewById(R.id.e2).setVisibility(View.VISIBLE);
                ((TextView) row.findViewById(R.id.e1)).setText(E1);
                ((TextView) row.findViewById(R.id.e2)).setText(E2);
            case 4:
                row.findViewById(R.id.d1).setVisibility(View.VISIBLE);
                row.findViewById(R.id.d2).setVisibility(View.VISIBLE);
                ((TextView) row.findViewById(R.id.d1)).setText(D1);
                ((TextView) row.findViewById(R.id.d2)).setText(D2);
            case 3:
                row.findViewById(R.id.c1).setVisibility(View.VISIBLE);
                row.findViewById(R.id.c2).setVisibility(View.VISIBLE);
                ((TextView) row.findViewById(R.id.c1)).setText(C1);
                ((TextView) row.findViewById(R.id.c2)).setText(C2);
            case 2:
                row.findViewById(R.id.b1).setVisibility(View.VISIBLE);
                row.findViewById(R.id.b2).setVisibility(View.VISIBLE);
                ((TextView) row.findViewById(R.id.b1)).setText(B1);
                ((TextView) row.findViewById(R.id.b2)).setText(B2);
            case 1:
                row.findViewById(R.id.a1).setVisibility(View.VISIBLE);
                row.findViewById(R.id.a2).setVisibility(View.VISIBLE);
                ((TextView) row.findViewById(R.id.a1)).setText(A1);
                ((TextView) row.findViewById(R.id.a2)).setText(A2);


        }

        tableLayoutA.addView(row);

    }

    @Override
    public void onStart() {
        super.onStart();
        mRootref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference event = mRootref;

        event.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0, j = 0, bobotCount = 0;

                for (final DataSnapshot postShot : dataSnapshot.child("Kriteria").getChildren()) {
                    String a = postShot.getKey().toString();
                    bobotKriteria[bobotCount] = Integer.parseInt(postShot.child("Bobot" + a).getValue().toString());
                    preferensiKriteria[bobotCount] = Integer.parseInt(postShot.child("Preferensi" + a).getValue().toString());
                    bobotCount++;

                }
                for (final DataSnapshot postSnapshot : dataSnapshot.child("Kecamatan").getChildren()) {
                    for (final DataSnapshot postSnap : dataSnapshot.child("Kecamatan").child(postSnapshot.getKey().toString()).getChildren()) {
                         arrNilai[i][j] = Integer.parseInt(postSnap.getValue().toString());

                        j++;


                    }
                    j = 0;
                    i++;


                }

                for (int k = 0; k < countKriteria; k++) {
                    for (int l = 0; l < 14; l++) {
                        for (int m = 0; m < 14; m++) {
                            arrPrefD[l][m][k] = arrNilai[l][k] - arrNilai[m][k];
                        }
                    }
                }

                for (int k = countKriteria; k < 10; k++) {
                    for (int l = 0; l < 14; l++) {
                        for (int m = 0; m < 14; m++) {
                            arrPrefD[l][m][k] = 0;
                        }
                    }
                }
                //l adalah perpindahan kecamatan perb.1,m perpindahan kecamatan perb.2,k kriteria


                batasPref[0] = Double.parseDouble(dataSnapshot.child("Batas").child("BatasTipe2").getValue().toString());
                batasPref[1] = Double.parseDouble(dataSnapshot.child("Batas").child("BatasTipe3").getValue().toString());
                batasPref[2] = Double.parseDouble(dataSnapshot.child("Batas").child("BatasTipe4").getValue().toString());
                batasPref[3] = Double.parseDouble(dataSnapshot.child("Batas").child("BatasTipe4b").getValue().toString());
                batasPref[4] = Double.parseDouble(dataSnapshot.child("Batas").child("BatasTipe5").getValue().toString());
                batasPref[5] = Double.parseDouble(dataSnapshot.child("Batas").child("BatasTipe5b").getValue().toString());
                batasPref[6] = Double.parseDouble(dataSnapshot.child("Batas").child("BatasTipe6").getValue().toString());

                switch (countKriteria) {
                    case 10:
                        KTT1.setVisibility(View.VISIBLE);
                        KTT2.setVisibility(View.VISIBLE);
                        KDT1.setVisibility(View.VISIBLE);
                        KDT2.setVisibility(View.VISIBLE);
                    case 9:
                        KT91.setVisibility(View.VISIBLE);
                        KT92.setVisibility(View.VISIBLE);
                        KD91.setVisibility(View.VISIBLE);
                        KD92.setVisibility(View.VISIBLE);
                    case 8:
                        KT81.setVisibility(View.VISIBLE);
                        KT82.setVisibility(View.VISIBLE);
                        KD81.setVisibility(View.VISIBLE);
                        KD82.setVisibility(View.VISIBLE);
                    case 7:
                        KT71.setVisibility(View.VISIBLE);
                        KT72.setVisibility(View.VISIBLE);
                        KD71.setVisibility(View.VISIBLE);
                        KD72.setVisibility(View.VISIBLE);
                    case 6:
                        KT61.setVisibility(View.VISIBLE);
                        KT62.setVisibility(View.VISIBLE);
                        KD61.setVisibility(View.VISIBLE);
                        KD62.setVisibility(View.VISIBLE);
                    case 5:
                        KT51.setVisibility(View.VISIBLE);
                        KT52.setVisibility(View.VISIBLE);
                        KD51.setVisibility(View.VISIBLE);
                        KD52.setVisibility(View.VISIBLE);
                    case 4:
                        KT41.setVisibility(View.VISIBLE);
                        KT42.setVisibility(View.VISIBLE);
                        KD41.setVisibility(View.VISIBLE);
                        KD42.setVisibility(View.VISIBLE);
                    case 3:
                        KT31.setVisibility(View.VISIBLE);
                        KT32.setVisibility(View.VISIBLE);
                        KD31.setVisibility(View.VISIBLE);
                        KD32.setVisibility(View.VISIBLE);
                    case 2:
                        KT21.setVisibility(View.VISIBLE);
                        KT22.setVisibility(View.VISIBLE);
                        KD21.setVisibility(View.VISIBLE);
                        KD22.setVisibility(View.VISIBLE);
                    case 1:
                        KT11.setVisibility(View.VISIBLE);
                        KT12.setVisibility(View.VISIBLE);
                        KD11.setVisibility(View.VISIBLE);
                        KD12.setVisibility(View.VISIBLE);

                }

                PrefHD();

                char a = 'A', b = 'A';
                for (int u = 0; u < 14; u++) {
                    for (int v = 0; v < 14; v++) {

                        InsertRow(a, b, Integer.toString(arrPrefD[u][v][0]), String.format("%.1f", arrPrefHd[u][v][0]), Integer.toString(arrPrefD[u][v][1]), String.format("%.1f", arrPrefHd[u][v][1]), Integer.toString(arrPrefD[u][v][2]), String.format("%.1f", arrPrefHd[u][v][2]), Integer.toString(arrPrefD[u][v][3]), String.format("%.1f", arrPrefHd[u][v][3]), Integer.toString(arrPrefD[u][v][4]), String.format("%.1f", arrPrefHd[u][v][4]), Integer.toString(arrPrefD[u][v][5]), String.format("%.1f", arrPrefHd[u][v][5]), Integer.toString(arrPrefD[u][v][6]), String.format("%.1f", arrPrefHd[u][v][6]), Integer.toString(arrPrefD[u][v][7]), String.format("%.1f", arrPrefHd[u][v][7]), Integer.toString(arrPrefD[u][v][8]), String.format("%.1f", arrPrefHd[u][v][8]), Integer.toString(arrPrefD[u][v][9]), String.format("%.1f", arrPrefHd[u][v][9]));
                        b++;
                    }
                    b = 'A';
                    a++;
                }
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });


    }

    public void PrefHD() {

        for (int a = 0; a < countKriteria; a++) {
            switch (preferensiKriteria[a]) {
                case 1:
                    for (int i = 0; i < 14; i++) {
                        for (int j = 0; j < 14; j++) {
                            if (arrPrefD[i][j][a] <= 0)
                                arrPrefHd[i][j][a] = 0;
                            else arrPrefHd[i][j][a] = 1;
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 14; i++) {
                        for (int j = 0; j < 14; j++) {
                            if (Math.abs(arrPrefD[i][j][a]) <= batasPref[0])
                                arrPrefHd[i][j][a] = 0;
                            else arrPrefHd[i][j][a] = 1;
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 14; i++) {
                        for (int j = 0; j < 14; j++) {
                            if (Math.abs(arrPrefD[i][j][a]) <= batasPref[1])
                                arrPrefHd[i][j][a] = arrPrefD[i][j][a] / batasPref[1];
                            else arrPrefHd[i][j][a] = 1;
                        }
                    }
                    break;
                case 4:
                    for (int i = 0; i < 14; i++) {
                        for (int j = 0; j < 14; j++) {
                            if (Math.abs(arrPrefD[i][j][a]) <= batasPref[3])
                                arrPrefHd[i][j][a] = 0;
                            else if (Math.abs(arrPrefD[i][j][a]) <= (batasPref[2] + batasPref[3]))
                                arrPrefHd[i][j][a] = 0.5;
                            else arrPrefHd[i][j][a] = 1;
                        }
                    }
                    break;
                case 5:
                    for (int i = 0; i < 14; i++) {
                        for (int j = 0; j < 14; j++) {
                            if (Math.abs(arrPrefD[i][j][a]) <= batasPref[4])
                                arrPrefHd[i][j][a] = 0;
                            else if (Math.abs(arrPrefD[i][j][a]) <= (batasPref[4] + batasPref[5]))
                                arrPrefHd[i][j][a] = (arrPrefD[i][j][a] - batasPref[4]) / batasPref[5];
                            else arrPrefHd[i][j][a] = 1;
                        }
                    }
                    break;
                case 6:
                    for (int i = 0; i < 14; i++) {
                        for (int j = 0; j < 14; j++) {
                            if (Math.abs(arrPrefD[i][j][a]) <= 0)
                                arrPrefHd[i][j][a] = 0;
                            else
                                arrPrefHd[i][j][a] = 1 - Math.pow(2.71828, -(arrPrefD[i][j][a] * arrPrefD[i][j][a]) / (2.0d * (batasPref[6] * batasPref[6])));

                        }
                    }
                    break;
            }
        }
        for (int a = countKriteria; a < 10; a++) {
            for (int i = 0; i < 14; i++) {
                for (int j = 0; j < 14; j++) {
                    arrPrefHd[i][j][a] = 0;
                }
            }

        }
    }

}


