package com.example.labone.day_planer

import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.labone.R
import com.example.labone.day_planer.db.DB
import com.example.labone.day_planer.db.ModelPlanner
import org.json.JSONObject
import org.json.JSONArray

class AddToDoActivity : AppCompatActivity() {
    private lateinit var getData: GetData
    private lateinit var sqliteHelper: DB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do)

        val nameEditText: EditText = findViewById(R.id.editTextTextNameToDo)
        val descriptionEditText: EditText = findViewById(R.id.editTextTextDescriptionToDo)

        val buttonAddNew: Button = findViewById(R.id.buttonAddToDo)
        buttonAddNew.setOnClickListener{
            val nameText = nameEditText.text.toString()
            val descriptionText = descriptionEditText.text.toString()

            if(nameText.isEmpty() || descriptionText.isEmpty()){
                Toast.makeText(this, "Пожалуйста заполните данные", Toast.LENGTH_LONG).show()
            }else{
                val std = ModelPlanner(name = nameText, description = descriptionText)
                val status = sqliteHelper.insertPlanner(std)

                if(status > -1){
                    Toast.makeText(this, "Добавили новое дело", Toast.LENGTH_LONG).show()

                }else{
                    Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show()
                }
            }

        }
        sqliteHelper = DB(this)
    }

}