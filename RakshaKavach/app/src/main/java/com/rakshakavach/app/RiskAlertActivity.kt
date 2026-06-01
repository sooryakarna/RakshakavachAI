package com.rakshakavach.app

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

data class RiskAlert(val title: String, val description: String, val severity: String)

class RiskAlertActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_risk_alert)

        val alerts = listOf(
            RiskAlert("Slippery Floor", "Wet floor detected near Gate 3 washroom area. Use alternate route.", "HIGH"),
            RiskAlert("High Noise Zone", "Ear protection mandatory in the fabrication bay (Zone B).", "MEDIUM"),
            RiskAlert("Chemical Handling", "Caustic soda being used in Lab 2. Wear goggles and gloves.", "HIGH"),
            RiskAlert("Crane Operations", "Overhead crane operational in Bay 4. Do not walk under loads.", "CRITICAL"),
            RiskAlert("Fire Drill", "Scheduled fire drill on Friday at 3:00 PM. Be aware of evacuation routes.", "INFO")
        )

        val container = findViewById<android.widget.LinearLayout>(R.id.alertContainer)

        alerts.forEach { alert ->
            val card = layoutInflater.inflate(R.layout.item_risk_alert, container, false)
            card.findViewById<TextView>(R.id.tvAlertTitle).text = alert.title
            card.findViewById<TextView>(R.id.tvAlertDesc).text = alert.description
            val tvSeverity = card.findViewById<TextView>(R.id.tvSeverity)
            tvSeverity.text = alert.severity
            tvSeverity.setBackgroundColor(
                when (alert.severity) {
                    "CRITICAL" -> getColor(R.color.severity_critical)
                    "HIGH"     -> getColor(R.color.severity_high)
                    "MEDIUM"   -> getColor(R.color.severity_medium)
                    else       -> getColor(R.color.severity_info)
                }
            )
            container.addView(card)
        }
    }
}
