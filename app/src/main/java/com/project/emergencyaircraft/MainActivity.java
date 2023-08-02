package com.project.emergencyaircraft;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavView = findViewById(R.id.bottomNavView);
        bottomNavView.setOnNavigationItemSelectedListener(this);

        // Set the default selected item to "Check Notifications
        setTitle("Notifications");
        bottomNavView.setSelectedItemId(R.id.menu_check_notifications);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation item selection
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.menu_check_notifications:
                selectedFragment = new CheckNotificationsFragment();
                setTitle("Notifications");
                break;
            case R.id.menu_send_notification:
                selectedFragment = new SendNotificationFragment();
                setTitle("Send Notification");
                break;
            case R.id.menu_user_profile:
                selectedFragment = new UserProfileFragment();
                setTitle("Profile");
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
