package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class BankMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_main);

        Intent transferClicked = new Intent(
                getApplicationContext(), BankTransfer.class
        );

        Intent balanceClicked = new Intent(
                getApplicationContext(), BankBalance.class
        );

        TextView greeting = findViewById(R.id.userGreeting);
        TextView info = findViewById(R.id.account_details);

        BankExternal b = new BankExternal();

        greeting.setText("Greetings, " + b.getUsername());
        info.setText("Account Information:\nFull Name - " + b.geFName() + "\nBank ID - " + b.getBankId());

        Button balance = findViewById(R.id.balanceBtn);
        balance.setOnClickListener(view -> {
            startActivity(balanceClicked);
        });

        Button transfer = findViewById(R.id.transferBtn);
        transfer.setOnClickListener(view -> {
            startActivity(transferClicked);
        });
    }
}