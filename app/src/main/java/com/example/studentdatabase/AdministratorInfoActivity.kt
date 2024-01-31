package com.example.studentdatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdministratorInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrator_info)

        val adminButton: Button = findViewById(R.id.adminButton)
        val readfeedbackbutton: Button = findViewById(R.id.readfeedbackbutton)
        adminButton.setOnClickListener {
            // Go back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        readfeedbackbutton.setOnClickListener {
            // Go back to MainActivity
            val intent = Intent(this, ReadFeedback::class.java)
            startActivity(intent)
            finish()
        }

    }
}
