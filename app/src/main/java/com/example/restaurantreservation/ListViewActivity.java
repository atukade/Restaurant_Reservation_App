package com.example.restaurantreservation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"Name 1", "Name 2", "Name 3", "Name 4", "Name 5"};
    String mDescription[] = {"Des 1", "Des 2", "Des 3", "Des 4", "Des 5"};
    int images[] = {R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });

    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rTtile[];
        String rDescription[];
        int rImgs[];

        MyAdapter(Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTtile = title;
            this.rDescription = description;
            this.rImgs = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView imageView = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            imageView.setImageResource(rImgs[position]);
            myTitle.setText(rTtile[position]);
            myDescription.setText(rDescription[position]);

            return row;
        }
    }
}
