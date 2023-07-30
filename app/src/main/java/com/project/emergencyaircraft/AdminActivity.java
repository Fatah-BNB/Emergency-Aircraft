package com.project.emergencyaircraft;// AdminActivity.java
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.emergencyaircraft.NotificationsFragment;
import com.project.emergencyaircraft.UsersFragment;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.action_check_notifications); // Set "Check Notification List" as the default selected item

        // Set the default fragment to NotificationsFragment when the AdminActivity is created
        displayNotificationsFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_check_notifications:
                        displayNotificationsFragment();
                        return true;
                    case R.id.action_list_users:
                        displayUsersFragment();
                        return true;
                }
                return false;
            }
        });
    }

    private void displayNotificationsFragment() {
        Fragment notificationsFragment = new NotificationsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, notificationsFragment);
        fragmentTransaction.commit();
    }

    private void displayUsersFragment() {
        Fragment usersFragment = new UsersFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, usersFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
