package com.cursosant.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = "0"
    private var operator: String? = null
    private var firstOperand: Double? = null
    private var waitingForNewNumber = false
    private var hasError = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        display = findViewById(R.id.display)
        setupButtons()
        updateDisplay()
    }

    private fun setupButtons() {
        // Números
        findViewById<Button>(R.id.btn0).setOnClickListener { appendNumber("0") }
        findViewById<Button>(R.id.btn1).setOnClickListener { appendNumber("1") }
        findViewById<Button>(R.id.btn2).setOnClickListener { appendNumber("2") }
        findViewById<Button>(R.id.btn3).setOnClickListener { appendNumber("3") }
        findViewById<Button>(R.id.btn4).setOnClickListener { appendNumber("4") }
        findViewById<Button>(R.id.btn5).setOnClickListener { appendNumber("5") }
        findViewById<Button>(R.id.btn6).setOnClickListener { appendNumber("6") }
        findViewById<Button>(R.id.btn7).setOnClickListener { appendNumber("7") }
        findViewById<Button>(R.id.btn8).setOnClickListener { appendNumber("8") }
        findViewById<Button>(R.id.btn9).setOnClickListener { appendNumber("9") }
        findViewById<Button>(R.id.btnDot).setOnClickListener { appendNumber(".") }

        // Operadores
        findViewById<Button>(R.id.btnAdd).setOnClickListener { appendOperator("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { appendOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { appendOperator("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { appendOperator("/") }

        // Funciones especiales
        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculate() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearLast() }
        findViewById<Button>(R.id.btnClearAll).setOnClickListener { clearAll() }
    }

    private fun updateDisplay() {
        display.text = currentInput
        display.setTextColor(resources.getColor(android.R.color.black, null))
        hasError = false
    }

    private fun showError(message: String) {
        display.text = message
        display.setTextColor(resources.getColor(android.R.color.holo_red_dark, null))
        hasError = true

        // Resetear después de 1.5 segundos
        display.postDelayed({
            if (hasError) {
                currentInput = "0"
                updateDisplay()
            }
        }, 1500)
    }

    private fun appendNumber(num: String) {
        if (hasError) return

        when {
            waitingForNewNumber -> {
                currentInput = num
                waitingForNewNumber = false
            }
            num == "." && currentInput.contains(".") -> return
            currentInput == "0" -> currentInput = num
            else -> currentInput += num
        }
        updateDisplay()
    }

    private fun appendOperator(op: String) {
        if (hasError) return

        when {
            firstOperand == null -> {
                firstOperand = currentInput.toDouble()
            }
            operator != null && !waitingForNewNumber -> {
                val result = performCalculation()
                if (result == null) return
                currentInput = formatResult(result)
                firstOperand = result
                updateDisplay()
            }
        }

        operator = op
        waitingForNewNumber = true
    }

    private fun calculate() {
        if (hasError) return

        if (operator == null || firstOperand == null || waitingForNewNumber) return

        val result = performCalculation()
        if (result == null) return

        currentInput = formatResult(result)
        firstOperand = null
        operator = null
        waitingForNewNumber = true
        updateDisplay()
    }

    private fun performCalculation(): Double? {
        val secondOperand = currentInput.toDouble()
        val first = firstOperand ?: return null

        return when (operator) {
            "+" -> first + secondOperand
            "-" -> first - secondOperand
            "*" -> first * secondOperand
            "/" -> {
                if (secondOperand == 0.0) {
                    showError("Error: División por cero")
                    return null
                }
                first / secondOperand
            }
            else -> null
        }
    }

    private fun formatResult(result: Double): String {
        return if (result == result.toLong().toDouble()) {
            result.toLong().toString()
        } else {
            String.format("%.8f", result).trimEnd('0').trimEnd('.')
        }
    }

    private fun clearLast() {
        if (hasError) return

        if (currentInput.length <= 1 || currentInput == "0") {
            showError("Error: No hay nada que borrar")
            return
        }

        currentInput = currentInput.dropLast(1)
        updateDisplay()
    }

    private fun clearAll() {
        currentInput = "0"
        operator = null
        firstOperand = null
        waitingForNewNumber = false
        updateDisplay()
    }
}