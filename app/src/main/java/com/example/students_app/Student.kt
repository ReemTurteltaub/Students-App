data class Student(
    var id: Int,
    var name: String,
    var phone: String = "", // Add phone
    var address: String = "", // Add address
    var isChecked: Boolean = false
)