object StudentRepository {
    private val _students = mutableListOf<Student>()
    val students: List<Student> = _students

    private var nextId = 1 // Simple auto-increment for IDs

    fun addStudent(name: String) {
        val newStudent = Student(nextId++, name)
        _students.add(newStudent)
    }

    fun getStudentById(id: Int): Student? {
        return _students.find { it.id == id }
    }

    fun updateStudent(student: Student) {
        val index = _students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            _students[index] = student
        }
    }

    fun deleteStudent(id: Int) {
        _students.removeAll { it.id == id }
    }

    fun toggleCheck(id: Int) {
        getStudentById(id)?.let { it.isChecked = !it.isChecked }
    }
}