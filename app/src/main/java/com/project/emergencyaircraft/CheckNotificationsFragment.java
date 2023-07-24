package com.project.emergencyaircraft;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.emergencyaircraft.NotificationAdapter;
import com.project.emergencyaircraft.NotificationItem;

import java.util.ArrayList;
import java.util.List;

public class CheckNotificationsFragment extends Fragment {

    private List<NotificationItem> notificationList;

    public CheckNotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the list of notifications (replace this with your actual data)
        notificationList = new ArrayList<>();
        notificationList.add(new NotificationItem("Emergency 1", "More info about Emergency 1"));
        notificationList.add(new NotificationItem("Emergency 2", "More info about Emergency 2"));
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
