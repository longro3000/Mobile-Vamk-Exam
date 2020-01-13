package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    TextView shoppingCart = null;
    Button backButtonToHome;
    String viewCart = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        shoppingCart = findViewById(R.id.summary1);

        backButtonToHome = findViewById(R.id.backButtonToHome);

        backButtonToHome.setOnClickListener(buttonClickListener);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            viewCart = extras.getString("customer");

            // Here we set the hint value of the edit text to the data
            // sent by calling activity
        }

        shoppingCart.setText(viewCart);

    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//Here we initialize the content of the text view
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        }
    };
}
