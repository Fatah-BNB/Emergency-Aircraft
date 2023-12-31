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
        setTitle("Notifications");
        bottomNavigationView.setSelectedItemId(R.id.action_check_notifications); // Set "Check Notification List" as the default selected item

        // Set the default fragment to NotificationsFragment when the AdminActivity is created
        displayNotificationsFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_check_notifications:
                        displayNotificationsFragment();
                        setTitle("Notifications");
                        return true;
                    case R.id.action_list_users:
                        displayUsersFragment();
                        setTitle("Users");
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
        // Check if there is any fragment in the back stack
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (currentFragment instanceof AddUserFragment) {
            // If the current fragment is AddUserFragment, pop the back stack and show UsersFragment
            fragmentManager.popBackStackImmediate();
        } else {
            // If there are no fragments in the back stack or the current fragment is not AddUserFragment, handle back press as usual
            super.onBackPressed();
            finishAffinity();
        }
    }
}
