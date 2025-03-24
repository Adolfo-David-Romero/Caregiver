package sheridan.romeroad.caregiver.data

/**
 * Student ID: 991555778
 * Caregiver
 * created by davidromero
 * on 2025-03-24
 **/
data class PatientStatus(
    val healthStatus: String = "", // e.g., "Diabetes"
    //val medications: List<MedicationReminder> = emptyList(),
    val avgBloodOxygen: Int = 0, // Percentage
    val avgHeartRate: Int = 0, // Beats per minute
    val avgStepsPerDay: Int = 0,
    val emergencyContact: String = "", // Contact info
    val nurseNotes: String = "" // Notes from the nurse
)