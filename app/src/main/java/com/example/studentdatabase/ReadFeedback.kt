package com.example.studentdatabase

import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ReadFeedback : AppCompatActivity() {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("feedbacks")
    private lateinit var tableLayout: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_feedback)

        tableLayout = findViewById(R.id.tableLayoutFeedback)

        readFeedbacks()
    }

    private fun readFeedbacks() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Clear existing rows
                tableLayout.removeAllViews()

                // Iterate through feedbacks and add rows to the table
                for (feedbackSnapshot in dataSnapshot.children) {
                    val feedback = feedbackSnapshot.getValue(Feedback::class.java)
                    if (feedback != null) {
                        addFeedbackRow(feedback)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })
    }

    private fun addFeedbackRow(feedback: Feedback) {
        val row = TableRow(this)

        // Create TextView for Name
        val nameTextView = TextView(this)
        nameTextView.text = feedback.name
        nameTextView.gravity = Gravity.START
        nameTextView.textSize = 18f
        nameTextView.setTypeface(null, android.graphics.Typeface.BOLD)
        nameTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)

        // Create TextView for Message
        val messageTextView = TextView(this)
        messageTextView.text = feedback.message
        messageTextView.gravity = Gravity.START
        messageTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2f)

        // Add padding and margins
        val padding = resources.getDimensionPixelSize(R.dimen.feedback_table_padding)
        val margin = resources.getDimensionPixelSize(R.dimen.feedback_table_margin)
        nameTextView.setPadding(padding, padding, padding, padding)
        messageTextView.setPadding(padding, padding, padding, padding)

        // Add TextViews to TableRow
        row.addView(nameTextView)
        row.addView(messageTextView)

        // Add TableRow to TableLayout
        tableLayout.addView(row)
    }

}
