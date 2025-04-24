import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentListAdapter
    private lateinit var fabAddStudent: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        recyclerView = findViewById(R.id.recyclerViewStudents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentListAdapter(
            StudentRepository.students,
            { student -> // On item click
                val intent = Intent(this, StudentDetailsActivity::class.java)
                intent.putExtra("studentId", student.id)
                startActivity(intent)
            },
            { updatedStudent -> // On checkbox change
                StudentRepository.updateStudent(updatedStudent)
                adapter.updateList(StudentRepository.students) // Refresh the list
            }
        )
        recyclerView.adapter = adapter

        fabAddStudent = findViewById(R.id.fabAddStudent)
        fabAddStudent.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.updateList(StudentRepository.students) // Refresh the list when returning to this screen
    }
}