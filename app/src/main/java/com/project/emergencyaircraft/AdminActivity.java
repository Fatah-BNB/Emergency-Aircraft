package com.project.emergencyaircraft;// AdminActivity.java

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminActivity extends AppCompatActivity {
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        prefs = getSharedPreferences("myApp", Context.MODE_PRIVATE);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.action_check_notifications); // Set "Check Notification List" as the default selected item

        // Set the default fragment to NotificationsFragment when the AdminActivity is created
        displayNotificationsFragment();
        Button logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("username", null);
            editor.apply();
            startActivity(new Intent(this, LoginActivity.class));
        });
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

    public void displayUsersFragment() {
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
