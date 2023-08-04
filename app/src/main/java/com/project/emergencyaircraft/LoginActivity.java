package com.project.emergencyaircraft;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class LoginActivity extends AppCompatActivity {


    private EditText usernameEditText;
    private EditText passwordEditText;
    FirebaseDatabase database;
    DatabaseReference usersRef;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");
        prefs = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        String username = prefs.getString("username", null);
        if (username != null) {
            usersRef.child(username).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    // Create user object
                    User user = snapshot.getValue(User.class);
                    Intent intent;
                    assert user != null;
                    if (user.getRole().equals("admin")) {
                        intent = new Intent(this, AdminActivity.class);
                    } else {
                        intent = new Intent(this, MainActivity.class);
                    }
                    startActivity(intent);
                }
            });
        } else {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_login);
            usernameEditText = findViewById(R.id.usernameEditText);
            passwordEditText = findViewById(R.id.passwordEditText);
            Button loginButton = findViewById(R.id.loginButton);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });
        }
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
                    assert user != null;
                    user.setUsername(phone);
                    userList.add(user);
                }
                Optional<User> optionalUser = userList.stream()
                        .filter(user -> user.getEmail().equals(email))
                        .findFirst();

                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    if (user.getPassword().equals(password)) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("username", optionalUser.get().getUsername());
                        editor.apply();
                        Intent intent;
                        if (user.getRole().equals("admin")) {
                            intent = new Intent(LoginActivity.this, AdminActivity.class);
                        } else {
                             intent = new Intent(LoginActivity.this, MainActivity.class);

                        }
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "invalid credentials", Toast.LENGTH_SHORT).show();
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

