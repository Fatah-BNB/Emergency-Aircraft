package com.project.emergencyaircraft;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    User savedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        FirebaseMessaging firebaseMessaging=FirebaseMessaging.getInstance();
        User user = new User();
        user.setFullName("islem");
        user.setEmail("benarab2000@gmail.com");
        user.setPassword("islem123");
        user.setUsername("islem12345");
        user.setRole("admin");
        myRef.child(user.getUsername()).setValue(user);
        String username = prefs.getString("username", null);
        myRef.child(username).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                // Create user object
                savedUser = snapshot.getValue(User.class);

            }
        });

        firebaseMessaging.getToken().addOnCompleteListener(task2 -> {
            if (task2.isSuccessful()){
                String savedToken=task2.getResult();
                Map<String,Object> map=new HashMap<>();
                map.put("token",savedToken);
                myRef.child(username).updateChildren(map).isSuccessful();
            }
        });        BottomNavigationView bottomNavView = findViewById(R.id.bottomNavView);
        bottomNavView.setOnNavigationItemSelectedListener(this);
        // Set the default selected item to "Check Notifications"
        bottomNavView.setSelectedItemId(R.id.menu_check_notifications);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation item selection
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.menu_check_notifications:
                selectedFragment = new CheckNotificationsFragment();
                break;
            case R.id.menu_send_notification:
                selectedFragment = new SendNotificationFragment();
                break;
            case R.id.menu_user_profile:
                selectedFragment = new UserProfileFragment(savedUser);
                break;
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, selectedFragment)
                    .commit();
        }

        return true;
    }
}
