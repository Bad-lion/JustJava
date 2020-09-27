package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int numberOfCoffee = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        boolean haswhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolateCheck = chocolateCheckBox.isChecked();

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();




        String priceMessage = message(name, numberOfCoffee,haswhippedCream,hasChocolateCheck, calculateMethod(haswhippedCream,hasChocolateCheck));

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just java for " + name);
        intent.putExtra(intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



    }

    public String message(String name, Integer quantity, boolean cream, boolean chocolate, int clak) {
        String price = displayMassageForCream(cream);
        price += "\n" + displayMassageForChocolate(chocolate);
        price += "\nname: " + name;
        price += "\nQuantity: " + quantity;
        price += "\nTotal price $" + clak;
        price += "\nThank You";
        return price;

    }
    public void increment(View view) {
        numberOfCoffee = numberOfCoffee + 1;
        display(numberOfCoffee);
    }

    public void decrement(View view) {
        numberOfCoffee = numberOfCoffee - 1;
        if (numberOfCoffee < 1) {
            numberOfCoffee = 1;
        }
        display(numberOfCoffee);
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_order);
        quantityTextView.setText("" + number);


    }


    public int calculateMethod(boolean whippedcream, boolean chocolate) {
        int price = 5;
        if (whippedcream) {
            price +=1;
        }
        if (chocolate) {
            price +=2;
        }
        int numb = numberOfCoffee * price;
        return numb;
    }


    private String displayMassageForCream(boolean hasWhip) {
        String message = "add whipped cream? " + hasWhip;

        return message;
    }

    private String displayMassageForChocolate(boolean hasWhip) {
        String message = "add Chocolate? " + hasWhip;

        return message;
    }

}


