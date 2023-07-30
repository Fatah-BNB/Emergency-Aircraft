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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.emergencyaircraft.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class LoginActivity extends AppCompatActivity {

    private static final String HARDCODED_USERNAME = "user";
    private static final String HARDCODED_PASSWORD = "123";

    private static final String HARDCODED_USERNAME_ADMIN = "admin";
    private static final String HARDCODED_PASSWORD_ADMIN = "admin";

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private CheckBox loginAsAdminCheckbox;
    FirebaseDatabase database;
    DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");
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
        String email = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        CompletableFuture<String> future = new CompletableFuture<>();
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> userList = new ArrayList<>();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String phone = userSnapshot.getKey();
                    User user = userSnapshot.getValue(User.class);
                    user.setUsername(phone);
                    userList.add(user);
                }
                Optional<User> optionalUser = userList.stream()
                        .filter(user -> user.getEmail().equals(email))
                        .findFirst();

                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    if (user.getPassword().equals(password))
                        if (user.getRole().equals("admin")) {
                            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    else {
                        Toast.makeText(LoginActivity.this,"invalid credentials",Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });
    }
}
