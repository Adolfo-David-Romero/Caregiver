package sheridan.romeroad.caregiver.ui.medication

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import sheridan.romeroad.caregiver.viewmodels.MedicationViewModel

/**
 * Student ID: 991555778
 * Caregiver
 * created by davidromero
 * on 2025-03-24
 **/
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationRemindersScreen(
    viewModel: MedicationViewModel,
    context: Context = LocalContext.current
) {
    val medications by viewModel.medications.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMedications()
        viewModel.scheduleAlarms(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Medication Reminders") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Upcoming Medications",
                style = MaterialTheme.typography.headlineSmall
            )

            if (medications.isEmpty()) {
                Text("No medication reminders available.", style = MaterialTheme.typography.bodyMedium)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(medications) { reminder ->
                        MedicationReminderItem(reminder)
                    }
                }
            }
        }
    }
}