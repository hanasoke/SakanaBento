package com.example.stocksakanabento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);

        Info.setText("No of attemps remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void validate(String userName, String userPassword) {
        if ((userName.equals("Admin")) && (userPassword.equals("1234"))) {
            Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
            Toast.makeText(getApplicationContext(), "You are Admin", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        } else if((userName.equals("Admin")) != (userPassword.equals("1234"))) {
            Toast.makeText(getApplicationContext(), "You are not Admin", Toast.LENGTH_SHORT).show();
        } else {
            counter--;

            Info.setText("No of atteps remaining: " + String.valueOf(counter));


            if (counter == 0) {
                Login.setEnabled(false);
            }
        }
        if(Name.getText().length() == 0) {
            Name.setError("Fill the Nama");
        } else if(Password.getText().length() == 0) {
            Password.setError("Fill the Password");
        }
    }
}
