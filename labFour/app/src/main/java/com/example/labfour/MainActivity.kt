package com.example.labfour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.labfour.day_planer.ActivityListToDo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonNextLabFour: Button = findViewById(R.id.buttonNextLabFour)

        buttonNextLabFour.setOnClickListener{
            val intent = Intent(this, ActivityListToDo::class.java)
            startActivity(intent)
        }
    }
}