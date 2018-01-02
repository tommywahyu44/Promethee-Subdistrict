package com.example.tommywahyu44.promethee.ListKecamatanSet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tommywahyu44.promethee.R;

import java.util.List;

import static com.example.tommywahyu44.promethee.ListKecamatanSet.SetKecamatanActivity.countKriteria;

/**
 * Created by tommywahyu44 on 4/12/2017.
 */

public class AdapterSetKecamatan extends RecyclerView.Adapter<AdapterSetKecamatan.ViewHolder> {

    public static String a;


    private List<ItemSetKecamatan> listItems;
    private Context context;

    public AdapterSetKecamatan(List<ItemSetKecamatan> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public AdapterSetKecamatan.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.set_kecamatan_item_list, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final AdapterSetKecamatan.ViewHolder holder, final int position) {

        final ItemSetKecamatan listItem = listItems.get(position);

        switch (countKriteria) {
            case 10:
                holder.KKT1.setVisibility(View.VISIBLE);
                holder.KKT2.setVisibility(View.VISIBLE);
                holder.KKT1.setText(listItem.getKKT1());
                holder.KKT2.setText(listItem.getKKT2());
            case 9:
                holder.KK91.setVisibility(View.VISIBLE);
                holder.KK92.setVisibility(View.VISIBLE);
                holder.KK91.setText(listItem.getKK91());
                holder.KK92.setText(listItem.getKK92());
            case 8:
                holder.KK81.setVisibility(View.VISIBLE);
                holder.KK82.setVisibility(View.VISIBLE);
                holder.KK81.setText(listItem.getKK81());
                holder.KK82.setText(listItem.getKK82());
            case 7:
                holder.KK71.setVisibility(View.VISIBLE);
                holder.KK72.setVisibility(View.VISIBLE);
                holder.KK71.setText(listItem.getKK71());
                holder.KK72.setText(listItem.getKK72());
            case 6:
                holder.KK61.setVisibility(View.VISIBLE);
                holder.KK62.setVisibility(View.VISIBLE);
                holder.KK61.setText(listItem.getKK61());
                holder.KK62.setText(listItem.getKK62());
            case 5:
                holder.KK51.setVisibility(View.VISIBLE);
                holder.KK52.setVisibility(View.VISIBLE);
                holder.KK51.setText(listItem.getKK51());
                holder.KK52.setText(listItem.getKK52());
            case 4:
                holder.KK41.setVisibility(View.VISIBLE);
                holder.KK42.setVisibility(View.VISIBLE);
                holder.KK41.setText(listItem.getKK41());
                holder.KK42.setText(listItem.getKK42());
            case 3:
                holder.KK31.setVisibility(View.VISIBLE);
                holder.KK32.setVisibility(View.VISIBLE);
                holder.KK31.setText(listItem.getKK31());
                holder.KK32.setText(listItem.getKK32());
            case 2:
                holder.KK21.setVisibility(View.VISIBLE);
                holder.KK22.setVisibility(View.VISIBLE);
                holder.KK21.setText(listItem.getKK21());
                holder.KK22.setText(listItem.getKK22());
            case 1:
                holder.KK11.setVisibility(View.VISIBLE);
                holder.KK12.setVisibility(View.VISIBLE);
                holder.KK11.setText(listItem.getKK11());
                holder.KK12.setText(listItem.getKK12());
        }
        holder.namaKecamatan.setText(listItem.namaKecamatan);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == holder.itemView) {
                    final Intent intent;

                    intent = new Intent(context, EditKecamatanActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", holder.namaKecamatan.getText().toString());
                    switch (countKriteria) {
                        case 10:
                            intent.putExtra("nama10", holder.KKT1.getText().toString());

                        case 9:
                            intent.putExtra("nama9", holder.KK91.getText().toString());

                        case 8:
                            intent.putExtra("nama8", holder.KK81.getText().toString());

                        case 7:
                            intent.putExtra("nama7", holder.KK71.getText().toString());
                        case 6:
                            intent.putExtra("nama6", holder.KK61.getText().toString());

                        case 5:
                            intent.putExtra("nama5", holder.KK51.getText().toString());

                        case 4:
                            intent.putExtra("nama4", holder.KK41.getText().toString());

                        case 3:
                            intent.putExtra("nama3", holder.KK31.getText().toString());

                        case 2:
                            intent.putExtra("nama2", holder.KK21.getText().toString());

                        case 1:
                            intent.putExtra("nama1", holder.KK11.getText().toString());

                    }
                    context.startActivity(intent);

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView KK11, KK12, KK21, KK22, KK31, KK32, KK41, KK42,
                KK51, KK52, KK61, KK62, KK71, KK72, KK81, KK82,
                KK91, KK92, KKT1, KKT2, namaKecamatan;


        public ViewHolder(View itemView) {
            super(itemView);
            KK11 = itemView.findViewById(R.id.KK11);
            KK21 = itemView.findViewById(R.id.KK21);
            KK31 = itemView.findViewById(R.id.KK31);
            KK41 = itemView.findViewById(R.id.KK41);
            KK51 = itemView.findViewById(R.id.KK51);
            KK61 = itemView.findViewById(R.id.KK61);
            KK71 = itemView.findViewById(R.id.KK71);
            KK81 = itemView.findViewById(R.id.KK81);
            KK91 = itemView.findViewById(R.id.KK91);
            KKT1 = itemView.findViewById(R.id.KKT1);
            KK12 = itemView.findViewById(R.id.KK12);
            KK22 = itemView.findViewById(R.id.KK22);
            KK32 = itemView.findViewById(R.id.KK32);
            KK42 = itemView.findViewById(R.id.KK42);
            KK52 = itemView.findViewById(R.id.KK52);
            KK62 = itemView.findViewById(R.id.KK62);
            KK72 = itemView.findViewById(R.id.KK72);
            KK82 = itemView.findViewById(R.id.KK82);
            KK92 = itemView.findViewById(R.id.KK92);
            KKT2 = itemView.findViewById(R.id.KKT2);
            namaKecamatan = itemView.findViewById(R.id.namaKecamatan);


            itemView.setClickable(true);
            itemView.setOnClickListener(this);


        }


        public void onClick(final View v) {

        }
    }
}
