package sheridan.romeroad.caregiver.ui.medication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sheridan.romeroad.caregiver.data.MedicationReminder

/**
 * Student ID: 991555778
 * Caregiver
 * created by davidromero
 * on 2025-03-24
 **/
@Composable
fun MedicationReminderItem(reminder: MedicationReminder) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = reminder.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = "Time: ${reminder.time}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Dosage: ${reminder.dosage}", style = MaterialTheme.typography.bodyMedium)
            }
            Checkbox(
                checked = reminder.isTaken,
                onCheckedChange = { /* Handle checkbox state */ }
            )
        }
    }
}