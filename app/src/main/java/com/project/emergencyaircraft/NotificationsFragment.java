package com.project.emergencyaircraft;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private List<NotificationItem> notificationList;
    private NotificationAdapter notificationAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference notificationsRef = database.getReference("notifications");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        // Initialize the notification list (you can load it from a database or any other source)
        notificationList = new ArrayList<>();
        notificationsRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                for (DataSnapshot child : snapshot.getChildren()) {
                    NotificationItem notificationItem = child.getValue(NotificationItem.class);
                    notificationList.add(notificationItem);
                }
                RecyclerView recyclerView = view.findViewById(R.id.notificationRecyclerView);
                notificationAdapter = new NotificationAdapter(notificationList);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                recyclerView.setAdapter(notificationAdapter);
            }
        });

        return view;
    }

    private class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

        private final List<NotificationItem> notifications;

        public NotificationAdapter(List<NotificationItem> notifications) {
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
            String notificationTitle = notifications.get(position).getEmergencyType() + "\n" +notifications.get(position).getDate() + " : " + notifications.get(position).getTime();
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

                deleteButton.setOnClickListener(v -> {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        NotificationItem notificationItem = notifications.get(position);

                        // Remove the item from Firebase
                        notificationsRef.child(notificationItem.id).removeValue()
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        new Handler(Looper.getMainLooper()).post(() -> {
                                            notifications.remove(position);
                                            notifyItemRemoved(position);
                                        });
                                    }
                                });
                    }
                });
            }
        }
    }
}
