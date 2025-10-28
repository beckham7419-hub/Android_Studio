package com.cursosant.sharedpref

import android.graphics.Color
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

    private val PREFS_NAME = "mis preferencias"
    private val KEY_TEXTO = "texto_guardado"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtContenido = findViewById<TextView>(R.id.txtContenido)
        val edtGuardar = findViewById<EditText>(R.id.edtGuardar)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val btnBorrar = findViewById<Button>(R.id.btnBorrar)

        val prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE)
        mostrarContenido(txtContenido,prefs.getString(KEY_TEXTO,null))

        btnGuardar.setOnClickListener {
            val texto = edtGuardar.text.toString()
            if (texto.isNotEmpty()) {
                prefs.edit().putString(KEY_TEXTO, texto).apply()

                edtGuardar.text.clear()
                mostrarContenido(txtContenido, texto)
                Toast.makeText(this, "Contenido guardado", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "No hay contenido para guardar", Toast.LENGTH_SHORT).show()
            }
        }

        btnBorrar.setOnClickListener {
            prefs.edit().remove(KEY_TEXTO).apply()
            mostrarContenido(txtContenido, null)
            Toast.makeText(this, "Contenido borrado", Toast.LENGTH_SHORT).show()
        }

    }
    private fun mostrarContenido(txtContenido: TextView, texto: String?) {
        if (texto.isNullOrEmpty()) {
            txtContenido.text = "no hay contenido guardado"
            txtContenido.setTextColor(Color.RED)
        }
        else {
            txtContenido.text = texto
            txtContenido.setTextColor(Color.BLACK)
        }
    }
}