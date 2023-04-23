package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "MyChannelId"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNonClickableNotification = findViewById<Button>(R.id.btnNonClickableNotification)
        val btnClickableNotification = findViewById<Button>(R.id.btnClickableNotification)

        btnNonClickableNotification.setOnClickListener {
            showNonClickableNotification()
        }

        btnClickableNotification.setOnClickListener {
            showClickableNotification()
        }
    }

    private fun showNonClickableNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "My Channel", NotificationManager.IMPORTANCE_LOW)
            channel.description = "This is my channel description"
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 500, 1000)

            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Non-Clickable Notification")
            .setContentText("This is a non-clickable notification")

        val notification = builder.build()
        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL

        notificationManager.notify(1, notification)
    }

    private fun showClickableNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "My Channel", NotificationManager.IMPORTANCE_LOW)
            channel.description = "This is my channel description"
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 500, 1000)

            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Clickable Notification")
            .setContentText("This is a clickable notification")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notification = builder.build()

        notificationManager.notify(2, notification)
    }
}
