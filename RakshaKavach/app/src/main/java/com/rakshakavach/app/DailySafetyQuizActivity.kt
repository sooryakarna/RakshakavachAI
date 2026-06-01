package com.rakshakavach.app

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

data class QuizQuestion(val question: String, val options: List<String>, val answerIndex: Int)

class DailySafetyQuizActivity : AppCompatActivity() {

    private val questions = listOf(
        QuizQuestion(
            "What does PPE stand for?",
            listOf("Personal Protective Equipment", "Plant Protection Equipment", "Primary Prevention Equipment", "Personal Preparedness Exercise"),
            0
        ),
        QuizQuestion(
            "Which colour of hard hat is typically worn by site supervisors?",
            listOf("White", "Yellow", "Blue", "Red"),
            0
        ),
        QuizQuestion(
            "What should you do immediately after a workplace incident?",
            listOf("Continue work", "Report it to the supervisor", "Ignore if minor", "Wait until end of shift"),
            1
        ),
        QuizQuestion(
            "Which of the following is NOT a PPE item?",
            listOf("Safety Gloves", "Helmet", "Mobile Phone", "Safety Shoes"),
            2
        ),
        QuizQuestion(
            "When working at height, which equipment is essential?",
            listOf("Gloves", "Safety Harness", "Goggles", "Earplugs"),
            1
        ),
        QuizQuestion(
            "Fire extinguisher colour code for CO2 type is?",
            listOf("Red", "Black", "Blue", "Green"),
            1
        ),
        QuizQuestion(
            "How often should safety toolbox talks ideally be conducted?",
            listOf("Monthly", "Yearly", "Weekly", "Never"),
            2
        ),
        QuizQuestion(
            "What is the first step in any emergency evacuation?",
            listOf("Call family", "Stay at workstation", "Proceed to nearest exit calmly", "Collect belongings"),
            2
        )
    )

    private var currentIndex = 0
    private var score = 0
    private var selectedOption = -1

    private lateinit var tvQuestion: TextView
    private lateinit var tvProgress: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnNext: Button
    private lateinit var tvScore: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_safety_quiz)

        tvQuestion  = findViewById(R.id.tvQuestion)
        tvProgress  = findViewById(R.id.tvProgress)
        radioGroup  = findViewById(R.id.radioGroupOptions)
        btnNext     = findViewById(R.id.btnNext)
        tvScore     = findViewById(R.id.tvScore)

        loadQuestion()

        btnNext.setOnClickListener {
            val selected = radioGroup.checkedRadioButtonId
            if (selected == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedView = findViewById<RadioButton>(selected)
            val selectedIndex = radioGroup.indexOfChild(selectedView)
            if (selectedIndex == questions[currentIndex].answerIndex) score++

            currentIndex++
            if (currentIndex < questions.size) {
                loadQuestion()
            } else {
                showResult()
            }
        }
    }

    private fun loadQuestion() {
        val q = questions[currentIndex]
        tvProgress.text = "Question ${currentIndex + 1} / ${questions.size}"
        tvQuestion.text = q.question
        radioGroup.removeAllViews()
        radioGroup.clearCheck()

        q.options.forEachIndexed { i, option ->
            val rb = RadioButton(this)
            rb.text = option
            rb.id = i
            rb.textSize = 16f
            rb.setPadding(8, 12, 8, 12)
            radioGroup.addView(rb)
        }

        btnNext.text = if (currentIndex == questions.size - 1) "Submit" else "Next"
        tvScore.text = ""
    }

    private fun showResult() {
        tvQuestion.text = "Quiz Completed!"
        tvProgress.text = "Final Score"
        radioGroup.removeAllViews()
        tvScore.text = "You scored $score out of ${questions.size}\n" +
            when {
                score == questions.size -> "🏆 Perfect! Outstanding safety knowledge!"
                score >= questions.size * 0.75 -> "✅ Great job! Stay safety conscious."
                score >= questions.size * 0.5  -> "⚠️ Good effort. Review safety guidelines."
                else                           -> "❌ Please retake the safety training."
            }
        btnNext.text = "Retake Quiz"
        btnNext.setOnClickListener {
            currentIndex = 0
            score = 0
            loadQuestion()
        }
    }
}
