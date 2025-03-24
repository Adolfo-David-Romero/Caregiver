package sheridan.romeroad.caregiver.domain

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import sheridan.romeroad.caregiver.data.MedicationReminder
import sheridan.romeroad.caregiver.services.MedicationAlarmReceiver
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Student ID: 991555778
 * Caregiver
 * created by davidromero
 * on 2025-03-24
 **/
@SuppressLint("MissingPermission", "ScheduleExactAlarm")
fun scheduleMedicationAlarm(context: Context, reminder: MedicationReminder) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val intent = Intent(context, MedicationAlarmReceiver::class.java).apply {
        putExtra("name", reminder.name)
        putExtra("time", reminder.time)
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        reminder.id.hashCode(), // Unique ID for each reminder
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val reminderTime = parseMedicationTime(reminder.time)

    if (reminderTime != null) {
        val calendar = Calendar.getInstance().apply {
            time = reminderTime
            if (before(Calendar.getInstance())) {
                add(Calendar.DATE, 1) // Schedule for the next day if the time is in the past
            }
        }

        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    } else {
        Log.e("ScheduleMedicationAlarm", "Failed to parse reminder time: ${reminder.time}")
    }
}
fun parseMedicationTime(timeString: String): Date? {
    return try {
        val timeFormat = SimpleDateFormat("h:mma", Locale.getDefault()) // Matches "2:29PM"
        timeFormat.parse(timeString)
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}
