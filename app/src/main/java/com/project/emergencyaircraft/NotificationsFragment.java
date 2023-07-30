package com.project.emergencyaircraft;// NotificationsFragment.java
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private List<String> notificationList;
    private NotificationAdapter notificationAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        // Initialize the notification list (you can load it from a database or any other source)
        notificationList = new ArrayList<>();
        notificationList.add("Notification 1");
        notificationList.add("Notification 2");
        notificationList.add("Notification 3");

        RecyclerView recyclerView = view.findViewById(R.id.notificationRecyclerView);
        notificationAdapter = new NotificationAdapter(notificationList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(notificationAdapter);

        return view;
    }

    private class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

        private List<String> notifications;

        public NotificationAdapter(List<String> notifications) {
            this.notifications = notifications;
        }

        @NonNull
        @Override
        public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
            return new NotificationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
            String notificationTitle = notifications.get(position);
            holder.notificationTitle.setText(notificationTitle);
        }

        @Override
        public int getItemCount() {
            return notifications.size();
        }

        private class NotificationViewHolder extends RecyclerView.ViewHolder {
            Button deleteButton;
            TextView notificationTitle;

            public NotificationViewHolder(@NonNull View itemView) {
                super(itemView);
                deleteButton = itemView.findViewById(R.id.deleteButton);
                notificationTitle = itemView.findViewById(R.id.notificationTitle);

                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            notifications.remove(position);
                            notificationAdapter.notifyItemRemoved(position);
                        }
                    }
                });
            }
        }

    }
}
