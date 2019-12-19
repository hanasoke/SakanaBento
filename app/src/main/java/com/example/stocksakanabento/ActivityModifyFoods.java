package com.example.stocksakanabento;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class ActivityModifyFoods extends Activity implements View.OnClickListener{
    private EditText priceText;
    private Button updateBtn, deleteBtn;
    private EditText foodText;
    private long _id;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Food");
        setContentView(R.layout.activity_modifydata);
        dbManager = new DBManager(this);
        try {
            dbManager.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        priceText = (EditText) findViewById(R.id.price_edittext);
        foodText = (EditText) findViewById(R.id.food_edittext);
        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        /* Membuat objek Intent dengan nilai yang dikirim objek Intent
        yang telah memanggil kelas ini sebelumnya */
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String price = intent.getStringExtra("price");
        String food = intent.getStringExtra("food");

        _id = Long.parseLong(id);
        priceText.setText(price);
        foodText.setText(food);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    // Pemilihan untuk tombol yang disentuh (update/ delete)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // Pilihan Update
            case R.id.btn_update:
                // Menyimpan nilai price dan food baru ke variabel
                String price = priceText.getText().toString();
                String food = foodText.getText().toString();
                if(priceText.getText().length()==0) {
                    priceText.setError("Fill The Price");
                } else if(priceText.getText().length()== 5000) {
                    priceText.setError("The Price is greater than 5000");
                } else if(foodText.getText().length() == 0) {
                    foodText.setError("Fill the Food Name");
                }
                /* Memanggil fungsi update melalui objek dbManager
                fungsi ini membawa tiga parameter yakni _id, price, food */
                dbManager.update(_id, price, food);
                /* Setelah selesai, akan memanggil fungsi returnHome()
                untuk kembali kehalaman utama */
                this.returnHome();
                Toast.makeText(getApplicationContext(), "Successfully Modify Food", Toast.LENGTH_SHORT).show();
                break;
            // Pilihan Update
            case R.id.btn_delete:
                // Memanggil fungsi delete dengan parameter _id
                dbManager.delete(_id);
                this.returnHome();
                Toast.makeText(getApplicationContext(), "Successfully Delete Food", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // Fungsi untuk kembali ke halaman awal
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ActivityDataFoods.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

}
