package com.example.hairshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class barbers extends AppCompatActivity {

    ListView listView;
    String mtit[]={"RajeshBhai","VijayBhai","vipulBhai"};
    String stit[]={"9825265361","9879399832","9825164094"};
    Integer img1[] = {R.drawable.g1,R.drawable.g2,R.drawable.g3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barbers);

        listView = (ListView)findViewById(R.id.list);

        MyAdepter adepter = new MyAdepter(this,mtit,stit,img1);
        listView.setAdapter(adepter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(position == 0)
                {
                    Intent intent  = new Intent(barbers.this,history.class);
                    startActivity(intent);
                }
                if(position == 1)
                {
                    Intent intent  = new Intent(barbers.this,history.class);
                    startActivity(intent);
                }
                if(position == 2)
                {
                    Intent intent  = new Intent(barbers.this,history.class);
                    startActivity(intent);
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navibar);

        bottomNavigationView.setSelectedItemId(R.id.item2);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.item1:
                        startActivity(new Intent(getApplicationContext(),home.class));

                        overridePendingTransition(0,0);
                        return true;

                    case R.id.item2:
                        return true;

                    case R.id.item3:
                        startActivity(new Intent(getApplicationContext(),book_it.class));

                        overridePendingTransition(0,0);
                        return true;


                    case R.id.item4:
                        startActivity(new Intent(getApplicationContext(),gallery.class));

                        overridePendingTransition(0,0);
                        return true;


                    case R.id.item5:
                        startActivity(new Intent(getApplicationContext(),More.class));

                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    class MyAdepter extends ArrayAdapter<String>
    {

        Context context;
        String rtitle[];
        String stitle[];
        Integer imgs[];

        MyAdepter (Activity c, String title[], String dtitle[], Integer imgd[])
        {
            super(c,R.layout.row, R.id.mi, title );
            //super(c,R.layout.row,R.id.si, S );
            this.context = c;
            this.rtitle = title;
            this.stitle = dtitle;
            this.imgs = imgd;
        }
        public View getView(int Position, View convertView, ViewGroup parent)
        {

            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row,parent,false);
            ImageView imgs = row.findViewById(R.id.img);
            TextView main = row.findViewById(R.id.mi);
            TextView sho = row.findViewById(R.id.si);

            imgs.setImageResource(img1[Position]);
            main.setText(mtit[Position]);
            sho.setText(stit[Position]);
            return row;
        }
    }
}
