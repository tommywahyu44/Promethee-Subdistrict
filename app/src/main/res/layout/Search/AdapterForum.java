package com.example.tommywahyu44.cekin.Search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tommywahyu44.cekin.FirebaseImageLoader;
import com.example.tommywahyu44.cekin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by tommywahyu44 on 4/12/2017.
 */

public class AdapterForum extends RecyclerView.Adapter<com.example.tommywahyu44.cekin.Search.AdapterForum.ViewHolder> {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private StorageReference mStorageRef;
    private StorageReference fotoposter;
    private FirebaseStorage storage;
    public static String a;

    DatabaseReference mRootref = FirebaseDatabase.getInstance().getReference();

    private List<ItemForum> listItems;
    private Context context;

    public AdapterForum(List<ItemForum> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public com.example.tommywahyu44.cekin.Search.AdapterForum.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forum_item_list, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final com.example.tommywahyu44.cekin.Search.AdapterForum.ViewHolder holder, final int position) {

//        if (Forum.sortByComment == true) {
//            Collections.sort(listItems, Forum.Comparators.sortByComent);
//        } else if(Forum.sortByLike == true){
//            Collections.sort(listItems, Forum.Comparators.sortByLike);
//        }

        final ItemForum listItem = listItems.get(position);
        holder.Tempat.setText(listItem.getTempat());
        holder.Brand.setText(listItem.getBrand());
        holder.Ukuran.setText(listItem.getUkuran());
        holder.Harga.setText(listItem.getHarga());
        holder.Discount.setText(listItem.getDiscount());

         storage = FirebaseStorage.getInstance();
         mStorageRef = storage.getReferenceFromUrl("gs://cekin-938c3.appspot.com/fotoya/");
        StorageReference fotoposter = mStorageRef.child(listItem.getDiscount().toLowerCase()+".jpg");
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(fotoposter)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.Image);

//        DatabaseReference profileRef = FirebaseDatabase.getInstance().getReference().child("User").child(listItem.getIdPoster());
//        profileRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                holder.forum_poster_name.setText(dataSnapshot.child("name").getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });



//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v == holder.itemView) {
//                    final Intent intent;
//
//                    intent = new Intent(context, Komentar.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("id", holder.id);
//                    context.startActivity(intent);
//
//                }
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView Ukuran,Brand,Harga,Discount,Tempat;
        private ImageView Image;


        public ViewHolder(View itemView) {
            super(itemView);
            Ukuran = itemView.findViewById(R.id.cardUkuran);
            Brand = itemView.findViewById(R.id.cardBrand);
            Harga = itemView.findViewById(R.id.cardHarga);
            Image = itemView.findViewById(R.id.image);
            Discount = itemView.findViewById(R.id.cardDiscount);
            Tempat = itemView.findViewById(R.id.cardTempat);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);


        }


        public void onClick(final View v) {

        }
    }
}

//    public void toProfileCari(String id){
//        Intent intent = new Intent(context, ProfilCari.class);
//        intent.putExtra("id", id);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
//    }

