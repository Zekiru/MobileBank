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

        EditText in1 = findViewById(R.id.transferId);
        EditText in2 = findViewById(R.id.transferAmount);

        Button transfer = findViewById(R.id.transferConfirmBtn);
        transfer.setOnClickListener(view -> {
            if(TextUtils.isEmpty(in1.getText()) || TextUtils.isEmpty(in2.getText())) {
                Alert("Please fill in all the fields.");
                return;
            }

            try {
                BankExternal b = new BankExternal();

                if (b.setId(in1.getText().toString()) == (b.getId())) {
                    Alert("Cannot Transfer to the Same Account.");
                    return;
                }

                if (Integer.parseInt(in2.getText().toString()) > b.getBalance()) {
                    Alert("Insufficient Funds.");
                    return;
                }

                if (b.setId(in1.getText().toString()) != -1) {
                    b.balanceTransfer(b.setId(in1.getText().toString()), Integer.parseInt(in2.getText().toString()));
                    Alert("Balance Transfer Successful!");
                } else {
                    Alert("Account Not Found.");
                }

                return;
            } catch (Exception e) {}
            Alert("Balance Transfer Failed.");
        });
    }

    private void Alert(String dialog) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BankTransfer.this);
        builder.setMessage(dialog);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}