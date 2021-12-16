package com.kelompok1.ojomager.activities

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.kelompok1.ojomager.broadcasts.ReminderReceiver
import com.kelompok1.ojomager.databinding.ActivityReminderBinding
import java.util.*


class ReminderActivity : AppCompatActivity() {
    private var binding: ActivityReminderBinding? = null
    private var picker: MaterialTimePicker? = null
    private var calendar: Calendar? = null
    private var alarmManager: AlarmManager? = null
    private var pendingIntent: PendingIntent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReminderBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        supportActionBar?.hide()

        createNotificationChannel()
        binding!!.selectTimeBtn.setOnClickListener { showTimePicker() }
        binding!!.setAlarmBtn.setOnClickListener { setAlarm() }
        binding!!.cancelAlarmBtn.setOnClickListener { cancelAlarm() }
    }

    private fun cancelAlarm() {
        val intent = Intent(this, ReminderReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        if (alarmManager == null) {
            alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        }
        alarmManager!!.cancel(pendingIntent)
        Toast.makeText(this, "Reminder Cancelled", Toast.LENGTH_SHORT).show()
    }

    private fun setAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ReminderReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager!!.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar!!.timeInMillis,
            AlarmManager.INTERVAL_DAY, pendingIntent
        )
        Toast.makeText(this, "Reminder set Successfully", Toast.LENGTH_SHORT).show()
    }

    private fun showTimePicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Reminder Time")
            .build()
        picker!!.show(supportFragmentManager, "ojomagerapp")
        picker!!.addOnPositiveButtonClickListener {
            if (picker!!.hour > 12) {
                binding?.selectedTime?.text = String.format(
                    "%02d",
                    picker!!.hour - 12
                ) + " : " + String.format("%02d", picker!!.minute) + " PM"
            } else {
                binding?.selectedTime?.text = picker!!.hour.toString() + " : " + picker!!.minute + " AM"
            }
            calendar = Calendar.getInstance()
            with(calendar, {
                this?.set(Calendar.HOUR_OF_DAY, picker!!.hour)
                this?.set(Calendar.MINUTE, picker!!.minute)
                this?.set(Calendar.SECOND, 0)
                this?.set(Calendar.MILLISECOND, 0)
            })
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "ojoMagerAppReminderChannel"
            val description = "Channel For Reminder Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("ojomager", name, importance)
            channel.description = description
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}