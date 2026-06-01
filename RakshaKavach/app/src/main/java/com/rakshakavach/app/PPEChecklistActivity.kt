package com.rakshakavach.app

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PPEChecklistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ppe_checklist)

        val cbHelmet    = findViewById<CheckBox>(R.id.cbHelmet)
        val cbGloves    = findViewById<CheckBox>(R.id.cbGloves)
        val cbShoes     = findViewById<CheckBox>(R.id.cbShoes)
        val cbGoggles   = findViewById<CheckBox>(R.id.cbGoggles)
        val cbMask      = findViewById<CheckBox>(R.id.cbMask)
        val cbVest      = findViewById<CheckBox>(R.id.cbVest)
        val cbHarness   = findViewById<CheckBox>(R.id.cbHarness)
        val btnSubmit   = findViewById<Button>(R.id.btnSubmitPPE)

        btnSubmit.setOnClickListener {
            val items = listOf(
                cbHelmet   to "Helmet",
                cbGloves   to "Gloves",
                cbShoes    to "Safety Shoes",
                cbGoggles  to "Goggles",
                cbMask     to "Mask",
                cbVest     to "Safety Vest",
                cbHarness  to "Safety Harness"
            )

            val missing = items.filter { !it.first.isChecked }.map { it.second }

            if (missing.isEmpty()) {
                Toast.makeText(this, "✅ All PPE items verified! Stay safe.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    this,
                    "⚠️ Missing: ${missing.joinToString(", ")}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
