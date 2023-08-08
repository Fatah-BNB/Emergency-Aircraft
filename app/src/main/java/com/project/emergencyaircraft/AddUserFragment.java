package com.project.emergencyaircraft;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUserFragment extends Fragment {

    private EditText usernameEditText;
    private EditText emailEditText;

    private EditText passwordEditText;

    public interface OnUserCreatedListener {
        void onUserCreated(String username, String password);
    }

    private OnUserCreatedListener userCreatedListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        usernameEditText = view.findViewById(R.id.usernameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        Button createUserButton = view.findViewById(R.id.createUserButton);

        createUserButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (!username.isEmpty() && !password.isEmpty() && userCreatedListener != null) {
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setUsername(username);
                user.setRole("user");
                myRef.child(user.getUsername()).setValue(user);
                userCreatedListener.onUserCreated(username, password);
                startActivity(new Intent(getActivity(),AdminActivity.class));
                Toast.makeText(requireContext(), "User Created.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "some inputs are empty", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void setOnUserCreatedListener(OnUserCreatedListener listener) {
        this.userCreatedListener = listener;
    }
}
