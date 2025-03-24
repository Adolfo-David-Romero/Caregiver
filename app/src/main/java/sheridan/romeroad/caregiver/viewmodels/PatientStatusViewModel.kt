package sheridan.romeroad.caregiver.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import sheridan.romeroad.caregiver.data.MedicationReminder
import sheridan.romeroad.caregiver.data.PatientStatus

/**
 * Student ID: 991555778
 * Caregiver
 * created by davidromero
 * on 2025-03-24
 **/
class PatientStatusViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _patientStatus = MutableStateFlow<PatientStatus?>(null)
    val patientStatus: StateFlow<PatientStatus?> = _patientStatus

    fun fetchPatientStatus(patientId: String) {
        viewModelScope.launch {
            try {
                // Fetch Patient Info
                val patientDoc = db.collection("patients").document(patientId).get().await()
                val healthStatus = patientDoc.getString("healthStatus") ?: "Unknown"
                val avgBloodOxygen = patientDoc.getLong("avgBloodOxygen")?.toInt() ?: 0
                val avgHeartRate = patientDoc.getLong("avgHeartRate")?.toInt() ?: 0
                val avgStepsPerDay = patientDoc.getLong("avgStepsPerDay")?.toInt() ?: 0
                val emergencyContact = patientDoc.getString("emergencyContact") ?: "No contact info"
                val nurseNotes = patientDoc.getString("nurseNotes") ?: "No notes"

                val medications = db.collection("medications")
                    .whereEqualTo("patientId", patientId)
                    .get()
                    .await()
                    .documents.mapNotNull { it.toObject(MedicationReminder::class.java) }

                // Populate PatientStatus object
                val fetchedStatus = PatientStatus(
                    healthStatus = healthStatus,
                    //medications = medications,
                    avgBloodOxygen = avgBloodOxygen,
                    avgHeartRate = avgHeartRate,
                    avgStepsPerDay = avgStepsPerDay,
                    emergencyContact = emergencyContact,
                    nurseNotes = nurseNotes
                )

                _patientStatus.value = fetchedStatus
            } catch (e: Exception) {
                _patientStatus.value = null
            }
        }
    }
}
