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

        // Declare an Intent that calls the Transfer Activity:
        Intent transferClicked = new Intent(
                getApplicationContext(), BankTransfer.class
        );

        // Declare an Intent that calls the Balance Activity:
        Intent balanceClicked = new Intent(
                getApplicationContext(), BankBalance.class
        );

        // Call the TextViews for setting Text Values:
        TextView greeting = findViewById(R.id.userGreeting);
        TextView info = findViewById(R.id.account_details);

        // Create an object to access Bank Information:
        BankExternal b = new BankExternal();

        // Set Text for called TextViews from information of the Bank External object:
        greeting.setText("Greetings, " + b.getUsername() + ".\nWelcome to Nougat Bank!");
        info.setText("Account Information:\nFull Name - " + b.geFName() + "\nBank ID - " + b.getBankId());

        // Calls the Balance Button and adds an On-Click Listener to it:
        Button balance = findViewById(R.id.balanceBtn);
        balance.setOnClickListener(view -> {
            // If the Balance Button is Clicked, Start the Balance Activity:
            startActivity(balanceClicked);
        });

        // Calls the Transfer Button and adds an On-Click Listener to it:
        Button transfer = findViewById(R.id.transferBtn);
        transfer.setOnClickListener(view -> {
            // If the Transfer Button is Clicked, Start the Transfer Activity:
            startActivity(transferClicked);
        });
    }
}