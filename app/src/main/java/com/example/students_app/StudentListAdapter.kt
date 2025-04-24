import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentListAdapter(
    private var students: List<Student>,
    private val onItemClicked: (Student) -> Unit,
    private val onCheckChanged: (Student) -> Unit
) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewStudentPic: ImageView = itemView.findViewById(R.id.imageViewStudentPic)
        val textViewStudentName: TextView = itemView.findViewById(R.id.textViewStudentName)
        val textViewStudentId: TextView = itemView.findViewById(R.id.textViewStudentId)
        val checkBoxChecked: CheckBox = itemView.findViewById(R.id.checkBoxChecked)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClicked(students[position])
                }
            }

            checkBoxChecked.setOnCheckedChangeListener { _, isChecked ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val student = students[position]
                    if (student.isChecked != isChecked) {
                        onCheckChanged(student.copy(isChecked = isChecked))
                    }
                }
            }
        }

        fun bind(student: Student) {
            // Load static image (you'll need to add ic_student.png to your drawable folder)
            imageViewStudentPic.setImageResource(R.drawable.ic_student)
            textViewStudentName.text = student.name
            textViewStudentId.text = "ID: ${student.id}"
            checkBoxChecked.isChecked = student.isChecked
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount() = students.size

    fun updateList(newList: List<Student>) {
        students = newList
        notifyDataSetChanged()
    }
}