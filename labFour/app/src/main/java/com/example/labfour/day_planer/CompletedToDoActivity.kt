package com.example.labfour.day_planer

import SessionIndex
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.labfour.MainActivity
import com.example.labfour.R
import com.example.labfour.day_planer.db.DB
import java.util.HashMap

class CompletedToDoActivity : AppCompatActivity() {
    private var index: Int = 0
    private lateinit var sqliteHelper: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_to_do)

        val sessionIndex = SessionIndex(this)
        val user: HashMap<String, String?> = sessionIndex.getIndex()

        var textTitleView: TextView = findViewById(R.id.editTextTextNameToDo2)
        var textDescriptorView: TextView = findViewById(R.id.editTextTextDescriptionToDo2)

        sqliteHelper = DB(this)
        val stdList = sqliteHelper.getPlanner(user.get("index").toString().toInt())
        textTitleView.text = stdList.first().name.toString()
        textDescriptorView.text = stdList.first().description.toString()

        val button: Button = findViewById(R.id.buttonDeletePlan)
        button.setOnClickListener{
            sqliteHelper.deletePlanner(user.get("index").toString().toInt())

            val intent = Intent(this, ActivityListToDo::class.java)
            startActivity(intent)
        }
    }
}