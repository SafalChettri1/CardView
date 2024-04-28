package net.com.gopal.cardview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView Name, cardNumber, instNumber;
    EditText editName, editCardNumber, editInstNumber, editCardTypes;
    Button continueBtn;
    ImageView cardTypes;
//    private StringBuilder formattedCardNumber;
//    private static final int GROUP_SIZE = 4;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = findViewById(R.id.bankName);
        cardNumber = findViewById(R.id.CardNumber);
        instNumber= findViewById(R.id.validDate);
        cardTypes = findViewById(R.id.cardTypes);
        editName = findViewById(R.id.editName);
        editCardNumber = findViewById(R.id.editCardNumber);
        editInstNumber = findViewById(R.id.editInstNumber);
        editCardTypes = findViewById(R.id.editCardTypes);
        continueBtn = findViewById(R.id.continueBtn);



        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String cardNumber = editCardNumber.getText().toString();
                String instNumber = editInstNumber.getText().toString();
                CardTypes cardType = CardTypes.detectCardType(cardNumber);

                Log.d("CardView", "Name: " + name);
                Log.d("CardView", "Card Number: " + cardNumber);
                Log.d("CardView", "Institution Number: " + instNumber);
                Log.d("CardView", "Card Type: " + cardType.getDisplayName());

//                int imageResource = getCardImageResource(cardType);

                Intent intent = new Intent(MainActivity.this, cardData.class);
                intent.putExtra("name", name);
                intent.putExtra("cardNumber", cardNumber);
                intent.putExtra("instNumber", instNumber);
                intent.putExtra("cardType", cardType.getDisplayName());
//                intent.putExtra("cardTypeImage", imageResource); // Add this line
                startActivity(intent);
            }
        });

        editCardNumber.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String formattedCardNumber = formatCardNumber(s.toString());

                // Update cardNumber TextView with formatted card number
                cardNumber.setText(formattedCardNumber);

            }

            @Override
            public void afterTextChanged(Editable s) {
                String cardNumber = s.toString().replaceAll("[^\\d]", ""); // Remove non-numeric characters
                CardTypes cardType = CardTypes.detectCardType(cardNumber);
                updateCardTypeImage(cardType);
                updateCardTypeEditText(cardType);


            }
        });
//        editValidDate.addTextChangedListener(new ValidNameTextWatcher());
        editName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Name.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editInstNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                instNumber.setText(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void updateCardTypeEditText(CardTypes cardType) {
        String cardTypeName = cardType.getDisplayName();
        editCardTypes.setText(cardTypeName);

    }

    private String formatCardNumber(String cardNumber) {
        StringBuilder formatted = new StringBuilder();
        int groupSize = 4;
        for (int i = 0; i < cardNumber.length(); i++) {
            if (i > 0 && i % groupSize == 0) {
                formatted.append("  ");
            }
            formatted.append(cardNumber.charAt(i));
        }
        return formatted.toString();
    }
//    private void formatCardNumbers(Editable s) {
//        String cleanText = s.toString().replaceAll("-", "");
//
//        StringBuilder formattedText = new StringBuilder();
//        int groupStartIndex = 0;
//        while (groupStartIndex < cleanText.length()) {
//            int groupEndIndex = Math.min(groupStartIndex + GROUP_SIZE, cleanText.length());
//            formattedText.append(cleanText.substring(groupStartIndex, groupEndIndex));
//            if (groupEndIndex < cleanText.length()) {
//                formattedText.append("-");
//            }
//            groupStartIndex = groupEndIndex;
//        }
//
//        int cursorPosition = editCardNumber.getSelectionEnd();
//        editCardNumber.setText(formattedText);
//
//        // Adjust cursor position based on added hyphens
//        int lengthDiff = formattedText.length() - cleanText.length();
//        int newPosition = cursorPosition + lengthDiff;
//        if (newPosition > 0 && newPosition <= formattedText.length()) {
//            editCardNumber.setSelection(newPosition);
//        } else {
//            editCardNumber.setSelection(formattedText.length());
//        }
//    }
private void updateCardTypeImage(CardTypes cardType) {
    int imageResource;
    switch (cardType) {
        case VISA:
            imageResource = R.drawable.visa;
            break;
        case MASTERCARD:
            imageResource = R.drawable.mastercard;
            break;
        case AMERICAN_EXPRESS:
            imageResource = R.drawable.amex;
            break;
        case DISCOVER:
            imageResource = R.drawable.discover;
            break;

        default:
            imageResource = R.drawable.unionpay;
            break;
    }
    cardTypes.setImageResource(imageResource);
}
}
