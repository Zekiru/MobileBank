package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    static String[] input = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent loginSuccess = new Intent(
                getApplicationContext(), BankMain.class
        );

        Button Login = findViewById(R.id.loginBtn);
        Login.setOnClickListener(view -> {
            EditText in1 = findViewById(R.id.username);
            EditText in2 = findViewById(R.id.password);

            if(TextUtils.isEmpty(in1.getText()) || TextUtils.isEmpty(in2.getText())) {
                Alert("Please fill in all the fields.");
                return;
            }

            input[0] = in1.getText().toString();
            input[1] = in2.getText().toString();

            Bank.init();

            BankExternal b = new BankExternal();

            if (b.login(input[0], input[1])) {
                startActivity(loginSuccess);
                in1.setText("");
                in2.setText("");
                return;
            }

            Alert("Login Failed.");
        });
    }

    private void Alert(String dialog) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(dialog);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}