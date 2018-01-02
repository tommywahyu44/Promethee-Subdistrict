package com.example.tommywahyu44.promethee.ListKecamatanSet;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tommywahyu44.promethee.PreferensiActivity;
import com.example.tommywahyu44.promethee.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SetKecamatanActivity extends AppCompatActivity implements View.OnClickListener{

    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private LinearLayout linear;
    private LinearLayoutManager linearLayoutManager;
    private ImageView back,empty;
    private CoordinatorLayout coordinatorLayout;
    public static List<ItemSetKecamatan> listItems;
    DatabaseReference mRootref;
    String[] Kriteria = new String[10];
    public static int countKriteria;
    ImageView CheckNext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_kecamatan);

        mRootref = FirebaseDatabase.getInstance().getReference();


        empty = (ImageView)findViewById(R.id.empty);
        coordinatorLayout = findViewById(R.id.layoutCoord);
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewForum);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        listItems = new ArrayList<>();

        adapter = new AdapterSetKecamatan(listItems, getApplicationContext());
        recyclerView.setAdapter(adapter);
        CheckNext = findViewById(R.id.checkNext);
        CheckNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
              startActivity(new Intent(SetKecamatanActivity.this, PreferensiActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        listItems.clear();
        DatabaseReference event = mRootref;
        event.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                countKriteria =0;
                for (final DataSnapshot postSnap : dataSnapshot.child("Kriteria").getChildren()) {
                    Kriteria[countKriteria] = postSnap.getKey().toString(); countKriteria++;
                }
                for (int j = countKriteria; j < 10 ; j++) Kriteria[j] = Kriteria[0];
                for (final DataSnapshot postSnapshot : dataSnapshot.child("Kecamatan").getChildren()) {

                    ItemSetKecamatan listItem = new ItemSetKecamatan(Kriteria[0],postSnapshot.child(Kriteria[0]).getValue().toString(),Kriteria[1],postSnapshot.child(Kriteria[1]).getValue().toString(),
                            Kriteria[2],postSnapshot.child(Kriteria[2]).getValue().toString(),Kriteria[3],postSnapshot.child(Kriteria[3]).getValue().toString(),Kriteria[4],postSnapshot.child(Kriteria[4]).getValue().toString()
                    ,Kriteria[5],postSnapshot.child(Kriteria[5]).getValue().toString(),Kriteria[6],postSnapshot.child(Kriteria[6]).getValue().toString(),Kriteria[7],postSnapshot.child(Kriteria[7]).getValue().toString(),
                            Kriteria[8],postSnapshot.child(Kriteria[8]).getValue().toString(),Kriteria[9],postSnapshot.child(Kriteria[9]).getValue().toString(),postSnapshot.getKey().toString());


                    listItems.add(listItem);

                    adapter.notifyDataSetChanged();
                }
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == back){
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_sortforum, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
      return true;
    }


}