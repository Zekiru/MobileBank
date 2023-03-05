package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    // String Array used to record and share Log In Inputs to Inheritors:
    static String[] input = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declare an Intent that calls the Next Activity:
        Intent loginSuccess = new Intent(
                getApplicationContext(), BankMain.class
        );

        // Calls the Log In Button and adds an On-Click Listener to it:
        Button Login = findViewById(R.id.loginBtn);
        Login.setOnClickListener(view -> {
            // Call the EditTexts to get Text Values From:
            EditText in1 = findViewById(R.id.username);
            EditText in2 = findViewById(R.id.password);

            // Checks if all EditTexts are filled out before proceeding:
            if(TextUtils.isEmpty(in1.getText()) || TextUtils.isEmpty(in2.getText())) {
                Alert("Please fill in all the fields.");
                return;
            }

            // Records the user's inputs:
            input[0] = in1.getText().toString();
            input[1] = in2.getText().toString();

            // Initialize the Bank's Information Gathering:
            Bank.init();

            // Create an object to access Bank Information:
            BankExternal b = new BankExternal();

            // Log In Conditional Statement:
            if (b.login(input[0], input[1])) {
                // If Log In is successful, Start the next Activity and clear all EditTexts:
                startActivity(loginSuccess);
                in1.setText("");
                in2.setText("");
                return;
            }

            // Alerts the user if the Log In was Unsuccessful:
            Alert("Login Failed.");
        });
    }

    // A Method to call Alert Dialogs:
    private void Alert(String dialog) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(dialog);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}