package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BankBalance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_balance);

        // Create an object to access Bank Information:
        BankExternal b = new BankExternal();

        // Call the TextView for setting Text Values:
        TextView balance = findViewById(R.id.balance);

        // Set Text for the called TextView from information of the Bank External object:
        balance.setText("P " + b.getBalance());
    }
}