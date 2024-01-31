package com.example.studentdatabase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class ContactUsActivity : AppCompatActivity() {

    private val databaseReference = FirebaseDatabase.getInstance().reference.child("feedbacks")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val feedbackEditText: EditText = findViewById(R.id.feedbackEditText)
        val submitButton: Button = findViewById(R.id.submitButton)
        val returnButton: Button = findViewById(R.id.returnButton)

        submitButton.setOnClickListener {
            // Get the name and feedback text, and clear the EditTexts
            val name = nameEditText.text.toString()
            val feedbackText = feedbackEditText.text.toString()
            nameEditText.text.clear()
            feedbackEditText.text.clear()

            // Save feedback to Firebase Realtime Database
            saveFeedback(name, feedbackText)

            // Show a toast message indicating feedback sent successfully
            showToast("Feedback sent successfully")
        }

        returnButton.setOnClickListener {
            // Finish the current activity to go back to the previous activity (Main Menu)
            finish()
        }
    }

    private fun saveFeedback(name: String, message: String) {
        // Generate a unique key for each feedback entry
        val feedbackId = databaseReference.push().key ?: ""

        // Create a Feedback object
        val feedback = Feedback(name, message)

        // Save the feedback to the database under the unique key
        databaseReference.child(feedbackId).setValue(feedback)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
