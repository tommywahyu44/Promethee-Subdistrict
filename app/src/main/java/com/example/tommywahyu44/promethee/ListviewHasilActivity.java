package com.example.tommywahyu44.promethee;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListviewHasilActivity extends AppCompatActivity {

    static String[] values = new String[14];
    ListView simpleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_hasil);
        simpleList = findViewById(R.id.simpleListView);

        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, values);
        simpleList.setAdapter(adapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Toast.makeText(ListviewHasilActivity.this,"Anda ngeklik item"+item,Toast.LENGTH_LONG).show();
            }

        });
    }


    private class MySimpleArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public MySimpleArrayAdapter(Context context, String[] values) {
            super(context, R.layout.activity_item_listview_hasil, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.activity_item_listview_hasil, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.textListviewHasil);
            textView.setText(values[position]);
            if (position <= 3){
                textView.setBackgroundResource(R.color.Merah);
            } else if (position <= 8) textView.setBackgroundResource(R.color.Coklat);

            return rowView;
        }
    }



}
