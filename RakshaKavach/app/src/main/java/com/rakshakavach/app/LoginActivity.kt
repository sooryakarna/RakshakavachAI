package com.rakshakavach.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etName = findViewById<EditText>(R.id.etName)
        val etWorkerId = findViewById<EditText>(R.id.etWorkerId)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val name = etName.text.toString().trim()
            val workerId = etWorkerId.text.toString().trim()

            if (name.isEmpty()) {
                etName.error = "Please enter your name"
                return@setOnClickListener
            }
            if (workerId.isEmpty()) {
                etWorkerId.error = "Please enter your Worker ID"
                return@setOnClickListener
            }

            Toast.makeText(this, "Welcome, $name!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, TaskSelectionActivity::class.java)
            intent.putExtra("WORKER_NAME", name)
            intent.putExtra("WORKER_ID", workerId)
            startActivity(intent)
            finish()
        }
    }
}
