package net.com.gopal.cardview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class cardData extends AppCompatActivity {
TextView Name, cardNumber,instNumber;
ImageView cardType;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_data);
        Name = findViewById(R.id.BankName);
        cardNumber = findViewById(R.id.CardNumber);
        instNumber = findViewById(R.id.ValidDate);
        cardType = findViewById(R.id.CardType);
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String CardNumber = intent.getStringExtra("cardNumber");
            String InstNumber = intent.getStringExtra("instNumber");
            String cardType = intent.getStringExtra("cardType");

            cardNumber.setText(CardNumber);
            Name.setText(name);
            instNumber.setText(InstNumber);
            // Set card type image based on card type string (cardType)
//            cardTypes.setImageResource(R.drawable.your_card_type_image);
        }
    }
}