package com.project.emergencyaircraft;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private List<NotificationItem> notificationList;

    public NotificationAdapter(List<NotificationItem> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_notification, parent, false);
        return new NotificationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationItem notification = notificationList.get(position);

        holder.emergencyTypeTextView.setText(notification.getNomExploitant());
        holder.moreInfoTextView.setText(notification.getEmergencyType());
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView emergencyTypeTextView;
        TextView moreInfoTextView;

        NotificationViewHolder(View itemView) {
            super(itemView);
            emergencyTypeTextView = itemView.findViewById(R.id.emergencyTypeTextView);
            moreInfoTextView = itemView.findViewById(R.id.moreInfoTextView);
        }
    }
}
