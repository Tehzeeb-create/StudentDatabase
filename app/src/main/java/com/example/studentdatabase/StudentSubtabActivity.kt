package com.example.studentdatabase

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class StudentSubtabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_subtab)
    }

    // Called when the "Student Search" image is clicked
    fun onStudentSearchClick(view: View) {
        startActivity(Intent(this, StudentSearchActivity::class.java))
    }

    // Called when the "Student Entry" image is clicked
    fun onStudentEntryClick(view: View) {
        startActivity(Intent(this, StudentInfoActivity::class.java))
    }
}
