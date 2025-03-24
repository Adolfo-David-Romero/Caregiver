package sheridan.romeroad.caregiver.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Student ID: 991555778
 * Caregiver
 * created by davidromero
 * on 2025-03-24
 **/
class MedicationAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val name = intent?.getStringExtra("name")
        val time = intent?.getStringExtra("time")

        Toast.makeText(context, "Time to take your medication: $name at $time", Toast.LENGTH_LONG).show()

    }
}
