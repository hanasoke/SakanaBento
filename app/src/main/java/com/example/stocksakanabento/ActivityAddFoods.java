package com.example.stocksakanabento;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class ActivityAddFoods extends Activity implements View.OnClickListener  {
    private Button addTodoBtn;
    private EditText priceEditText;
    private EditText foodEditText;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Food");
        setContentView(R.layout.activity_addfoods);
        priceEditText = (EditText) findViewById(R.id.price_edittext);
        foodEditText = (EditText) findViewById(R.id.food_edittext);
        addTodoBtn = (Button) findViewById(R.id.add_record);
        // Membuat objek dari kelas DBManager
        dbManager = new DBManager(this);
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:
                // Mengambil data inputan
                final String price = priceEditText.getText().toString();
                final String food = foodEditText.getText().toString();
                if(priceEditText.getText().length()==0) {
                    priceEditText.setError("Fill The Price");
                } else if(priceEditText.getText().length()== 5000) {
                    priceEditText.setError("The Price is greater than 5000");
                } else if(foodEditText.getText().length() == 0) {
                    foodEditText.setError("Fill the Food Name");
                }
        /* Memanggil fungsi insert melalui objek dbManager dengan parameter
                harga dan makanan */
                dbManager.insert(price, food);
                // Memindahkan halaman kembali ke daftar foods
                Intent main = new Intent(ActivityAddFoods.this, ActivityDataFoods.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }
        Toast.makeText(getApplicationContext(), "Successfully Add", Toast.LENGTH_SHORT).show();
    }
}
