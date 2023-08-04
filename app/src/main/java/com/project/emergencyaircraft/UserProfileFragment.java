package com.project.emergencyaircraft;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class UserProfileFragment extends Fragment {

    public UserProfileFragment(User user) {
        this.user=user;
    }
    public User user;
    SharedPreferences prefs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        prefs = getActivity().getSharedPreferences("myApp", Context.MODE_PRIVATE);
        // Example user data
        String userName = user.getUsername();
        String role = user.getRole();

        TextView userNameTextView = view.findViewById(R.id.userNameTextView);
        TextView roleTextView = view.findViewById(R.id.roleTextView);

        userNameTextView.setText("User: " + userName);
        roleTextView.setText("Role: " + role);
        Button logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("username", null);
            editor.apply();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });
        return view;
    }

}
