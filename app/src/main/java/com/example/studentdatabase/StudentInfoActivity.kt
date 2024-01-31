package com.example.studentdatabase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class StudentInfoActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_info)

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().reference.child("students")

        val editTextName: EditText = findViewById(R.id.editTextName)
        val editTextFatherName: EditText = findViewById(R.id.editTextFatherName)
        val editTextAddress: EditText = findViewById(R.id.editTextAddress)
        val editTextGender: EditText = findViewById(R.id.editTextGender)
        val editTextContactNo: EditText = findViewById(R.id.editTextContactNo)
        val editTextNICNo: EditText = findViewById(R.id.editTextNICNo)

        val buttonSubmit: Button = findViewById(R.id.buttonSubmit)
        val buttonReset: Button = findViewById(R.id.buttonReset)

        buttonSubmit.setOnClickListener {
            // Save data to Firebase Realtime Database
            saveData(
                editTextName.text.toString(),
                editTextFatherName.text.toString(),
                editTextAddress.text.toString(),
                editTextGender.text.toString(),
                editTextContactNo.text.toString(),
                editTextNICNo.text.toString()
            )
            showToast("Data Saved")

            editTextName.text.clear()
            editTextFatherName.text.clear()
            editTextAddress.text.clear()
            editTextGender.text.clear()
            editTextContactNo.text.clear()
            editTextNICNo.text.clear()
        }

        buttonReset.setOnClickListener {
            // Clear input fields
            editTextName.text.clear()
            editTextFatherName.text.clear()
            editTextAddress.text.clear()
            editTextGender.text.clear()
            editTextContactNo.text.clear()
            editTextNICNo.text.clear()
        }
    }

    private fun saveData(
        name: String,
        fatherName: String,
        address: String,
        gender: String,
        contactNo: String,
        nicNo: String
    ) {
        val studentId = databaseReference.push().key // Generate a unique key for each student

        // Create a Student object
        val student = Student(name, fatherName, address, gender, contactNo, nicNo)

        // Save the student data to Firebase Realtime Database
        studentId?.let {
            databaseReference.child(it).setValue(student)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
