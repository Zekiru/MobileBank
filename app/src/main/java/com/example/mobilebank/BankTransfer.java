package com.example.mobilebank;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class BankTransfer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_transfer);

        // Call the EditTexts to get Text Values From:
        EditText in1 = findViewById(R.id.transferId);
        EditText in2 = findViewById(R.id.transferAmount);

        // Calls the Balance Button and adds an On-Click Listener to it:
        Button transfer = findViewById(R.id.transferConfirmBtn);
        transfer.setOnClickListener(view -> {
            // Checks if all EditTexts are filled out before proceeding:
            if(TextUtils.isEmpty(in1.getText()) || TextUtils.isEmpty(in2.getText())) {
                Alert("Please fill in all the fields.");
                return;
            }

            // Implement a Try & Catch for Exceptions:
            try {
                // Create an object and array to access Bank Information:
                BankExternal b = new BankExternal();

                // Checks if the Transfer Account is the same with the Current Account:
                if (b.setId(in1.getText().toString()) == (b.getId())) {
                    Alert("Cannot Transfer to the Same Account.");
                    return;
                }

                // Checks if the Current Account has sufficient funds for Transferring:
                if (Integer.parseInt(in2.getText().toString()) > b.getBalance()) {
                    Alert("Insufficient Funds.");
                    return;
                }

                // Checks if the Transferee ID is Present in the System:
                if (b.setId(in1.getText().toString()) != -1) {
                    // If True, Transfer Funds from Current Account to the Designated Transferee Account:
                    b.balanceTransfer(b.setId(in1.getText().toString()), Integer.parseInt(in2.getText().toString()));
                    // Alert the User that the Transfer was Successful:
                    Alert("Balance Transfer Successful!");
                } else {
                    // Else, Alert the User that the Transfer was Unsuccessful:
                    Alert("Account Not Found.");
                }

                return;
            } catch (Exception e) {}
            // If Exceptions are caught, Alert the User that the Transfer Failed:
            Alert("Balance Transfer Failed.");
        });
    }

    // A Method to call Alert Dialogs:
    private void Alert(String dialog) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BankTransfer.this);
        builder.setMessage(dialog);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}