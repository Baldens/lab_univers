package com.example.labone

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Math.sqrt
import android.widget.Toast




class ActivityCalculate : AppCompatActivity() {
    val integerChars = '0'..'9'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate)

        val buttonCalc: Button = findViewById(R.id.buttonCalculate)
        val inputA: EditText = findViewById(R.id.inputA)
        val inputB: EditText = findViewById(R.id.inputB)
        var inputC: EditText = findViewById(R.id.inputC)

        buttonCalc.setOnClickListener {
            val a = inputA.text.trim().toString().toDouble()
            val b = inputB.text.trim().toString().toDouble()
            val c = inputC.text.trim().toString().toDouble()
            if(!checkIfForInputs(inputA, inputB, inputC)){
                onCreateDialog("не верный формат")
            }else{
                val discrResult: Double = discriminant(a, b, c)

                if(a == 0.0 && b == 0.0 && c > 0.0){
                    onCreateDialog("Нету корней")
                }else if(a == 0.0 && b == 0.0 && c == 0.0){
                    onCreateDialog("Корней бесконечное можество")
                }else{
                    if(discrResult > 0){
                        val sd = sqrt(discrResult)
                        val xOne = (-b + sd)
                        val xTwo = (-b - sd)
                        onCreateDialog("x1 = $xOne; x2 = $xTwo")
                    }else if(discrResult == 0.0){
                        val x: Double = (-b/2*a)
                        onCreateDialog("x: " + x.toString())
                    }else{
                        onCreateDialog("Нету корней")
                    }
                }

            }

        }
    }

    private fun checkIfForInputs(inputA: EditText, inputB: EditText, inputC: EditText): Boolean{
        val inputADouble = inputA.text.trim().toString()
        val inputBDouble = inputB.text.trim().toString()
        val inputCDouble = inputC.text.trim().toString()

        if(!(inputADouble.isNullOrEmpty() || inputBDouble.isNullOrEmpty() || inputCDouble.isNullOrEmpty())){
            if(isInteger(inputADouble) || isInteger(inputBDouble) || isInteger(inputCDouble)){
                return true
            }
        }

        return false
    }

    private fun isInteger(input: String) = input.all { it in integerChars }
    private fun discriminant(a: Double, b: Double, c: Double) = sqr(b) - 4 * a * c
    private fun sqr(x: Double): Double = x * x

    private fun onCreateDialog(result: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Результат")
            .setMessage("Ответ: " + result)
            .setPositiveButton("OK") { dialog, id ->
                dialog.cancel()
            }
        builder.show()
    }
}