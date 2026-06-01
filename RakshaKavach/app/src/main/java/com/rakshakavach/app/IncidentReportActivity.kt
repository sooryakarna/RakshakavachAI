package com.rakshakavach.app

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class IncidentReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incident_report)

        val etLocation    = findViewById<EditText>(R.id.etLocation)
        val etDate        = findViewById<EditText>(R.id.etDate)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val etInjured     = findViewById<EditText>(R.id.etInjured)
        val spinnerType   = findViewById<Spinner>(R.id.spinnerIncidentType)
        val btnSubmit     = findViewById<Button>(R.id.btnSubmitReport)

        val types = listOf("Select Type", "Near Miss", "Minor Injury", "Major Injury", "Property Damage", "Fire", "Chemical Spill", "Other")
        spinnerType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)

        btnSubmit.setOnClickListener {
            val location    = etLocation.text.toString().trim()
            val date        = etDate.text.toString().trim()
            val description = etDescription.text.toString().trim()
            val type        = spinnerType.selectedItem.toString()

            if (location.isEmpty()) { etLocation.error = "Enter location"; return@setOnClickListener }
            if (date.isEmpty())     { etDate.error = "Enter date"; return@setOnClickListener }
            if (description.isEmpty()) { etDescription.error = "Describe the incident"; return@setOnClickListener }
            if (type == "Select Type") { Toast.makeText(this, "Please select incident type", Toast.LENGTH_SHORT).show(); return@setOnClickListener }

            Toast.makeText(this, "✅ Incident report submitted successfully.", Toast.LENGTH_LONG).show()
            clearFields(etLocation, etDate, etDescription, etInjured)
            spinnerType.setSelection(0)
        }
    }

    private fun clearFields(vararg fields: EditText) = fields.forEach { it.text.clear() }
}
