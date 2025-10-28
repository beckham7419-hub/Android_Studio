package com.cursosant.tiktaktoegato

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var JActivo = false
    var turnAct = ""
    var cntRendirse = 0
    var nombreO = ""
    var nombreX = ""
    lateinit var nomJug: TextView
    lateinit var nomO: EditText
    lateinit var nomX: EditText
    lateinit var arreglo: Array<TextView>
    val tablero = Array(3) { Array(3) { "" } }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nomO = findViewById<EditText>(R.id.nomO)
        nomX = findViewById<EditText>(R.id.nomX)
        nomJug = findViewById(R.id.nomJug)
        val cs1 = findViewById<TextView>(R.id.cs1)
        val cs2 = findViewById<TextView>(R.id.cs2)
        val cs3 = findViewById<TextView>(R.id.cs3)
        val cs4 = findViewById<TextView>(R.id.cs4)
        val cs5 = findViewById<TextView>(R.id.cs5)
        val cs6 = findViewById<TextView>(R.id.cs6)
        val cs7 = findViewById<TextView>(R.id.cs7)
        val cs8 = findViewById<TextView>(R.id.cs8)
        val cs9 = findViewById<TextView>(R.id.cs9)
        val btnEmp = findViewById<Button>(R.id.btnEmp)
        val btnRen = findViewById<Button>(R.id.btnRen)
        arreglo = arrayOf(cs1, cs2, cs3, cs4, cs5, cs6, cs7, cs8, cs9)

        btnEmp.setOnClickListener {
            val JugO = nomO.text.toString().trim()
            val JugX = nomX.text.toString().trim()
            if (JugO.isEmpty() || JugX.isEmpty()) {
                Toast.makeText(this,"El juego no ha comenzado, ingrese 2 nombres",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (JugO == JugX) {
                Toast.makeText(this,"Los nombres no pueden ser iguales",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (JActivo == true) {
                Toast.makeText(this,"El juego ya ha comenzado",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val numram = (1..100).random()
            nombreO = JugO
            nombreX = JugX
            if (numram <= 50) {
                nomJug.text = nombreO
                turnAct = "O"
            } else {
                nomJug.text = nombreX
                turnAct = "X"
            }
            limpiarTab()
            JActivo = true
            cntRendirse = 0
        }

        btnRen.setOnClickListener {
            val JugO = nomO.text.toString().trim()
            val JugX = nomX.text.toString().trim()
            if (JugO.isEmpty() || JugX.isEmpty()) {
                Toast.makeText(this, "El juego no ha comenzado", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (JugO == JugX) {
                Toast.makeText(this,"Los nombres no pueden ser iguales",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (JActivo == false) {
                Toast.makeText(this,"El juego no ha comenzado",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            cntRendirse++
            if (cntRendirse < 5) {
                val veces = 5 - cntRendirse
                Toast.makeText(this,"Presiona $veces veces para rendirse",Toast.LENGTH_SHORT).show()
            } else {
                val gano: String
                if (turnAct == "O") {
                    gano = nombreX
                } else {
                    gano = nombreO
                }
                Toast.makeText(this,"Ganador: $gano",Toast.LENGTH_SHORT).show()
                reset()
                JActivo = false
            }
        }

        arreglo.forEachIndexed { i, casilla ->
            casilla.setOnClickListener {
                val JugO = nomO.text.toString().trim()
                val JugX = nomX.text.toString().trim()
                if (JActivo == false) {
                    if (JugO.isEmpty() || JugX.isEmpty()) {
                        Toast.makeText(this,"El juego no ha comenzado, ingrese 2 nombres",Toast.LENGTH_SHORT).show()
                    } else if (JugO == JugX) {
                        Toast.makeText(this,"Los nombres no pueden ser iguales",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"Presiona 'Empezar'",Toast.LENGTH_SHORT).show()
                    }
                    return@setOnClickListener
                }

                if (casilla.text.isNotEmpty()) {
                    Toast.makeText(this,"Casilla ocupada",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val fil = i / 3
                val colum = i % 3
                casilla.text = turnAct
                tablero[fil][colum] = turnAct
                if (verificarG()) {
                    val ganador = if (turnAct == "O") nombreO else nombreX
                    Toast.makeText(this,"Ganador $ganador",Toast.LENGTH_LONG).show()
                    reset()
                    JActivo = false
                    return@setOnClickListener
                }

                if (verificarEmp()) {
                    Toast.makeText(this,"Empate",Toast.LENGTH_LONG).show()
                    reset()
                    JActivo = false
                    return@setOnClickListener
                }
                cambiarT()
                cntRendirse = 0
            }
        }
    }

    fun limpiarTab() {
        arreglo.forEach { it.text = "" }
        for (i in 0..2) {
            for (j in 0..2) {
                tablero[i][j] = ""
            }
        }
    }

    fun reset() {
        limpiarTab()
        nomJug.text = ""
        nomO.text.clear()
        nomX.text.clear()
        JActivo = false
        turnAct = ""
        nombreO = ""
        nombreX = ""
        cntRendirse = 0
    }

    fun cambiarT() {
        turnAct = if (turnAct == "O") "X" else "O"
        nomJug.text = if (turnAct == "O") nombreO else nombreX
    }

    fun verificarEmp(): Boolean {
        for (i in 0..2) {
            for (j in 0..2) {
                if (tablero[i][j].isEmpty()) return false
            }
        }
        return true
    }

    fun verificarG(): Boolean {
        for (i in 0..2) {
            if (tablero[i][0] == turnAct && tablero[i][1] == turnAct && tablero[i][2] == turnAct) {
                return true
            }
        }
        for (i in 0..2) {
            if (tablero[0][i] == turnAct && tablero[1][i] == turnAct && tablero[2][i] == turnAct) {
                return true
            }
        }
        if (tablero[0][0] == turnAct && tablero[1][1] == turnAct && tablero[2][2] == turnAct) {
            return true
        }
        if (tablero[0][2] == turnAct && tablero[1][1] == turnAct && tablero[2][0] == turnAct) {
            return true
        }
        return false
    }
}