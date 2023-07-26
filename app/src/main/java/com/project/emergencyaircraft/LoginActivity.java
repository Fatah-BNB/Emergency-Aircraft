package com.project.emergencyaircraft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.emergencyaircraft.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String HARDCODED_USERNAME = "user";
    private static final String HARDCODED_PASSWORD = "123";

    private static final String HARDCODED_USERNAME_ADMIN = "admin";
    private static final String HARDCODED_PASSWORD_ADMIN = "admin";

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private CheckBox loginAsAdminCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        loginAsAdminCheckbox = findViewById(R.id.loginAsAdminCheckbox);

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

        if(loginAsAdminCheckbox.isChecked()){
            if(username.equals(HARDCODED_USERNAME_ADMIN) && password.equals((HARDCODED_PASSWORD_ADMIN))){
                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }else{
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
}
