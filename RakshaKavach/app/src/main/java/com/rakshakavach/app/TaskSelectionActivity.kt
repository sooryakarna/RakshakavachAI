package com.rakshakavach.app

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class TaskSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_selection)

        val workerName = intent.getStringExtra("WORKER_NAME") ?: "Worker"
        val workerId = intent.getStringExtra("WORKER_ID") ?: ""

        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        tvGreeting.text = "Hello, $workerName  |  ID: $workerId"

        findViewById<CardView>(R.id.cardQuiz).setOnClickListener {
            startActivity(Intent(this, DailySafetyQuizActivity::class.java))
        }

        findViewById<CardView>(R.id.cardIncident).setOnClickListener {
            startActivity(Intent(this, IncidentReportActivity::class.java))
        }

        findViewById<CardView>(R.id.cardPPE).setOnClickListener {
            startActivity(Intent(this, PPEChecklistActivity::class.java))
        }

        findViewById<CardView>(R.id.cardRiskAlert).setOnClickListener {
            startActivity(Intent(this, RiskAlertActivity::class.java))
        }
    }
}
