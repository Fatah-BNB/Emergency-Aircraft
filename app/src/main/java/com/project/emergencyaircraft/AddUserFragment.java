package com.project.emergencyaircraft;

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

public class AddUserFragment extends Fragment {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button createUserButton;

    public interface OnUserCreatedListener {
        void onUserCreated(String username, String password);
    }

    private OnUserCreatedListener userCreatedListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        usernameEditText = view.findViewById(R.id.usernameEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        createUserButton = view.findViewById(R.id.createUserButton);

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (!username.isEmpty() && !password.isEmpty() && userCreatedListener != null) {
                    userCreatedListener.onUserCreated(username, password);
                }else{
                    Toast.makeText(getContext(), "some inputs are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void setOnUserCreatedListener(OnUserCreatedListener listener) {
        this.userCreatedListener = listener;
    }
}
