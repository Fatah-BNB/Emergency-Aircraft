package com.project.emergencyaircraft;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

        notificationList = new ArrayList<>();

        notificationsRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                for (DataSnapshot child : snapshot.getChildren()) {
                    NotificationItem notificationItem = child.getValue(NotificationItem.class);
                    notificationList.add(notificationItem);
                }

                // After fetching the data, set the RecyclerView adapter
                if (!notificationList.isEmpty()) {
                    setRecyclerViewAdapter();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_notifications, container, false);
        return view;
    }

    private void setRecyclerViewAdapter() {
        View view = getView();
        if (view != null) {
            RecyclerView recyclerView = view.findViewById(R.id.notificationRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

            NotificationAdapter adapter = new NotificationAdapter(notificationList, new NotificationAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(NotificationItem clickedItem) {
                    showDialog(clickedItem.getEmergencyType(), clickedItem.getDamages() + "\n" + clickedItem.getOther());
                }
            });

            recyclerView.setAdapter(adapter);
        }
    }

    public void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
