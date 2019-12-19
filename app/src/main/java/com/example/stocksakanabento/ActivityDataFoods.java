package com.example.stocksakanabento;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

public class ActivityDataFoods extends AppCompatActivity {
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.PRICE, DatabaseHelper.FOOD };
    final int[] to = new int[] { R.id.id, R.id.price, R.id.food };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Memilih layout
        setContentView(R.layout.activity_datafoods);

        dbManager = new DBManager(this);
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        /* Adapter untuk menunjuk data di database
        untuk di tampilkan dalam list food yang mana data tersebut
        menunjuk ke fragment dari ListView */
        adapter = new SimpleCursorAdapter(this, R.layout.activity_fragment, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListener untuk Data Food yang telah ada di database
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                // Mengambil nilai list yang dipilih
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView priceTextView = (TextView) view.findViewById(R.id.price);
                TextView foodTextView = (TextView) view.findViewById(R.id.food);

                // Menyimpan nilai list yang di pilih ke variabel
                String id = idTextView.getText().toString();
                String price = priceTextView.getText().toString();
                String food = foodTextView.getText().toString();

                // Proses Intent untuk mengirim data ke halaman Edit
                Intent modify_intent = new Intent(getApplicationContext(), ActivityModifyFoods.class);
                modify_intent.putExtra("price", price);
                modify_intent.putExtra("food", food);
                modify_intent.putExtra("id", id);
                startActivity(modify_intent);
            }
        }); }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_record) {
            Intent add_mem = new Intent(this, ActivityAddFoods.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }

    public void Back(View view) {
        Intent intent = new Intent(ActivityDataFoods.this, MainActivity.class);
        startActivity(intent);
    }
}
