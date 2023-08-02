package com.project.emergencyaircraft;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class UserProfileFragment extends Fragment {

    public UserProfileFragment(User user) {
        // Required empty public constructor
        this.user=user;
    }
    public User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        // Example user data
        String userName = user.getUsername();
        String role = user.getRole();

        TextView userNameTextView = view.findViewById(R.id.userNameTextView);
        TextView roleTextView = view.findViewById(R.id.roleTextView);

        userNameTextView.setText("User: " + userName);
        roleTextView.setText("Role: " + role);

        return view;
    }
}
