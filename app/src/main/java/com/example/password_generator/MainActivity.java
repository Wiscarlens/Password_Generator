package com.example.password_generator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {
    private TextView passwordView;
    private ImageButton favorite;
    private ImageButton share;
    private ImageButton copy;
    private SeekBar passwordLength;
    private CheckBox lowercase;
    private CheckBox uppercase;
    private CheckBox digits;
    private CheckBox specialCharacters;
    private Button generateButton;

    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordView = findViewById(R.id.passwordView);
        favorite = findViewById(R.id.favoriteButton);
        share = findViewById(R.id.shareButton);
        copy = findViewById(R.id.copyButton);
        passwordLength = findViewById(R.id.passwordLengthSeekBar);
        lowercase = findViewById(R.id.lowercase_checkBox);
        uppercase = findViewById(R.id.uppercase_checkBox);
        digits = findViewById(R.id.digit_checkBox);
        specialCharacters = findViewById(R.id.specialCharacters_checkBox);
        generateButton = findViewById(R.id.generateButton);

        favorite.setColorFilter(Color.GRAY);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!lowercase.isChecked() && !uppercase.isChecked() && !digits.isChecked() && !specialCharacters.isChecked()){
                    Toast.makeText(MainActivity.this, "Check at least one case", Toast.LENGTH_SHORT).show();
                } else {
                    generatePassword();
                }

            }
        });

        favorite.setOnClickListener(v -> toggleFavorite());

    }

    private void generatePassword() {
        int length = passwordLength.getProgress() + 8; // Minimum length of 8 characters
        boolean useLowercase = lowercase.isChecked();
        boolean useUppercase = uppercase.isChecked();
        boolean useDigits = digits.isChecked();
        boolean useSpecialCharacters = specialCharacters.isChecked();

        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digitCharacters = "0123456789";
        String specialCharacters = "!@#$%^&*()_+";

        String characters = "";
        if (useLowercase) {
            characters += lowercaseLetters;
        }
        if (useUppercase) {
            characters += uppercaseLetters;
        }
        if (useDigits) {
            characters += digitCharacters;
        }
        if (useSpecialCharacters) {
            characters += specialCharacters;
        }

        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        passwordView.setText(password.toString());
    }

    private void toggleFavorite() {
        isFavorite = !isFavorite;
        updateFavoriteButton();
    }

    private void updateFavoriteButton() {
        if (isFavorite) {
            favorite.setColorFilter(Color.RED);
        } else {
            favorite.setColorFilter(Color.GRAY);
        }
    }
}