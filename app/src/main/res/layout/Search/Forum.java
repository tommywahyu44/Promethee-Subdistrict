package com.example.tommywahyu44.cekin.Search;

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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tommywahyu44.cekin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.tommywahyu44.cekin.HomeFragment.brand;
import static com.example.tommywahyu44.cekin.HomeFragment.ukuran;


public class Forum extends AppCompatActivity implements View.OnClickListener{

    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private LinearLayout linear;
    private LinearLayoutManager linearLayoutManager;
    private ImageView back,empty;
    private CoordinatorLayout coordinatorLayout;
    public static List<ItemForum> listItems;
    public static String a;
    public static boolean sortByLike = false;
    public static boolean sortByComment = false;
    DatabaseReference mRootref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        mRootref = FirebaseDatabase.getInstance().getReference();

        back = (ImageView)findViewById(R.id.BackToMenu);
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

        adapter = new com.example.tommywahyu44.cekin.Search.AdapterForum(listItems, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume(){
        super.onResume();

        listItems.clear();
        DatabaseReference event = mRootref.child("Pakaian");
        event.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Pattern pattern = Pattern.compile("(\\w*)(" + brand.toLowerCase() + ")(\\w*)");
                for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Matcher matcher = pattern.matcher(postSnapshot.child("merk").getValue().toString().toLowerCase());
                    if((matcher.find() || brand.equalsIgnoreCase("")) && (ukuran.equalsIgnoreCase(postSnapshot.child("ukuran").getValue().toString()) || ukuran.equalsIgnoreCase("")) ) {
                        ItemForum listItem = new ItemForum(postSnapshot.child("ukuran").getValue().toString(),  currency(Integer.parseInt(postSnapshot.child("harga").getValue().toString())), postSnapshot.child("namabarang").getValue().toString(),
                                postSnapshot.child("merk").getValue().toString(), postSnapshot.child("tempat").getValue().toString(), postSnapshot.getKey());


                        listItems.add(listItem);
                    }
                    if (listItems.size()==0) {
                        coordinatorLayout.setBackgroundColor(Color.parseColor("#ffffff"));
                        empty.setVisibility(View.VISIBLE);
                    } else {
                        empty.setVisibility(View.GONE);
                    }
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
        inflater.inflate(R.menu.menu_sortforum, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_sort_forum:
                int choice = 0;
                if(sortByLike){
                    choice = 1;
                } else if(sortByComment){
                    choice = 2;
                }
                final CharSequence[] options = {"Terbaru", "Like", "Komentar"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Urutkan :");
                builder.setSingleChoiceItems(options, choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Like")) {
                            sortByLike = true;
                            sortByComment = false;
                        } else if (options[item].equals("Komentar")) {
                            sortByLike = false;
                            sortByComment = true;
                        } else if(options[item].equals("Terbaru")){
                            sortByComment = false;
                            sortByLike = false;
                        }
                    }

                }).setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        Intent intent = new Intent(com.example.tommywahyu44.cekin.Search.Forum.this, com.example.tommywahyu44.cekin.Search.Forum.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.show();

                break;
        }
        return true;
    }

//    public static class Comparators {
//        public static Comparator<ItemForum> sortByComent = new Comparator<ItemForum>() {
//            @Override
//            public int compare(ItemForum o1, ItemForum o2) {
//                return o1.getForumJumlahKomen() - o2.getForumJumlahKomen();
//            }
//        };
//        public static Comparator<ItemForum> sortByLike = new Comparator<ItemForum>() {
//            @Override
//            public int compare(ItemForum o1, ItemForum o2) {
//                return o1.getForumJumlahLike() - o2.getForumJumlahLike();
//            }
//        };
//    }
public static String currency (int a) {
    // write your code here

    NumberFormat currencyFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
    return "Rp. "+ currencyFormat.format(a);
}

}