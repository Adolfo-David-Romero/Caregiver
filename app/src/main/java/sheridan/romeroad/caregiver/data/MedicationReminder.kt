package sheridan.romeroad.caregiver.data

/**
 * Student ID: 991555778
 * Caregiver
 * created by davidromero
 * on 2025-03-24
 **/
data class MedicationReminder(
    val id: String = "",
    val name: String = "",
    val time: String = "",
    val dosage: String = "",
    val isTaken: Boolean = false
)
