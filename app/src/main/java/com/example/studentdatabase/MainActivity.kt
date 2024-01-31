package com.example.studentdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        // Initialize buttons
        val studentInfoButton: Button = findViewById(R.id.studentInfoButton)
        val administratorInfoButton: Button = findViewById(R.id.administratorInfoButton)
        val contactUsButton: Button = findViewById(R.id.contactUsButton)

        // Set onClickListener for each button
        studentInfoButton.setOnClickListener {
            val intent = Intent(this, StudentSubtabActivity::class.java)
            startActivity(intent)
        }

        administratorInfoButton.setOnClickListener {
            val intent = Intent(this, AdministratorInfoActivity::class.java)
            startActivity(intent)
        }

        contactUsButton.setOnClickListener {
            val intent = Intent(this, ContactUsActivity::class.java)
            startActivity(intent)
        }
    }
}
