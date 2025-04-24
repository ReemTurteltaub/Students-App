import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var textViewName: TextView
    private lateinit var textViewId: TextView
    private lateinit var buttonEdit: Button
    private var studentId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        textViewName = findViewById(R.id.textViewDetailsName)
        textViewId = findViewById(R.id.textViewDetailsId)
        buttonEdit = findViewById(R.id.buttonEditStudent)

        studentId = intent.getIntExtra("studentId", -1)

        val student = StudentRepository.getStudentById(studentId)

        student?.let {
            textViewName.text = it.name
            textViewId.text = "ID: ${it.id}"
        }

        buttonEdit.setOnClickListener {
            if (student != null) {
                val intent = Intent(this, EditStudentActivity::class.java)
                intent.putExtra("studentId", student.id)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val student = StudentRepository.getStudentById(studentId)
        student?.let {
            textViewName.text = it.name
            textViewId.text = "ID: ${it.id}"
        }
    }
}