package com.project.emergencyaircraft;

public class NotificationItem {
    private String emergencyType;
    private String moreInfo;

    public NotificationItem(String emergencyType, String moreInfo) {
        this.emergencyType = emergencyType;
        this.moreInfo = moreInfo;
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public String getMoreInfo() {
        return moreInfo;
    }
}
