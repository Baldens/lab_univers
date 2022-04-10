package com.example.labone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.labone.day_planer.ListToDoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textMain)

        val button: Button = findViewById(R.id.buttonW)
        val buttonNext: Button = findViewById(R.id.buttonNextLabTwo)
        val buttonNextLabThree: Button = findViewById(R.id.buttonNextLabThree)
        val buttonNextLabFour: Button = findViewById(R.id.buttonNextLabFour)

        button.setOnClickListener (object: View.OnClickListener {
            override fun onClick(v: View?) {
                textView.text = "Hello World!"
            }
        })
        buttonNext.setOnClickListener{
            val intent = Intent(this,ActivityCalculate::class.java)
            startActivity(intent)
        }
        buttonNextLabThree.setOnClickListener{
            val intent = Intent(this,ActivityCalculateInterface::class.java)
            startActivity(intent)
        }
        buttonNextLabFour.setOnClickListener{
            val intent = Intent(this,ListToDoActivity::class.java)
            startActivity(intent)
        }
    }
}