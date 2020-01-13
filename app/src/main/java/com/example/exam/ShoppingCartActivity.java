package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShoppingCartActivity extends AppCompatActivity {

    Button addCustomer, backButton;

    TextView shoppingCart = null;

    String viewCart = "";
    double totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        addCustomer = findViewById(R.id.addCustomer);

        shoppingCart = findViewById(R.id.summary1);
        backButton = findViewById(R.id.backButton1);

        addCustomer.setOnClickListener(buttonAddCustomerClickListener);
        backButton.setOnClickListener(buttonClickListener);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            viewCart = extras.getString("shoppingCart");

            totalPrice = extras.getDouble("totalPrice");
            // Here we set the hint value of the edit text to the data
            // sent by calling activity
        }

        viewCart += "\n total Price: " + totalPrice;
        shoppingCart.setText(viewCart);


    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//Here we initialize the content of the text view
            finish();
        }
    };

    private View.OnClickListener buttonAddCustomerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//Here we initialize the content of the text view
            Intent intent = new Intent(getApplication(), AddCustomerActivity.class);
            intent.putExtra("viewCart", viewCart);
            startActivity(intent);
        }
    };

}
