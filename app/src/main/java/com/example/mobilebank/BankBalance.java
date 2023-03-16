package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BankBalance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_balance);

        BankExternal b = new BankExternal();

        TextView balance = findViewById(R.id.balance);

        balance.setText("P " + b.getBalance());
    }
}