package com.project.emergencyaircraft;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        BottomNavigationView bottomNavView = findViewById(R.id.bottomNavView);
        User user=new User();
        user.setFullName("islem");
        user.setEmail("benarab2000@gmail.com");
        user.setPassword("islem123");
        user.setUsername("islem12345");
        user.setRole("admin");
        myRef.child(user.getUsername()).setValue(user);
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
                selectedFragment = new UserProfileFragment();
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
