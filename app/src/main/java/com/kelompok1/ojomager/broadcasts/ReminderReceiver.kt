package com.kelompok1.ojomager.broadcasts

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.kelompok1.ojomager.R
import com.kelompok1.ojomager.activities.MainActivity

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val i = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, "foxandroid")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Ojo Mager")
            .setContentText("Yuk jangan lupa olahraga hari ini!")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(123, builder.build())
    }
}