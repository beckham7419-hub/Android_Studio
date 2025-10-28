package com.cursosant.urls

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val PREFS_NAME = "mis_preferencias"
    val KEY_NOMBRE = "nombre"
    val KEY_APELLIDO = "apellido"
    val KEY_EDAD = "edad"
    val KEY_VIVO = "vivo"
    val KEY_EXISTE_DATOS = "existe_datos"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etnom = findViewById<EditText>(R.id.etnom)
        val etape = findViewById<EditText>(R.id.etape)
        val eted = findViewById<EditText>(R.id.eted)
        val etvi = findViewById<EditText>(R.id.etvi)
        val btn = findViewById<Button>(R.id.btn)
        val btn2 = findViewById<Button>(R.id.btn2)

        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        cargaDatos(prefs, etnom, etape, eted, etvi)

        btn.setOnClickListener {
            val nombre = etnom.text.toString().trim()
            val apellido = etape.text.toString().trim()
            val edadStr = eted.text.toString().trim()
            val vivoStr = etvi.text.toString().trim()
            if (nombre.isEmpty() || apellido.isEmpty() || edadStr.isEmpty() || vivoStr.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!soloLetras(nombre)) {
                Toast.makeText(this, "El nombre solo puede contener letras", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!soloLetras(apellido)) {
                Toast.makeText(this, "El apellido solo puede contener letras", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!edadStr.matches(Regex("^\\d+\$"))) {
                Toast.makeText(this, "La edad debe ser un numero entero sin decimales", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val edad = edadStr.toIntOrNull()
            if (edad == null) {
                Toast.makeText(this, "La edad debe ser un numero valido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (edad < 0) {
                Toast.makeText(this, "La edad no puede ser un numero negativo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val vivo = convertirBoolean(vivoStr)
            if (vivo == null) {
                Toast.makeText(this, "Escribe 'Vivo' o 'Muerto' en el campo Vivo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val editor = prefs.edit()
            editor.putString(KEY_NOMBRE, nombre)
            editor.putString(KEY_APELLIDO, apellido)
            editor.putInt(KEY_EDAD, edad)
            editor.putBoolean(KEY_VIVO, vivo)
            editor.putBoolean(KEY_EXISTE_DATOS, true)
            editor.apply()
            Toast.makeText(this, "Datos guardados: $nombre $apellido, $edad años, Vivo:$vivo", Toast.LENGTH_LONG).show()
        }

        btn2.setOnClickListener {
            val editor = prefs.edit()
            editor.clear()
            editor.apply()
            etnom.text.clear()
            etape.text.clear()
            eted.text.clear()
            etvi.text.clear()
            Toast.makeText(this, "Datos borrados correctamente", Toast.LENGTH_SHORT).show()
        }
    }

    fun convertirBoolean(texto: String): Boolean? {
        return when (texto.lowercase()) {
            "vivo" -> true
            "muerto" -> false
            else -> null
        }
    }

    fun cargaDatos(prefs: android.content.SharedPreferences, etnom: EditText, etape: EditText, eted: EditText, etvi: EditText) {
        val existeDatos = prefs.getBoolean(KEY_EXISTE_DATOS, false) // Carga si existen datos anteriormente
        if (!existeDatos) {
            return
        }
        val nombre = prefs.getString(KEY_NOMBRE, "")
        val apellido = prefs.getString(KEY_APELLIDO, "")
        val edad = prefs.getInt(KEY_EDAD, 0)
        val vivo = prefs.getBoolean(KEY_VIVO, true)
        etnom.setText(nombre)
        etape.setText(apellido)
        eted.setText(edad.toString())
        etvi.setText(if (vivo) "Vivo" else "Muerto")
    }

    fun soloLetras(texto: String): Boolean {
        return texto.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+\$"))
    }
}