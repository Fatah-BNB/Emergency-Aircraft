package com.project.emergencyaircraft;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.emergencyaircraft.User;
import com.project.emergencyaircraft.UsersFragment;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private UsersFragment usersFragment; // Reference to UsersFragment

    public UserAdapter(List<User> userList, UsersFragment usersFragment) {
        this.userList = userList;
        this.usersFragment = usersFragment;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.usernameTextView.setText(user.getUsername());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView;
        Button deleteButton;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteButtonClick();
                }
            });
        }

        private void onDeleteButtonClick() {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                usersFragment.deleteUser(position); // Use the reference to UsersFragment
            }
        }
    }
}
