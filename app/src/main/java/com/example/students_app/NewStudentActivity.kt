import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class NewStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        val editTextName = findViewById<TextInputEditText>(R.id.editTextNewStudentName)
        val addButton = findViewById<Button>(R.id.buttonAddStudent)

        addButton.setOnClickListener {
            val name = editTextName.text.toString().trim()
            if (name.isNotEmpty()) {
                StudentRepository.addStudent(name)
                finish() // Go back to the student list
            } else {
                editTextName.error = "Student name cannot be empty"
            }
        }
    }
}