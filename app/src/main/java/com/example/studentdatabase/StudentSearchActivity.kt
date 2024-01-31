package com.example.studentdatabase

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class StudentSearchActivity : AppCompatActivity() {

    private lateinit var editTextSearch: EditText
    private lateinit var buttonSearch: Button
    private lateinit var tableLayoutResults: TableLayout
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_search)

        editTextSearch = findViewById(R.id.editTextSearch)
        buttonSearch = findViewById(R.id.buttonSearch)
        tableLayoutResults = findViewById(R.id.tableLayoutResults)

        // Assuming "students" is the node in your Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().reference.child("students")

        buttonSearch.setOnClickListener {
            performSearch()
        }
    }

    private fun performSearch() {
        val searchQuery = editTextSearch.text.toString()
        val query = databaseReference.orderByChild("name").startAt(searchQuery).endAt(searchQuery + "\uf8ff")

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val matchingStudents = mutableListOf<Student>()
                for (snapshot in dataSnapshot.children) {
                    val student = snapshot.getValue(Student::class.java)
                    student?.let { matchingStudents.add(it) }
                }
                displayResults(matchingStudents)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors here
                Toast.makeText(this@StudentSearchActivity, "Error in database", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun displayResults(matchingStudents: List<Student>) {
        // Clear previous results
        tableLayoutResults.removeAllViews()

        // Create a single column for all values
        val column = LinearLayout(this)
        column.orientation = LinearLayout.VERTICAL

        // Display results in the column
        for (student in matchingStudents) {
            val nameTextView = createTextView(this, "Name: ${student.name}")
            val fatherNameTextView = createTextView(this, "Father Name: ${student.fatherName}")
            val addressTextView = createTextView(this, "Address: ${student.address}")
            val genderTextView = createTextView(this, "Gender: ${student.gender}")
            val contactNoTextView = createTextView(this, "Contact No: ${student.contactNo}")
            val nicNoTextView = createTextView(this, "NIC No: ${student.nicNo}")

            // Add each TextView to the column
            column.addView(nameTextView)
            column.addView(fatherNameTextView)
            column.addView(addressTextView)
            column.addView(genderTextView)
            column.addView(contactNoTextView)
            column.addView(nicNoTextView)

            // Add a separator between student entries (optional)
            val separator = View(this)
            separator.setBackgroundColor(Color.BLACK) // Set separator color
            separator.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                2 // Set separator height
            )
            column.addView(separator)
        }

        // Add the column to the table
        val row = TableRow(this)
        row.addView(column)
        tableLayoutResults.addView(row)

        // Show a Toast message for debugging purposes
        if (matchingStudents.isEmpty()) {
            Toast.makeText(this, "No matching students found", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "${matchingStudents.size} students found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createTextView(context: Context, text: String): TextView {
        val textView = TextView(context)
        textView.text = text
        textView.textSize = 16f // Adjust text size as needed
        textView.setPadding(0, 5, 0, 5) // Add padding for better spacing
        return textView
    }
}
