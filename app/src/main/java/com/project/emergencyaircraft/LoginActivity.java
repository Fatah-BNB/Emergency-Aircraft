package com.project.emergencyaircraft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.emergencyaircraft.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String HARDCODED_USERNAME = "user";
    private static final String HARDCODED_PASSWORD = "123";

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.equals(HARDCODED_USERNAME) && password.equals(HARDCODED_PASSWORD)) {
            // Successful login, navigate to MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Optional - to prevent the user from going back to the login screen using the back button
        } else {
            // Invalid credentials, show an error message (e.g., using a Toast)
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}
