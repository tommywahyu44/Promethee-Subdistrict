package com.example.tommywahyu44.promethee;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BatasPreferensiActivity extends AppCompatActivity {

    TextInputEditText batas1,batas2,batas3,batas3b,batas4,batas4b,batas5;
    Button buttonBatas;
    DatabaseReference mRootref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batas_preferensi);
        mRootref = FirebaseDatabase.getInstance().getReference();
        batas1 = findViewById(R.id.batas1);
        batas2 = findViewById(R.id.batas2);
        batas3 = findViewById(R.id.batas3);
        batas3b = findViewById(R.id.batas3b);
        batas4 = findViewById(R.id.batas4);
        batas4b = findViewById(R.id.batas4b);
        batas5 = findViewById(R.id.batas5);
        buttonBatas = findViewById(R.id.buttonBatas);
        buttonBatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String batasTipe2 = batas1.getText().toString();
                String batasTipe3 = batas2.getText().toString();
                String batasTipe4 = batas3.getText().toString();
                String batasTipe4b = batas3b.getText().toString();
                String batasTipe5 = batas4.getText().toString();
                String batasTipe5b = batas4b.getText().toString();
                String batasTipe6 = batas5.getText().toString();

                mRootref.child("Batas").child("BatasTipe2").setValue(batasTipe2);
                mRootref.child("Batas").child("BatasTipe3").setValue(batasTipe3);
                mRootref.child("Batas").child("BatasTipe4").setValue(batasTipe4);
                mRootref.child("Batas").child("BatasTipe4b").setValue(batasTipe4b);
                mRootref.child("Batas").child("BatasTipe5").setValue(batasTipe5);
                mRootref.child("Batas").child("BatasTipe5b").setValue(batasTipe5b);
                mRootref.child("Batas").child("BatasTipe6").setValue(batasTipe6);
                finish();
                startActivity(new Intent(BatasPreferensiActivity.this,MainActivity.class));
            }
        });


    }
}
