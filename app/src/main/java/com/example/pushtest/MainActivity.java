package com.example.pushtest;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.example.pushtest.App.CHANNEL_1_ID;
import static com.example.pushtest.App.CHANNEL_2_ID;


public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;
    private EditText editTextTitle;
    private EditText editTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_message);

    }

    public void sendOnChannel1(View v){
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReciver.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.head);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_cloud_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.long_dummy_text))
                        .setBigContentTitle("Big Content Title")
                        .setSummaryText("Summary Text")
                )
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setColor(Color.BLUE)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();
        notificationManagerCompat.notify(1, notification);

    }

    public void sendOnChannel2(View v){
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_cloud_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("This is Line 1")
                        .addLine("This is Line 2")
                        .addLine("This is Line 3")
                        .addLine("This is Line 4")
                        .addLine("This is Line 5")
                        .addLine("This is Line 6")
                        .setBigContentTitle("Big Content Title")
                        .setSummaryText("Summary Text")
                )
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
        notificationManagerCompat.notify(2, notification);

    }

}
