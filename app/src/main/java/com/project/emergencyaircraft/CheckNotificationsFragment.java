package com.project.emergencyaircraft;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckNotificationsFragment extends Fragment {

    private List<NotificationItem> notificationList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference notificationsRef = database.getReference("notifications");

    public CheckNotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the list of notifications (replace this with your actual data)
        notificationList = new ArrayList<>();
        notificationsRef.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                for(DataSnapshot child : snapshot.getChildren()) {
                    NotificationItem notificationItem = child.getValue(NotificationItem.class);
                    notificationList.add(notificationItem);
                }
            }
        });
        // Add more notifications if needed
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_notifications, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.notificationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        NotificationAdapter adapter = new NotificationAdapter(notificationList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
