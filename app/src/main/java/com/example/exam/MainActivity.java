package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;

    EditText amountEditText;
    TextView numberOfProduct;
    Button addButton, removeButton, viewCartButton;

    ArrayList<Product> products = new ArrayList<Product>();
    ArrayList<String> productSpinner = new ArrayList<String>();
    ArrayList<Product> shoppingCart = new ArrayList<Product>();

    Product kidney = new Product("Kidney", "$", "NSFW Image", 50000);
    Product apple = new Product("Apple", "$", "NSFW Image", 1);
    Product beef = new Product("Beef", "$", "NSFW Image", 10);
    Product mac = new Product("MAC pro 2022", "$", "NSFW Image", 10000);
    Product huawei = new Product("Huawei Something", "$", "NSFW Image", 10000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amount);
        spinner = findViewById(R.id.productSpinner);
        InitialProductSpinner();

        addButton = findViewById(R.id.addButton);
        removeButton = findViewById(R.id.removeButton);
        viewCartButton = findViewById(R.id.viewCart);

        addButton.setOnClickListener(clickListener);
        removeButton.setOnClickListener(clickListener);
        viewCartButton.setOnClickListener(clickListener);

        numberOfProduct = findViewById(R.id.productNumber);
    }

    protected void InitialProductSpinner() {
            products.add(kidney);
            products.add(apple);
            products.add(beef);
            products.add(mac);
            products.add(huawei);
            for (Product product : products) {
                productSpinner.add(product.toStringWithoutAmount());
            }

            ArrayAdapter urlAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, productSpinner);
            urlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(urlAdapter);
    }

    protected void updateTextView() {
        StringBuilder shoppingCartSummary = new StringBuilder();
        double totalPrice = 0;
        for (Product product : shoppingCart) {
            product.totalPrice = product.amount*product.price;
            shoppingCartSummary.append(product.toString());
            totalPrice += product.totalPrice;
        }

        numberOfProduct.setText("Number Of Products in Cart: " + shoppingCart.size() + "\n" + shoppingCartSummary + "/n total Price: "+ totalPrice);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button clickedButton = (Button) v;
            if (clickedButton.getText().equals("Add To Cart")) {
                Product newItem = products.get(spinner.getSelectedItemPosition());
                int amount = Integer.parseInt(amountEditText.getText().toString());
                newItem.addAmount(amount);

                shoppingCart.add(newItem);
                updateTextView();
            }

            else if (clickedButton.getText().equals("Remove From Cart")) {

                for (int i=0; i< shoppingCart.size(); i++) {
                    if (shoppingCart.get(i).name.equals(products.get(spinner.getSelectedItemPosition()).name)) {
                        shoppingCart.remove(i);
                    }
                }

                updateTextView();
            }

            else if (clickedButton.getText().equals("View Shopping Cart")) {
                StringBuilder shoppingCartSummary = new StringBuilder();
                double totalPrice = 0;
                for (Product product : shoppingCart) {
                    product.totalPrice = product.amount*product.price;
                    shoppingCartSummary.append(product.toString());
                    totalPrice += product.totalPrice;
                }
                Intent intent = new Intent(getApplication(), ShoppingCartActivity.class);
                intent.putExtra("shoppingCart", shoppingCartSummary.toString());
                intent.putExtra("totalPrice", totalPrice);
                startActivity(intent);
            }
        }
    };
}
