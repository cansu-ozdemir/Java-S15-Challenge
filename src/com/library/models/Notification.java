package com.library.models;

import java.time.LocalDate;

public class Notification {
    private String message;
    private LocalDate notificationDate;

    public Notification(String message, LocalDate notificationDate) {
        this.message = message;
        this.notificationDate = notificationDate;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getNotificationDate() {
        return notificationDate;
    }

    @Override
    public String toString() {
        return "Notification: " + message + " (Date: " +notificationDate + ")";
    }
}
