package sheridan.romeroad.caregiver.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import sheridan.romeroad.caregiver.data.MedicationReminder
import sheridan.romeroad.caregiver.domain.scheduleMedicationAlarm

/**
 * Student ID: 991555778
 * Caregiver
 * created by davidromero
 * on 2025-03-24
 **/
class MedicationViewModel : ViewModel() {
    val db = FirebaseFirestore.getInstance()
    val _medications = MutableStateFlow<List<MedicationReminder>>(emptyList())
    val medications: StateFlow<List<MedicationReminder>> = _medications

    private var listenerRegistration: ListenerRegistration? = null

    fun fetchMedications() {
        listenerRegistration?.remove() // Remove previous listener
        listenerRegistration = db.collection("medications")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.e("MedicationViewModel", "Error fetching medications: ${error.message}")
                    _medications.value = emptyList()
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val fetchedMedications = snapshot.documents.mapNotNull { document ->
                        document.toObject(MedicationReminder::class.java)
                    }
                    _medications.value = fetchedMedications
                }
            }
    }

    fun scheduleAlarms(context: Context) {
        medications.value.forEach { reminder ->
            try {
                scheduleMedicationAlarm(context, reminder)
            } catch (e: Exception) {
                Log.e("MedicationViewModel", "Failed to schedule alarm for ${reminder.name}: ${e.message}")
            }
        }
    }
    fun formatMedicationsForGemini(): String {
        return medications.value.joinToString("\n") { reminder ->
            "Name: ${reminder.name}, Time: ${reminder.time}, Dosage: ${reminder.dosage}, Taken: ${if (reminder.isTaken) "Yes" else "No"}"
        }
    }

    override fun onCleared() {
        super.onCleared()
        listenerRegistration?.remove() // Clean up Firestore listener
    }
}