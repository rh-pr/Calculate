package com.example.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var display: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.input)
        display.showSoftInputOnFocus = false

        display.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                if (getString(R.string.display) == display.text.toString()){
                    display.setText("")
                }
            }
        })
    }

    private fun updateText(strToAdd: String){
        val oldStr: String = display.text.toString()
        val cursorPos: Int = display.selectionStart
        val leftStr: String = oldStr.substring(0,cursorPos)
        val rightStr: String = oldStr.substring((cursorPos))
        if(getString(R.string.display) == display.text.toString()){
            display.setText(strToAdd)
            display.setSelection(cursorPos + 1)

        } else {
            display.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr))
            display.setSelection(cursorPos + 1)
        }
    }

    private  fun isSubStr(st: String): Boolean {
        var mathSymbol: String = "(=+-×÷"

        mathSymbol.forEach { if (it.toString() == st) return true }
        return false
    }

    fun zeroBTN(view: View) {
        updateText("0")
    }

    fun oneBTN(view: View){
        updateText("1")
    }

    fun twoBTN(view: View){
        updateText("2")
    }

    fun treeBTN(view: View){
        updateText("3")
    }

    fun fourBTN(view: View){
        updateText("4")
    }

    fun fiveBTN(view: View){
        updateText("5")
    }

    fun sixBTN(view: View){
        updateText("6")
    }

    fun sevenBTN(view: View){
        updateText("7")
    }

    fun eightBTN(view: View){
        updateText("8")
    }

    fun nineBTN(view: View){
        updateText("9")
    }

    fun multiplyBTN(view: View){
        updateText("×")
    }

    fun divedeBTN(view: View){
        updateText("÷")
    }

    fun subtractBTN(view: View){
        updateText("-")
    }

    fun addBTN(view: View){
        updateText("+")
    }

    fun clearBTN(view: View){
        with(display) { setText("") }
    }

    fun parBTN(view: View){
        val cursorPos: Int = display.selectionStart
        val textLen: Int = display.text.length
        var openPar: Int = 0
        var closePar: Int = 0


        for (i in 0 until cursorPos){
            if(display.text.toString().substring(i, i+1) == "("){
                openPar +=1
            }
            if(display.text.toString().substring(i, i+1) == ")"){
                closePar +=1
            }
        }

        if (openPar == closePar || display.text.toString().substring(textLen-1, textLen) == "("){
            updateText("(")
        }
        else if (closePar < openPar || !(display.text.toString().substring(textLen-1, textLen) == "(")){
            updateText(")")
        }
        display.setSelection(cursorPos + 1)
    }

    fun expBTN(view: View){
        updateText("^")
    }

    fun plusMinusBTN(view: View){
        val cursorPos: Int = display.selectionStart
        val textLen: Int = display.text.length

        if(textLen == 2 &&  display.text.toString() == "(-") {
            with(display) { setText("") }
        }
        else if(textLen == 0) {
                updateText("(-")
                display.setSelection(cursorPos + 2)
        }
        else if(display.text.toString().substring(textLen-1, textLen) == ")") {
                updateText("×(-")
                display.setSelection(cursorPos + 3)
        }
        else if(cursorPos == 0){
            if(display.text.toString().substring(0, 1) == "("){
                display.setSelection(1)
            }
            updateText("(-")
            display.setSelection(cursorPos + 2)
        } else {

        }


    }

    fun decimalBTN(view: View){
        updateText(".")
    }

    fun equalBTN(view: View){
        var userExp: String = display.text.toString()

        userExp = userExp.replace("×","*")
        userExp = userExp.replace("÷","/")

        val exp = Expression(userExp)

        val result: String = exp.calculate().toString()

        display.setText(result)
        display.setSelection(result.length)
    }

    fun backspaceBTN(view: View){
        val cursorPos: Int = display.selectionStart
        val textLen = display.text.length

        if(cursorPos != 0 && textLen != 0){
            val selection: SpannableStringBuilder = display.text as SpannableStringBuilder
            selection.replace(cursorPos - 1, cursorPos, "")
            display.text = selection
            display.setSelection(cursorPos - 1)
        }
    }
}