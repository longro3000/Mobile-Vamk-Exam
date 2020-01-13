package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class AddCustomerActivity extends AppCompatActivity {

    DBAdapter dbAdapter=null;
    Button issueOrder, backButton;
    String viewCart = "";
    EditText firstName, lastName, phone, email, address, id;
    Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        dbAdapter = new DBAdapter(getApplicationContext());

        issueOrder = findViewById(R.id.orderIssue);

        id = findViewById(R.id.idEditText);
        firstName = findViewById(R.id.firstNameEditText);
        lastName = findViewById(R.id.lastNameEditText);
        phone = findViewById(R.id.phoneNumberEditText);
        email = findViewById(R.id.emailEditText);
        address = findViewById(R.id.addressEditText);

        backButton = findViewById(R.id.backButton2);
        issueOrder.setOnClickListener(buttonIssueClickListener);

        backButton.setOnClickListener(buttonClickListener);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            viewCart = extras.getString("viewCart");
            // Here we set the hint value of the edit text to the data
            // sent by calling activity
        }

    }

    private View.OnClickListener buttonIssueClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//Here we initialize the content of the text view
            if (id.getText().length()==0 || firstName.getText().length()==0 || lastName.getText().length()==0
                    || phone.getText().length()==0 || email.getText().length()==0 || address.getText().length()==0){
                Toast.makeText(getApplication(), "SOME FIELD IS MISSING", Toast.LENGTH_LONG).show();
            } else {
                //Here we add a new customer data
                dbAdapter.open();
                String date = "" + new Date();
                customer = new Customer(firstName.getText().toString(),
                        lastName.getText().toString(),
                        phone.getText().toString(),
                        email.getText().toString(),
                        address.getText().toString());

                long idDB = dbAdapter.addCustomer(Integer.parseInt(id.getText().toString()),
                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        phone.getText().toString(),
                        email.getText().toString(),
                        address.getText().toString(),
                        viewCart);


                displayResult(idDB);
                //Here we close database connection
                dbAdapter.close();

                viewCart += customer.toString();

                Intent intent = new Intent(getApplication(), SummaryActivity.class);
                intent.putExtra("customer", viewCart);
                startActivity(intent);
            }
        }
    };

    private void displayResult(long id) {
        Toast.makeText(getApplication(),  "add successfully with customer ID: " + id, Toast.LENGTH_LONG).show();
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//Here we initialize the content of the text view
            finish();
        }
    };

}
