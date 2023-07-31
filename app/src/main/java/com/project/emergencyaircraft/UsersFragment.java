package com.project.emergencyaircraft;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.emergencyaircraft.User;
import com.project.emergencyaircraft.UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class UsersFragment extends Fragment {

    private List<User> userList;
    private UserAdapter userAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        // Initialize the user list (you can load it from a database or any other source)
        userList = new ArrayList<>();
        userList.add(new User("User1"));
        userList.add(new User("User2"));
        userList.add(new User("User3"));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        userAdapter = new UserAdapter(userList, this); // Pass reference to UsersFragment
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(userAdapter);

        // Add User Button
        Button addUserButton = view.findViewById(R.id.addUserButton);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddUserFragment();
            }
        });

        return view;
    }

    private void showAddUserFragment() {
        AddUserFragment addUserFragment = new AddUserFragment();
        addUserFragment.setOnUserCreatedListener(new AddUserFragment.OnUserCreatedListener() {
            @Override
            public void onUserCreated(String username, String password) {
                // Handle the user creation logic here
                // For demonstration, we'll just show a toast message with the new user's details
                String message = "New User Created:\nUsername: " + username + "\nPassword: " + password;
                userList.add(new User(username)); // Add the new user to the list
                userAdapter.notifyDataSetChanged(); // Notify the adapter of the data change
                // Optionally, you can also save the new user to a database or perform any other required actions.
            }
        });
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, addUserFragment)
                .addToBackStack(null)
                .commit();
    }

    private void addUser(String username) {
        // Add the user to the list
        userList.add(new User(username));

        // Notify the adapter that the data set has changed
        userAdapter.notifyDataSetChanged();
    }

    public void deleteUser(int position) {
        // Remove the user from the list
        userList.remove(position);

        // Notify the adapter that the data set has changed
        userAdapter.notifyDataSetChanged();

        Toast.makeText(requireContext(), "User deleted.", Toast.LENGTH_SHORT).show();
    }
}
