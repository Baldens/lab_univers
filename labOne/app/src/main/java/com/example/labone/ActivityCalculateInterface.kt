package com.example.labone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.regex.Pattern

class ActivityCalculateInterface : AppCompatActivity(), View.OnClickListener {
    private var numInputOne: Double = 0.0
    private var boolCheck: Boolean = true
    private var boolCheckDifference: Boolean = true
    private var operation: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_interface)

        setButtonsInCalculate()
    }

    private fun setButtonsInCalculate(){
        var layoutOnDisplay: LinearLayout = findViewById(R.id.layoutCalc)
        var indexTextViewInButton: Int = 1
        for (i in 0..3){
            val layout: LinearLayout = LinearLayout(this)
            val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
            layoutParams.topMargin = 10
            layoutParams.weight = 1.0F
            layout.layoutParams = layoutParams

            for (j in 1..3){
                if(!((i == 3) && (j > 1))){
                    if(indexTextViewInButton == 10){
                        indexTextViewInButton = 0
                    }
                    val buttonParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT)
                    buttonParams.weight = 1.0F

                    val button: Button = Button(this)
                    button.setText(indexTextViewInButton.toString())
                    button.id = indexTextViewInButton
                    button.layoutParams = buttonParams
                    button.setOnClickListener(View.OnClickListener{ view ->
                        val inputTextViewCalculate: TextView = findViewById(R.id.inputNumberCalculate)
                        var howButtonClick: Int = 0

                        if(inputTextViewCalculate.text.toString().equals("Error: DivByZero")){
                            inputTextViewCalculate.setText("")
                        }

                        for (k in 0..9){
                            when(view?.id){
                                k -> {
                                    howButtonClick = k
                                }
                            }
                        }

                        var getStringFromInput: String = ""
                        if(!inputTextViewCalculate.text.toString().equals("0")){
                            getStringFromInput = inputTextViewCalculate.text.toString() + howButtonClick.toString()
                        }else{
                            getStringFromInput = howButtonClick.toString()
                        }
                        inputTextViewCalculate.setText(getStringFromInput)
                    })
                    layout.addView(button)
                    indexTextViewInButton++
                }
            }
            layoutOnDisplay.addView(layout)
        }
    }

    override fun onClick(view: View?) {
        val inputTextViewCalculate: TextView = findViewById(R.id.inputNumberCalculate)
        var numInputTwo = 0.0
        if(!inputTextViewCalculate.text.toString().equals("")){
            numInputTwo = inputTextViewCalculate.text.toString().toDouble()
        }
        if(boolCheck){
            numInputOne = numInputTwo
            boolCheck = false
        }

        when(view?.id){
            R.id.buttonDifference -> {
                inputTextViewCalculate.setText("")
                operation = "/"
            }
            R.id.buttonСomposition -> {
                inputTextViewCalculate.setText("")
                operation = "*"
            }
            R.id.buttonPlus -> {
                inputTextViewCalculate.setText("")
                operation = "+"
            }
            R.id.buttonMinus -> {
                inputTextViewCalculate.setText("")
                operation = "-"
            }
            R.id.buttonComma -> {
                val countOfSymbol = inputTextViewCalculate.text.toString().count { it == '.'}
                if(countOfSymbol == 0){
                    val getStringFromInput = inputTextViewCalculate.text.toString() + "."
                    inputTextViewCalculate.setText(getStringFromInput)
                }
            }
            R.id.buttonEqually ->{
                if(operation.equals("/")){
                    if(numInputTwo != 0.0){
                        numInputOne /= numInputTwo
                    }else{
                        boolCheckDifference = false
                        inputTextViewCalculate.setText("Error: DivByZero")
                    }
                }else if(operation.equals("*")){
                    numInputOne *= numInputTwo
                }else if(operation.equals("-")){
                    numInputOne -= numInputTwo
                }else if(operation.equals("+")){
                    numInputOne += numInputTwo
                }
                if(boolCheckDifference){
                    inputTextViewCalculate.setText(String.format("%.2f", numInputOne).toDouble().toString())
                }
                boolCheckDifference = true
                boolCheck = true
            }
            R.id.buttonCN ->{
                numInputOne = 0.0
                boolCheck = true
                inputTextViewCalculate.setText("")
            }
        }
    }
}